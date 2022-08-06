package com.hibernate.cache;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.*;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.jdbc.Work;
import org.hibernate.jpa.QueryHints;
import org.junit.*;

import com.hibernate.dao.DepartmentDao;
import com.hibernate.entity.*;
import com.hibernate.util.HibernateUtils;

/**
 * Test Hibernate 2nd Level Cache and Session Management 
 * @author Woo
 *
 */

/*
 * Hibernate provides 2 kinds of cache:
 * 1. 1st level cache is at the Session layer and it is managed by Hibernate. 1st level cache is in 
 *    the scope of transaction
 *    
 * 2. 2nd level cache is at the SessionFactory layer and it is in the scope of Process. (Thread is a segment of Process)   
 *    And only the not important data can be stored inside 2nd level cache.      
 */
public class TestLevelCacheAndSessionManagement {
	private SessionFactory sessionfactory;
	private Session session;
	private StatelessSession statelessSession;
	private Transaction transaction;
	private CriteriaBuilder criteriaBuilder; // for QBC
	
	@Before
	public void init() {
		// System.out.println("initialize");
		Configuration configuration = new Configuration().configure();
		sessionfactory = configuration.buildSessionFactory();
		session = sessionfactory.openSession();
		transaction = session.beginTransaction();
		criteriaBuilder = session.getCriteriaBuilder();
	}
	
	
	/*
	 * two kinds of cache inside SessionFactory:
	 * 1. built-in cache: Read only, can't be deleted and brought by Hibernate. When Hibernate is starting,
	 *                    it will put the mapping metadata and predefined SQL statements in the cache of 
	 *                    the SessionFactory. The mapping metadata is the copy of the data inside hbm.xml file.
	 * 
	 * 2. external cache: A configurable cache plug-in. By default, SessionFactory will not use this plug-in.
	 *                    The data inside the external cache is the copy of the data inside data base. Memory
	 *                    and hard disk can be taken as an external cache.                  
	 *                    
	 */
	
	
	/*
	 * when two concurrent transactions simultaneously access the same data of the cache at the 
	 * the persistence layer, it can also lead to multiple concurrent issues.
	 * 
	 * second level cache provide 4 kinds of concurrency strategies:
	 * 1. NONSTRICT_READ_WRITE: can not guarantee the consistency of the data in both the cache and the database and
	 *                          providing Read-Uncommitted transaction isolation level. When having the data that rarely
	 *                          be modified or in the process of dirty reading, it is the strategy can be used.
	 * 
	 * 2. READ_WRITE: providing Read-Committed transaction isolation level. When having the data will be read frequently but
	 *                modified rarely, it is the strategy can be used to prevent the dirty read.
	 *               
	 * 3. TRANSACTIONAL: providing Repeatable-Read transaction isolation level. When having the data will be read frequently but
	 *                   modified rarely, it is the strategy can be used to prevent the dirty read and non-repeatable read.
	 *                  
	 * 4. READ_ONLY: providing Serializable transaction isolation level. When having the data that will never ever be modified,
	 *               it is the strategy can be used.                                              
	 * 
	 */
	
	/* 1. using EH Cache
	 * <property name="cache.use_second_level_cache">true</property>
	 * <property name="hibernate.cache.region.factory_class"> org.hibernate.cache.ehcache.EhCacheRegionFactory</property>
	 * 
	 * 2. designate a certain target for EH Cache
	 * <class-cache usage="read-write" class="com.hibernate.entity.Employee"/> in hibernate.cfg.xml
	 * must provide absolute path of entity for class
	 * or
	 * <cache usage="read-write"/> in Employee.hbm.xml
	 */
	@Test 
	public void testSecondLevelCache() {
		Employee emp01 = session.get(Employee.class, 102);
		System.out.println(emp01.getName());
		
		// when fetching the Object with the same OID, if the Session is not close, it
		// won't generate duplicate select query for the Object
		
		// closing the current session and transaction will generate duplicate SQL query
		
		transaction.commit(); // commit the transaction first
		session.close();
		
		session = sessionfactory.openSession();
		transaction = session.beginTransaction();
		
		Employee emp02 = session.get(Employee.class, 102);
		System.out.println(emp02.getName());
		
	}
	
	
	@Test
	public void testSecondLevelCacheCollection() {
		Department dept01  = session.get(Department.class, 1);
		System.out.println(dept01.getDeptName());
		System.out.println(dept01.getEmployees().size());
		
		// closing the current session and transaction will generate duplicate SQL query
		// use second level cache to reduce query, set up inside hibernate.cfg.xml or 
		// entity.hbm.xml. 
		// use <class-cache usage="read-write" class="com.hibernate.entity.Department"/> for entity Department
		// use <collection-cache usage="read-write" collection="com.hibernate.entity.Department.Employees"/> for Employee Collection 
		// in Department entity, do not forget to set up <class-cache> for Employee entity otherwise it will also
		// generate duplicate records
		transaction.commit(); 
		session.close();
		session = sessionfactory.openSession();
		transaction = session.beginTransaction();
		
		Department dept02 = session.get(Department.class, 1);
		System.out.println(dept02.getDeptName());
		System.out.println(dept02.getEmployees().size());
						
	}
	
	// iterator() works same as list()
	// iterator() VS getResultList():
	// getResultList(): Executing SQL query that can load the entire data(select * from)
	// iterator(): Executing SQL query that that can only return the id of all the records(select id from where id = ?)
	//             when it traverse every record inside result set, this method will go to session or
	//             second level cache to check whether there is an object with a specific OID. If it exists, it will return 
	//             the object directly. If the object does not, the method will use query to fetch from data base like list().
	@Test
	@SuppressWarnings("unchecked")
	public void testQueryIterator() {
		// HQL does not use second level cache but query cache
		TypedQuery<Employee> query = session.createQuery("FROM Employee e Where e.Dept.DeptID = 1");
		List<Employee> employees = query.getResultList();
		System.out.println(employees.size());
		
		@SuppressWarnings("deprecation") // no more iterate() 
		Iterator<Employee> employeeIt = session.createQuery("FROM Employee e Where e.Dept.DeptID = 1").iterate();
		while (employeeIt.hasNext()) {
			System.out.println(employeeIt.next().getName());
		}
	}
	
	
	// For frequently used query, if query cache is enabled. When the query is executed for the first time, 
	// Hibernate will store the query results in the query cache to improve the performance. By default,
	// query caching will not be enable for QBC and HQL but we can use following steps to enable it:
	
	// 1. use query.setHint(QueryHints.HINT_CACHEABLE, true) with getResultList(), do not use this
	//    two methods separately
	// 2. enable query caching by using <property name="cache.use_query_cache">true</property> in cfg.xml
	@Test
	@SuppressWarnings("unchecked")
	public void testCacheQueryFetch() {
		// use HQL
		TypedQuery<Employee> query = session.createQuery("FROM Employee");
		// control Hibernate query caching
		List<Employee> employees = query.setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
		System.out.println(employees.size());
		
		employees = query.getResultList();
		System.out.println(employees.size());
		
		// test the query cache with time stamp query(UpdateTimestampsCache)
		// store the time stamp of the last updated table's time stamp, 
		// it tracks all the inserts, updates and deletes being done in a table through the Hibernate API, 
		// and as soon as one is done, this table's time stamp is updated. 
		
		Employee employee = session.get(Employee.class, 102);
		employee.setSalary(9800); // generate an update query and then a select query, UpdateTimestampsCache
		System.out.println(employees.size());
		
		System.out.println("Using QBC");
		// use QBC
		CriteriaQuery<Employee> cri = criteriaBuilder.createQuery(Employee.class);
		Root<Employee> root = cri.from(Employee.class);
		Predicate dept = criteriaBuilder.equal(root.get("Dept"), 2);
		query = session.createQuery(cri.where(dept));
		employees = query.setHint(QueryHints.HINT_CACHEABLE, true).getResultList();
		System.out.println(employees.size());
		
		// without setHint(QueryHints.HINT_CACHEABLE, true), the query statement will be
		// generated twice
		employees = query.getResultList();
		System.out.println(employees.size());
		
	}
	
	
	// there are 3 ways provided by Hibernate to manage session:
	// 1. The life cycle of the Session object is bound to the local thread
	// 2. The lifetime of the Session object is bound to the JTA transaction
	// 3. Hibernate delegate procedures to manage the Session object life cycle
	
	// focus on binding to the local thread: 
	// When a thread first calls the getCurrentSession() method of the SessionFactory object, the method will create a new Session(session A) object, 
	// it will bind the object to thread A, and returns the Session. When thread A calls the getCurrentSession() method of the SessionFactory again, 
	// the method will return the same session A object. When thread A commits a transaction associated with a session A object, 
	// Hibernate will automatically flush the cache of the session A and then commit the transaction and close the session in the end. 
	// The session A is also automatically closed when thread A revokes the transaction associated with the session A.
	// If thread A calls the getCurrentSession() method of the SessionFactory again, the method will create a new Session(sessionB), 
	// it will bind the object to thread A and return the new session B.
	
	@Test
	public void testManageSession() { // no need to close the session manually
		// get Session
	    // begin Transaction
		Session session = HibernateUtils.getInstance().getSession();
		System.out.println("HashCode ---> " + session.hashCode());
		Transaction tran = session.beginTransaction();
		Department dept = new Department();
		dept.setDeptID(16);
		dept.setDeptName("Offshore");
		DepartmentDao departMentDao = new DepartmentDao();
		departMentDao.save(dept); 
		departMentDao.save(dept);
		departMentDao.save(dept);
		
		// if the session is managed by local thread, the session will be closed when
		// commits or roll back the transaction
		tran.commit();
		System.out.println(session.isOpen()); // false
		
	}
	
	
	// there are 4 ways for batch job to process data:
	// 1. do it by using Session
	// 2. do it by using HQL(not good for insert batch job): 
	// only supports "insert into ... select" format query not "insert into ... values" format query.
	// 3. do it by using StatelessSession
	// 4. do it by using JDBC API. This is recommended because it is the fastest.
	@Test
	public void testBatchJobJDBC() {
		session.doWork(new Work() {
			public void execute(Connection connection) throws SQLException {
				// JDBC API
				Statement statement = connection.createStatement();
				for (int i = 0; i < 10; i ++) {
					statement.addBatch("insert into employee (EID, Salary, Name) values('" + (116 + i) + "', '1000" + i + "', 'User0" + i + "')");
				}
				statement.executeBatch();
				connection.commit();
			}
		});
	}
	
	
	/*
	 * doing batch job with session: 
	 * Both the save() and update() methods of Session store the processed objects in the cache. 
	 * If you are dealing with a large number of persistent objects with a single Session, 
	 * you should clear the cache of the persistent objects that have been processed and will not be accessed. 
	 * This is done by calling flush() to flush the cache after an object or a number of objects have been processed, 
	 * and then calling clear() to flush the cache.
	 * 
	 */
	@Test
	public void testBatchJobSession() {
		// In order to do the batch job by using Hibernate, we can set the batch job size 
		// inside Hibernate configuration file. The amount of the executed SQL queries will
		// be the same as the setting file's. <property name="hibernate.jdbc.batch_size">20</property>
		// If we use 'identity' generator, Hibernate will not have bulk insert function and we
		// usually turn off Hibernate level 2 cache for batch job.
		for (int i = 0; i < 10; i ++) {
			Employee emp = session.get(Employee.class, Integer.valueOf(116 + i));
			emp.setSalary(1000 + i);
			if (i % 5 == 0) {
				session.flush();
				session.clear();
			}
		}
		
		// When we doing the batch job update, it is not a good idea to load all of the objects
		// into Session cache and update them at once. We usually use ScrollableResults to do 
		// the batch job update. ScrollableResult is just a pointer locating the current object's
		// position. When we doing the iteration, it only load the object which is traced by the
		// current pointer.
		
		ScrollableResults sr = session.createQuery("From Employee").scroll();
		int count = 0;
		while (sr.next()) {
			Employee emp = (Employee) sr.get(0);
			emp.setName(" ");
			if((count ++) % 5 == 0) {
				session.flush();
				session.clear();
			}		
		}
		
	}
	
	
	/*
	 * StatelessSession is similar with Session but StatelessSession is different by the following features :
	   1. StatelessSession has no cache, and objects loaded, saved, or updated by StatelessSession is under detached state.
       2. StatelessSession does not interact with Hibernate's second level cache. When calling save(), update(), or delete() with StatelessSession, 
          those methods will immediately execute all the corresponding SQL statement.
       3. StatelessSession does not do dirty checking, we need to call the update() to update the data in the database when
          we modify a certain entity.
       4. StatelessSession does not perform any cascading operations for the associated objects. Object with the same OID will be loaded
          by it twice and the hash code for the Object with the same OID will be different.
	   5. The operations done by StatelessSession can be captured by an Intercepter but will be ignored by Hibernate's event handling system.
	 */
	@Test
	public void testBatchJobStatelessSession() {
		for (int i = 0; i < 10; i ++) {
			Employee emp = (Employee) statelessSession.get(Employee.class, Integer.valueOf(116 + i));
			emp.setSalary(0);
			if (i % 5 == 0) {
				session.flush();
				session.clear();
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
