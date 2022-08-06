package com.hibernate.relationship;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.junit.*;
import com.hibernate.entity.*;

/**
 * Test Case of Hibernate OneToOne
 * @author Woo
 *
 */
public class TestOneToOne {
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
	public void testGet() {
		/*
		 <generator class="foreign">
		 	<!-- Mgr, field name of the Department -->
			<param name="property">Mgr</param>
		 </generator>
		*/
		// department is the one using "foreign" generating strategy for its own
		// primary key and it use the label <one-to-one\> for the mapping and
		// also add the feature "constrained = true" in this label
		// correspondingly, manager is the one also using <one-to-one/>
		
		// by default, the related object uses the lazy loading
		// for example, Manager is lazy loaded.
		Department department = (Department) session.get(Department.class, 1);
		System.out.println(department.getDeptName());
		
		/*
		session.close(); // comment out the session.close() in destroy() 
		Manager manager = department.getMgr();
		// even if the session is close, we can still get the Class of Manager
		// the result is the proxy class object
		System.out.println(manager.getClass());
		System.out.println(manager.getMgrName()); // cause the LazyInitializationException
		*/
		
		// the condition of the select query is "manager.MGR_ID = department.DEPT_ID"
		Manager manager = department.getMgr();
		System.out.println(manager.getMgrName());
		
		// get the Manager directly
		// when selecting the object without foreign key, use "left outer join" and 
		// it will get the result of the related object also
		manager = (Manager) session.get(Manager.class, 2);
		// it will select the department first and then select the manager
		System.out.println(manager.getMgrName());
		System.out.println(manager.getDept().getDeptName());
	}
	
	@Test
	public void testSave() {
		/*
		 <generator class="foreign">
		 	<!-- Mgr, field name of the Department -->
			<param name="property">Mgr</param>
		 </generator>
		*/
		Department department = new Department();
		department.setDeptName("Dept-BB");
		
		Manager manager = new Manager();
		manager.setMgrName("Dept-BB");
		
		// set up the relationship
		department.setMgr(manager);
		manager.setDept(department);
		
		// save
		session.save(manager);
		session.save(department);
		
		/* 
		// the order does not matters
		// no more SQL query will be generated because the department does not 
		// have the primary key right now for the insert query
		session.save(department);
		session.save(manager);
		*/
	} 
	
	@After 
	public void destroy() {
		// System.out.println("destroy");
		transaction.commit();
		session.close();
		sessionfactory.close();
	}
}
