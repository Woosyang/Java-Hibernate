package com.hibernate.hello;
import java.sql.Date;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.junit.Test;
import junit.framework.TestCase;

/**
 * must create the table in database first
 * JUnit Test with Simple Hibernate Example
 * @author Woo
 *
 */
/*
 * persistence means store an object into the database forever
 * and it also means several operations to the object in the database: save, update
 * delete, search(load the objects which suit the requirement of the SQL query into the
 * memory), load(load the object from the database to the memory according to its specific
 * Object Identifier, each object gets its own unique OID, it also called as primary key 
 * in the database)
 */

/*
 * ORM use metadata in XML format to describe the mapping relationship of the objects
 *                XML file
 *		  /          |         \
 *	business logic  persistence  database
 */

/*
 * steps for create a hibernate project: 1. create configuration file 2. create the persistence object
 * 3. create mapping relationship file(hbm.xml) 4. use hibernate API to access the database
 */
public class HibernateTest extends TestCase {
	
	@Test
	public void test() {
		// 1. create a SessionFactory Object, thread safe, once it is built, it will be
		// assigned to specific configuration information, usually initialize one
		// session factory because it cost too much resource
		SessionFactory sessionFactory = null;
		
		// 1) create a configuration object -> corresponds to the basic information of 
		//    the configuration file of Hibernate and the information of the mapping 
		//    relationship of the objects
		Configuration configuration = new Configuration().configure(); // default file is hibernate.cfg.xml
		// 2) no needs to create a ServiceRegistry Object 
		sessionFactory = configuration.buildSessionFactory();
		
		// 2. create a Session Object
		// manage the interaction between database and application, core of the hibernate
		// all persistent objects must be managed by the session before they can be 
		// persisted. The life cycle of this object is very short. The Session object has
		// a first-level cache. Before executing flush, all data of the persistence layer operations 
		// are cached in the session object.
		// like connection of JDBC
		Session session = sessionFactory.openSession();
		
		// 3. start the transaction
		Transaction transaction = session.beginTransaction();
		
		// 4. execute the save operation
		News news01 = new News("Java", "Sun", new Date(System.currentTimeMillis()));
		session.save(news01);
		// the persistence object must have a default constructor -> reflection
		News news02 = (News) session.get(News.class, 2);
		System.out.println(news02);
		// 5. commit the transaction
		transaction.commit();
		
		// 6. close the session
		session.close();
		
		// 7. close the SessionFactory Object
		sessionFactory.close();
	}
}
