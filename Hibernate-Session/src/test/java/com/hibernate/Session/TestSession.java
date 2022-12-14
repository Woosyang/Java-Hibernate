package com.hibernate.Session;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.jdbc.Work;
import org.junit.*;
import com.hibernate.entity.News;

/**
 * Test Case of Hibernate Session
 * Session is an interface that Hibernate provides for the application to manipulate
 * the database. It has basic methods for saving, updating, deleting and loading java
 * objects. Session has a cache, the object inside that cache are called as persistent
 * object and the object corresponds to the related record in the table of the database.
 * As for the persistent object, there are 4 states of it in Hibernate: 
 * 1.Transient: OID is usually null, is not inside the Session cache and does not have
 * 				related record inside database
 * 2.Persist: OID is not null, is inside the Session cache and persistent object will 
 * 			  correspond to the related record inside database if database has already
 * 			  got the record of it.
 * 			  It will update the database synchronously when calling the flush() according
 * 			  to the modification of the persistent object(field change).
 * 			  In the cache of the same Session instance, each record in the table of the
 * 			  database will only correspond to a unique persistent object.
 * 3.Removed: does not have OID and related record in the table of database, is not in
 * 			  the Session cache 
 * 4.Detached: OID is not null, is no longer inside Session cache, Detached is transformed
 * 			   from Persist, it may has the related record inside table of the database
 * @author Woo
 *
 */
public class TestSession {
	private SessionFactory sessionfactory;
	private Session session;
	private Transaction transaction;
	
	@Before // before the @Test
	public void init() {
		// System.out.println("initialize");
		Configuration configuration = new Configuration().configure();
		sessionfactory = configuration.buildSessionFactory();
		session = sessionfactory.openSession();
		transaction = session.beginTransaction();
	}
	
	@After // after the @Test
	public void destroy() {
		// System.out.println("destroy");
		transaction.commit();
		session.close();
		sessionfactory.close();
	}
	
	/**
	 * flush: make the records in the table be consistent with the state of the object
	 * 		  inside Session cache, for the consistency, it may execute corresponding 
	 * 		  SQL statement
	 * 		  forces Hibernate to synchronize the in-memory state of the Session with 
	 * 		  the database.
	 * 1. when calling the commit() of the Transaction(transaction.commit()): 
	 * 	  call the flush() of the session first then commit the transaction
	 * 2. flush() may execute SQL statement but doesn't commit the transaction
	 * 3. note: it also can execute flush() before committing the transaction or 
	 * 	  explicitly calling session.flush()
	 * a. execute HQL or QBC query, it will execute flush() to get the latest record of 
	 * 	  the table(before transaction.commit())
	 * b. if the recorded ID of the item is generated by the database with auto-increment
	 * 	  strategy, when calling the save(), it will immediately send the insert query
	 * 	  to the database before transaction.commit(). Because it must make sure that
	 * 	  the ID of the item must exist after calling save(). 
	 * 	  <generator class="native"/> in the configuration file 
	 */
	
	// commit() VS flush(): flush() will execute the SQL queries but not commit
	// the transaction 
	// commit() will call the flush() first and then commit the transaction(persistence)
	@Test
	public void testSessionFlush02() {
		// example for note: b. call save() when using <generator class = "native"/>
		// before transaction.commit()
		News news = new News("Java", "Sun", new Date(System.currentTimeMillis()));
		session.save(news);
	}
	
	@Test
	public void testSessionFlush01() {
		News news01 = (News) session.get(News.class, 1);
		// news01.setAuthor("Oracle"); // call the update
		news01.setAuthor("Sun");
		// session.flush();
		// System.out.println("flush");
		
		// latest, example for note: a.(HQL, QBC), not explicitly call session.flush()
		News news02 = (News) session.createCriteria(News.class).uniqueResult();
		// the result of the select query will be latest, but the table still remain the same
		System.out.println(news02);
	}
	
	/**
	 * refresh: forced to send the select query to make the state of the object inside
	 * Session cache be consistent with the record in the table, reload all the data
	 */
	@Test
	public void testRefresh() {
		News news = (News) session.get(News.class, 1);
		System.out.println(news);
		// modify the information of the object in the table of the database first
		session.refresh(news);
		System.out.println(news);
	}
	
	/**
	 * clear: clear out the Session cache
	 */
	@Test
	public void testClear() {
		News news01 = (News) session.get(News.class, 1);
		System.out.println(news01);
		session.clear(); // without session.clear(), it only execute select query one time
		News news02 = (News) session.get(News.class, 1);
		System.out.println(news02);
	}
	
	/**
	 * save: make a transient object become to a persistent object, assign ID for the
	 * object, when calling flush(), it will send an insert query to the database, the
	 * ID is not valid before calling the save(), the ID of the persistent object can't
	 * be changed
	 */
	@Test
	public void testSave() {
		News news = new News("C++", "Bell", new Date(System.currentTimeMillis()));
		System.out.println(news); // transient object, does not have OID
		news.setId(100); // does not work
		session.save(news); // after save(), it will assign ID for the transient object
		System.out.println(news); // has OID
		
		// cause the exception because the ID of the persistent object can't be changed
		// news.setId(100); // exception 
	}
	
	/**
	 * persist: execute insert query
	 * it will throw an exception and no longer execute insert query if the object has
	 * already got the OID before calling the persist()
	 */
	@Test
	public void testPersist() {
		News news = new News("C#", "Microsoft", new Date(System.currentTimeMillis()));
		System.out.println(news);
		// news.setId(2); // exception, can't call persist() when the object has OID
		session.persist(news);
		System.out.println(news);
	}
	
	/**
	 * get VS load:
	 * 1. when calling get(), it will load the object immediately. However, when calling
	 * 	  load() and not use the object that is going to be loaded, it will not execute
	 * 	  select query immediately and return a proxy object.
	 * 	  get() uses eager loading but load() uses lazy loading
	 * 
	 * 2. In the load(), when the session is close before the proxy object needs to be
	 * 	  initialized, it will throw the LazyInitializationException Exception
	 * 
	 * 3. If there are no related records for the object and Session is not close and
	 *    use this object, it will return null as a result in get() and throw an exception
	 *    in load() 
	 */
	@Test
	public void testLoad() {
		News news01 = (News) session.load(News.class, 1);
		// System.out.println(news01); // comment out this will no longer execute the SQL query
		System.out.println(news01.getClass().getName()); // proxy class name

		// if there are no such record inside table of the database and use this object, 
		// it will throw an exception
		
		/*
		News news02 = (News) session.load(News.class, 10); // no such ID 10
		System.out.println(news02); // exception
		*/
		
		// session.close(); // can't close the session right now because it is lazy loading
		System.out.println(news01); // will cause the LazyInitializationException
	}
	
	@Test
	public void testGet() {
		News news01 = (News) session.get(News.class, 1);
		// session.close(); // compare it in the testLoad()
		System.out.println(news01); // comment out this will still execute the SQL query
		
		// if there are no such record inside table of the database, it will return
		// null as the result
		News news02 = (News) session.get(News.class, 10); // no such ID 10
		System.out.println(news02); // null
	}
	
	/**
	 * update: 
	 * 1. if a persistent object needs to be updated, there is no need to call
	 *    session.update() because it will be updated by calling flush() when 
	 *    committing the transaction(transaction.commit())
	 *    
	 * 2. if a detached object wants to be updated, it needs to call session.update()
	 *    explicitly and make the detached object become to the persistent object
	 * 
	 * note:
	 * 1. no matter whether the information of the detached object is the same as
	 * 	  the record inside table of the database, it will always execute the update 
	 * 	  query  
	 * Q: How to avoid the session.update() to execute session.update() blindly?
	 * set the "select-before-update=true" in <class> of the .hbm.xml file
	 * default is false
	 * 
	 * 2. if there is no record in the table of database relating to the detached object
	 * 	  and still call session.update(), it will throw the exception
	 * 
	 * 3. when the update() is relating to a detached object, if there is already a
	 *    persistent object having the same OID inside Session cache, it will throw 
	 *    an exception because Session cache can't have different object having same OID
	 */
	@Test
	public void testUpdate() {
		News news = (News) session.get(News.class, 1);
		// news.setAuthor("Sun");
		// session.update(news); // no need to call session.update() explicitly
		
		// terminate the current session and transaction
		transaction.commit();
		session.close();
		
		// open a new session, different session instance
		session = sessionfactory.openSession();
		transaction = session.beginTransaction();
		// news right now is a detached object
		// will not execute the update query if not call session.update()
		news.setAuthor("Oracle"); 
		
		// even if there is no modification to the news and call session.update(), it
		// will still execute the update query in the case that closing the former
		// session and open a new session
		
		// note.2
		// it will throw the exception if there is no such OID
		// news.setId(100); // exception, no such object
		
		// note.3
		// because in the same session instance, can't have different object which has
		// the same OID
		// News newz = (News) session.get(News.class, 1); // will cause the exception
		
		// comment out:
		/*
		 * transaction.commit();
		 * session.close();
		 * session = sessionfactory.openSession();
		 *  transaction = session.beginTransaction();
		 */
		// will not execute the update query even if call session.update()
		// because news is a persistent object
		session.update(news);
	}
	
	/**
	 * note: if OID is not null and there is no record relating to this detached object,
	 *       it will throw an exception
	 * how to verify whether the object is a transient object:
	 * 1. the OID is null
	 * 2. the OID equals to "unsaved-value" in <id> of hbm.xml file
	 */
	@Test
	public void testSaveOrUpdate() {
		News news01 = new News("Mac", "Apple", new Date(System.currentTimeMillis()));
		// there the object is detached, it will call save(), insert query
		// news01.setId(1000); // exception, no such OID
		news01.setId(2); // will call update()
		session.saveOrUpdate(news01);
		
		/*
		// used with <id name="id" type="java.lang.Integer" unsaved-value="100"> 
		News news02 = new News("Galaxy", "Samsung", new Date(System.currentTimeMillis()));
		news02.setId(100);
		session.saveOrUpdate(news02);
		*/
	}
	
	/**
	 * delete: if the OID is matched up with the record inside table of database, it will
	 * 		   execute delete query. Otherwise, it will throw an exception
	 * we can setup "hibernate.use_identifier_rollback = true" in the configuration file 
	 * to make the OID of the object is going to be removed become to null or unsaved-value
	 */
	@Test
	public void testDelete() {
		// delete a detached object
		/*
		// can't used with the code deleting the persistent object
		News news01 = new News();
		news01.setId(2); 
		session.delete(news01);
		*/
		
		// delete a persistent object
		News news02 = (News) session.get(News.class, 6);
		session.delete(news02);
		
		// even if it calls the session.delete(), it will execute the delete query
		// when commit the transaction. In this statement block, it can still have
		// the OID
		System.out.println(news02);
	}
	
	/**
	 * evict: remove the designate persistent object from the Session cache 
	 */
	@Test
	public void testEvict() {
		News news01 = (News) session.get(News.class, 1);
		News news02 = (News) session.get(News.class, 7);
		
		news01.setAuthor("Oracle");
		news02.setTitle("C++");
		
		// news01 will not be modified because its persistent object has been 
		// removed from the Session cache
		session.evict(news01);
	}
	
	@Test
	public void testDoWork() {
		session.doWork(new Work() {
			public void execute(Connection conn) throws SQLException {
				System.out.println(conn);
				// operation to access the database with JDBC API
				// prepared statement
			}
		});
	}
	
	@Test 
	public void testSessionCache() {
		// System.out.println("test");
		News news01 = (News) session.get(News.class, 1);
		System.out.println(news01);
		// only execute the SQL query one time 
		// because of the session cache, like connection pool
		News news02 = (News) session.get(News.class, 1);
		System.out.println(news02);
	}
}
