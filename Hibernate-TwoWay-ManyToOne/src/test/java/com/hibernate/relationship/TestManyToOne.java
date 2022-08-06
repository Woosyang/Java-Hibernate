package com.hibernate.relationship;
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
		Customer C02 = new Customer();
		C02.setCustName("Mok");
		
		// Many
		Order O01 = new Order();
		O01.setOrderName("A");
		Order O02 = new Order();
		O02.setOrderName("B");
		Order O03 = new Order();
		O03.setOrderName("C");
		Order O04 = new Order();
		O04.setOrderName("D");
		
		// Many-One
		O01.setCustomer(C01);
		O02.setCustomer(C01);
		C01.getOs().add(O01);
		C01.getOs().add(O02);
		
		// test with save-update in the set for Many of the One
		// <set name="Os" table="Orde_r" inverse="true"  cascade="save-update"> in Customer
		O03.setCustomer(C02);
		O04.setCustomer(C02);
		C02.getOs().add(O03);
		C02.getOs().add(O04);
		// because of "save-update", there is no need to use session to save orders
		session.save(C02);
		
		// SQL order: insert Customer first then insert Orders (5 SQL queries)
		// insert the One first and then insert Many -> 3 insert queries and 2 update queries
		// One and Many both have the reference of each other(configuration file)
		// it will create extra 2 update SQL query
		// to solve the problem above, <set inverse = true>, make the One maintain
		// the mapping relationship passively
		// no extra update SQL query
		session.save(C01);
		session.save(O01); // double check the Customer
		session.save(O02); // double check the Customer
		
		/*
		// SQL order: insert Orders first then insert Customer (7 SQL queries)
		// insert Many first and then insert One -> 3 insert queries come out first
		// then 4 update queries
		// when inserting the Many first, there is no clue about the One(value = null), 
		// and it has to finish inserting the Many first and then sent the update query
		// to the database
		session.save(O01); // extra SQL: double check the Customer
		session.save(O02); // extra SQL: double check the Customer
		session.save(C01); // update the C01 value for O01, O02
		*/
	}
	
	@Test
	public void testManyToOneGet() {
		// O is the Many, the set of One inside Many(O) will be lazy loaded
		Order o = (Order) session.get(Order.class, 1);
		System.out.println(o);
		
		// the returned Collection type of the One inside Many is built-in Hibernate Collection type
		// this type has lazy loading function and can store the proxy object
		System.out.println(o.getCustomer().getOs());
		
		// session.close(); // comment out the session.close(); in destroy() 
		// it will cause LazyInitializationException
		
		// when the element inside set needs to be used, it will be initialized
		System.out.println(o.getCustomer().getOs().size());
 	}
	
	@Test
	public void testManyToOneUpdate() {
		Customer c = (Customer) session.get(Customer.class, 1);
		c.getOs().iterator().next().setOrderName("S");
	}
	
	@Test
	public void testManyToOneCascade() {
		Customer c = (Customer) session.get(Customer.class, 2);
		// used with <cascade="delete-orphan"> for the One side in the set of Many Side
		// the record of customer remains, the record of orders removed
		c.getOs().clear();
	}
	
	@Test
	public void testManyToOneDelete() {
		// can not delete the One directly when the One is related to Many (without setting up cascade) 
		// if you want to delete the Customer record, set the <cascade="delete"> in 
		// for the Many side in the One side configuration file
		Customer c = session.get(Customer.class, 1);
		session.delete(c);
	}
	
	@Test
	public void testOneToManyGet() {
		// 1.use lazy loading for the Many side
		Customer c = (Customer) session.get(Customer.class, 3);
		System.out.println(c.getCustName());
		
		// 2. get the set of the Many side by using build-in Hibernate data type(set)
		// this build-in data type is lazy loaded and proxy object
		
		// class org.hibernate.collection.internal.PersistentSet
		System.out.println(c.getOs().getClass());
		
		// session.close(); // will cause LazyInitializationException
		
		// when the element inside set needs to be used, it will be initialized
		System.out.println(c.getOs().size());
	}
	
	@After 
	public void destroy() {
		// System.out.println("destroy");
		transaction.commit();
		session.close();
		sessionfactory.close();
	}
}
