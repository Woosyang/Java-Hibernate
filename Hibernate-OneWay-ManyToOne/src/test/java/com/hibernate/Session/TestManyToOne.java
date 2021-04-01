package com.hibernate.Session;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.junit.*;
import com.hibernate.entity.*;

/**
 * Test Case of Hibernate ManyToOne
 * @author Woo
 *
 */
public class TestManyToOne {
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
	
	@Test
	public void testManyToOneSave() {
		// One
		Customer C01 = new Customer();
		C01.setCustName("Mow");
		
		// Many
		Order O01 = new Order();
		O01.setOrderName("C");
		Order O02 = new Order();
		O02.setOrderName("D");
		
		// Many-One
		O01.setCustomer(C01);
		O02.setCustomer(C01);
		
		// SQL order: insert Customer first then insert Orders (3 SQL queries)
		// insert the One first and then insert Many -> Only create insert query
		session.save(C01);
		session.save(O01);
		session.save(O02);
		
		/*
		// SQL order: insert Orders first then insert Customer (5 SQL queries)
		// insert Many first and then insert One -> 3 insert queries come out first
		// then 2 update queries
		// when inserting the Many first, there is no clue about the One(value = null), 
		// and it has to finish inserting the Many first and then sent the update query
		// to the database
		session.save(O01);
		session.save(O02);
		session.save(C01);
		*/
	}
	
	@Test
	public void testManyToOneGet() {
		// only getting the Many can't get the searching result of the One 
		Order o = (Order) session.get(Order.class, 1);
		System.out.println(o);
		// when getting the Many, its corresponding One will be a proxy Object
		System.out.println(o.getCustomer().getCustName());
		
		// session.close();  // comment out the session.close() in destroy()
		// when the session is close before getting the Customer, it will cause LazyInitializationException
		
		// when using the related object(One), it will will sent the corresponding SQL query
		Customer c = session.get(Customer.class, 1);
		System.out.println(c.getCustName()); // lazy loading
 	}
	
	@Test
	public void testManyToOneUpdate() {
		Order o = (Order) session.get(Order.class, 1);
		o.getCustomer().setCustName("Luck");
	}
	
	@Test
	public void testManyToOneDelete() {
		// can not delete the One directly when the One is related to Many (without setting up cascade) 
		Customer c = session.get(Customer.class, 1);
		session.delete(c);
	}
	
	@After 
	public void destroy() {
		// System.out.println("destroy");
		transaction.commit();
		session.close();
		sessionfactory.close();
	}
}
