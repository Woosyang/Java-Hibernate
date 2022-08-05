package com.hibernate.strategy;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.junit.*;
import com.hibernate.entity.*;
import java.util.*;

/**
 * Test Case of Hibernate Fetching Strategy
 * @author Woo
 *
 */

/*
 * the purpose of fetching strategy:
 * 1. save the memory: suppose we have one-to-one, Customer relates to Order, if we only
 * 					   need the Customer, there is no need to load the Order class
 * 2. increase the efficiency: reduce the number of the SQL queries
 */
public class TestFetchingStrategy {
	private SessionFactory sessionfactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init() {
		// System.out.println("initialize");
		Configuration configuration = new Configuration().configure();
		sessionfactory = configuration.buildSessionFactory();
		session = sessionfactory.openSession();
		transaction = session.beginTransaction();
	}
	
	/*
	 * two type of the fetching strategy:
	 * 1. immediate(eager) fetching: load the object fetched immediately 
	 * 2. lazy fetching: lazy loading for the object needs to be fetched, when the
	 * 					 fetched object is being used, the object will be loaded 
	 * 					 immediately
	 * if we want to access the fields of the child(many side) object, use eager loading.
	 * otherwise we use lazy loading to only access the reference of the object(Primary ID)
	 */
	@Test
	public void testClassLevelFetchingStrategy() {
		// load() normally use the lazy fetching strategy by default
		// load() also can use the immediate fetching strategy with 
		// <class lazy="false"> for the parent level(Customer)
		
		// no matter the <class lazy="false" or lazy="true">, Session's get()
		// and Query's list() always use immediate(eager) fetching strategy for class
		Customer C01 = (Customer) session.load(Customer.class, 1);
		// when using the lazy fetching strategy, be careful to the Exception
		System.out.println(C01.getClass());
		// the OID will be always be initialized by Hibernate no matter which 
		// fetching strategy is used
		System.out.println(C01.getCustID());
	}
	
	@Test // Customer to Order -> One to Many
	public void testOneToManyLevelFetchingStrategy() {
		Customer C01 = (Customer) session.get(Customer.class, 1);
		System.out.println(C01.getCustName());
		System.out.println(C01.getOrders().size());
		// lazy of the <set> determine the timing of initializing the collections
		// of the many
		
		// 1. one-to-many or many-to-many use the lazy fetching strategy for
		//    the <set> (Collections) of the many
		
		// 2. we can use lazy="false" in the <set> of the many to change the
		//    fetching strategy, usually we use the lazy fetching strategy. 
		//    In the <set/>, lazy="true" is the default configuration
		
		// 3. we can use lazy="extra" in the <set> of the many to enhance the 
		//    the lazy fetching strategy. It will generate count(OID) to delay
		//    the timing of initializing the collection
		Order O01 = new Order();
		O01.setOrderID(1);
		// need to override the equals() of the Order
		System.out.println(C01.getOrders().contains(O01));
		
		// when we use the default fetching strategy for the collections, Hibernate
		// will also initialize the collections when we use iterator(), size(), isEmpty()
		// contains() at the 1st time. However, when we use lazy="extra", we can delay 
		// the initializing timing for collections for size(), isEmpty(), contains() at
		// the 1st time(Except iterator).
		
		Hibernate.initialize(C01.getOrders());
	}
	
	@Test
	public void testSetBatchSize() {
		// HQL
		List<Customer> Customers = session.createQuery("From Customer").list();
		System.out.println(Customers.size());
		
		// traverse each Customer in Customers
		// will generate 4 SQL queries without using batch-size="5" in <set> of many
		for (Customer C : Customers) {
			if (C.getOrders() != null) {
				System.out.println(C.getOrders().size());
			}
		}
		// batch-size of <set/> can set up the amount of objects need to be initialized
		// in the collection of many. It can reduce the number of SQL queries
	}
	
	@Test
	public void testSetFetch() {
		// "fetch = ?" of <set> for many determine the way how to select the Orders
		// fetch="join" determine the timing of fetching the Orders
		
		// 1. fetch="select" is the default way for initializing the object
		
		// 2. fetch="subselect" can use the sub-select query to initialize
		//    all of the objects in the collections of many, it is used in
		//    where ... in statement and search all of the ID of one(Customer)
		//    Also, it will ignore the batch-size setting but "lazy = ?" setting still works
		
		List<Customer> Customers = session.createQuery("From Customer").list();
		System.out.println(Customers.size());
		
		for (Customer C : Customers) {
			if (C.getOrders() != null) {
				System.out.println(C.getOrders().size());
			}
		}
		
		// 3. (1) fetch="join" uses urgent left outer join to search the collection of
		//    many when loading the one. Also, the collections has been initialized
		//    when doing the left outer join query.
		//    (2) It will ignore the lazy setting and use lazy loading
		//    (3) HQL query will ignore fetch="join"
		Customer C = (Customer) session.get(Customer.class, 1);
		System.out.println(C.getOrders().size());
		
	}
	
	@Test // for Order.hbm.xml
	// lazy in <many-to-one> of Order.hbm.xml
	public void testManyToOneLevelFetchingStrategy() {
		// lazy="proxy" means the lazy fetching strategy for the Customer
		// lazy="false" means the immediate fetching strategy for the Customer
		// fetch="join" uses the urgent left outer join to search the object of one
		// when selecting the many
		
		/*
		Order Order = (Order) session.get(Order.class, 1);
		System.out.println(Order.getCustomer().getCustName());
		*/
		
		// set up batch-size in <class> of one to reduce the SQL query
		// <class name="Customer" table="Customer" lazy="true" batch-size="5">
		// Query.list() will ignore the urgent left outer join fetching strategy 
		// set by hbm.xml and use the lazy fetching strategy
		List<Order> Os = session.createQuery("From Order o").list();
		for (Order O : Os) {
			if (O.getCustomer() != null) {
				System.out.println(O.getCustomer().getCustName());
			}
		}
	}
	
	@After 
	public void destroy() {
		// System.out.println("destroy");
		transaction.commit();
		session.close();
		sessionfactory.close();
	}
}
