package com.hibernate.query;
import java.util.*;
import javax.persistence.*;
import javax.persistence.criteria.*;

import org.hibernate.*;
import org.hibernate.Query;
import org.hibernate.cfg.*;
import org.junit.*;
import com.hibernate.entity.*;

/**
 * Test Hibernate HQL & QBC Queries
 * @author Woo
 *
 */

/*
 * Hibernate provides multiple ways for retrieving an Object from a table:
 * 
 * 1. Object Graph Navigation Language: fetch other object according to the object which 
 *                                      is loaded(getter() & setter()).
 *                                      
 * 2. OID Query: fetch object according to the OID primary key (load() & get()).
 * 
 * 3. HQL Query: similar to SQL but it is object - oriented, it use Session.createQuery()
 *               to create a query Object and this Object contains a HQL select query which 
 *               can have multiple named parameters. Those parameters can be set up dynamically
 *               like PreparedStatement in JDBC(method chaining)
 * 
 * 4. QBC Query: use Query By Criteria API to fetch the object, this API encapsulate the
 *               query based on String format providing a more object-oriented interface.
 * 
 * 5. SQL Query: use local SQL to retrieve the object.              
 */
public class TestHQLAndQBC {
	private SessionFactory sessionfactory;
	private Session session;
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
	 * HQL VS SQL:
	 * HQL: it is object-oriented, Hibernate will analyze HQL according to the hbm.xml file
	 *      and translate the HQL to SQL. The targets for HQL are the class and class's fields
	 *      of the domain model
	 *      
	 * SQL: it is hooked with data base. The targets for SQL are tables and table's columns.     
	 */
	
	// If not set the fetching strategy inside HQL, HQL will use the fetching strategy inside
	// hbm.xml mapping file. HQL will ignore the urgent left outer join fetching strategy setting
	// inside mapping file, if we want to use the "urgent left outer join", we have to set up this
	// in HQL query. Once we have fetching strategy inside HQL, it will overwrite the fetching strategy
	// setting inside hbm.xml mapping file.
	
	@SuppressWarnings("unchecked")
	@Test
	public void testHQLWithPlaceHolderAndNamedParameters() {
		
		// Use place holders "?" HQL
		// 1. create a query object
		String Hql = "From Employee e where e.Salary > ?0 AND e.Name Like ?1 AND e.Dept = ?2";
		// Query is deprecated
		TypedQuery<Employee> query = session.createQuery(Hql); 
		
		// 2. bind the parameters, chaining method, setParameterXXX()
		Department Dept = new Department();
		Dept.setDeptID(1);
		query.setParameter(0, 4000).setParameter(1, "%a%") // like "a" -> contains "a"
		     .setParameter(2, Dept);
		
		// 3. execute the query
		List<Employee> emps = query.getResultList();
		System.out.println("Place Holder: " + emps.size());
		
		// Use named parameters HQL
		Hql =  "From Employee e where e.Salary > :sal AND e.Name Like : nam Order By e.Salary";
		query = session.createQuery(Hql);
		
		// bind the parameter
		query.setParameter("sal", 4000).setParameter("nam", "%a%");
		
		emps = query.getResultList();
		System.out.println("Named Parameters: " + emps.size());
		
	}
	
	/*
	 * setFirstResult(int parameter): set the result in query which is starting from records,
	 *                                int parameter is the index which means the location of
	 *                                the records. Example: int parameter = 0 means fetching
	 *                                the object at first position.
	 *                                
	 * setMaxResults(int parameter): set the maximum result in query, int parameter is the max
	 *                               fetching amount per page.                              
	 */
	@Test
	public void testHQLPagination() {
		
		String Hql = "From Employee";
		TypedQuery<Employee> query = session.createQuery(Hql);
		
		int pageNum = 2; // how many pages
		int recordNum = 3; // how many records per page
		
		List<Employee> employees = query.setFirstResult((pageNum - 1) * recordNum) // start at the 1st of the second page
				                        .setMaxResults(recordNum).getResultList(); // 3 records per page
		
		System.out.println(employees); // 104, 105, 106
		System.out.println(employees.size());
		
	}
	
	
	/*
	 * customize the query in side hbm.xml mapping file, use <query> to define the HQL
	 * <query> has the same level with <class>
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testHQLNamedQuery() {
		
		// used with <query name = "salEmps"><![CDATA[From Employee e Where e.Salary > : minSal And e.Salary < : maxSal]]></query>
		TypedQuery<Employee> query = session.getNamedQuery("salEmps"); // name = "salEmps"
		
		List<Employee> employees = query.setParameter("minSal", 3000) // e.Salary > : minSal
				                        .setParameter("maxSal", 5000) // e.Salary < : maxSal
				                        .getResultList();
		
		// if you want to fetch different records, just change the query inside CDATA[]
		System.out.println(employees.size());
		
	}
	
	
	// projection: retrieve a subset of the fields of the entity, used with "Select" keyword
	@SuppressWarnings("unchecked")
	@Test
	public void testHQLProjection() {
		
		String Hql = "Select e.Name, e.Salary From Employee e Where e.Dept = : dept";
		TypedQuery<Object []> query01 = session.createQuery(Hql); 
		// use Object[] not employee because we only got partial fields from the Employee 
		
		Department dept = new Department();
		dept.setDeptID(2);
		List<Object[]> re01 =  query01.setParameter("dept", dept).getResultList();
		
		// the result it return is a List of object array, each factor in the object array
		// means a single record fetched from the table
		System.out.println(re01); 
		
		for (Object[] objs: re01) { // processing Array is not a good way
			System.out.println(Arrays.asList(objs));
		}
		
		// we can customize the constructor in the corresponding entity to cover the HQL
		// query which containing "new Entity(parameter1 , parameter2, ..., parameterN)"
		Hql = "Select new Employee(e.Name, e.Salary, e.Dept) From Employee e Where e.Dept = :dept";
		
		dept = new Department();
		dept.setDeptID(3);
		
		TypedQuery<Employee> query02 = session.createQuery(Hql);
		List<Employee> re02 = query02.setParameter("dept", dept).getResultList();
		
		for (Employee e: re02) { 
			System.out.println(e.getEID() + ", " + e.getName() + ", " + e.getSalary() + ", " + e.getDept());
		}
		
	}
	
	
	// query report: count and group data, HQL will use "GroupBy" keyword to group data
	//               and "Having" keyword to fetch the specific data like SQL
	
	@SuppressWarnings("unchecked")
	@Test
	public void testHQLQueryReport() {
		
		// find the range for the Salary in each Department
		String Hql = "Select min(e.Salary), max(e.Salary) From Employee e Group by e.Dept Having min(Salary) > : minSal ";
		// will fetch 2 different objects: Employee and Department which can be combined as an array
		TypedQuery<Object []> query = session.createQuery(Hql).setParameter("minSal", 3500);
		List<Object []> re = query.getResultList(); // the result it returns is a List of object array
		// hard to customize the constructor with min() & max() for entity, we only use 
		// List<Object[]> to return the record.
		// HQL can also use count(), sum() and avg()
		
		for (Object[] objs: re) { 
			System.out.println(Arrays.asList(objs));
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testHQLQueryLeftJoin() {
		
		// left join fetch(recommended): urgent left outer join fetching strategy 
		//                               which can reduce the number of select statements (n + 1) query
		
		                               // be careful to the lower and upper case 
		String Hql = "From Department d Left Join Fetch d.Employees"; // d.Employees maps to the field name inside the entity
		// the query above will cause duplicate records for department
		TypedQuery<Department> query = session.createQuery(Hql);
		
		// by using "left join fetch" in HQL, getResultList() will store the reference of each
		// Department and each Set<Employee> related to the corresponding Department will be 
		// initialized
		List<Department> Depts = query.getResultList();
		System.out.println("How many Departments the Query fetches: " + Depts.size()); // containing duplicate records
		
		// we can use some collections to remove the duplicate records to get the actual
		// size of department: use Set to get unique records and then use List to cast it
		Depts = new ArrayList<>(new LinkedHashSet<>(Depts));
		System.out.println("Actual Department Size: " + Depts.size()); // 6
		
		Hql = "Select Distinct d From Department d Left Join Fetch d.Employees";
		// use "Distinct" keyword to filter out duplicate records
		query = session.createQuery(Hql);
		Depts = query.getResultList();
		System.out.println("How many Departments using Distinct Query fetches: " + Depts.size()); // actually we only have 6 departments

		for (Department d : Depts) {
			System.out.println(d.getDeptName() + " - " + d.getEmployees().size());
		}
		
		
		// left join : left outer join fetching strategy, use left join without 'fetch' inside HQL
		
		// 1. using left join can make getResultList() store a list of object[]
		// 2. left join in HQL will comply with the fetching strategy(eg, lazy = true) in certain 
		// Entity hbm.xml mapping file
		
		Hql = "From Department d Left Join d.Employees";
		// we use HQL to fetch 2 different objects(Department & Employee), then we should use
		// an object[] to receive each records
		TypedQuery<Object[]> queryNew = session.createQuery(Hql);
		
		System.out.println("-------------------------------------");
		List<Object[]> re = queryNew.getResultList();
		System.out.println(re); // list of the Object[]
		
		for (Object[] objs : re) {
			// will have duplicate records and can't use collections to remove them
			// because some factors inside the array can be unique even the Department is the
			// same(Employee not the same)
			System.out.println(Arrays.asList(objs)); // Array.asList() prints array
		}
		
		// do not use List<Object[]> query, use List<Department> instead
		// use "Distinct" keyword to remove the duplicates
		Hql = "Select Distinct d From Department d Left Join d.Employees";
	    query = session.createQuery(Hql);
	    
	    // Set<Employee> of each Department will not be initialized, will generate extra SQL queries
	    Depts = query.getResultList(); 
	    System.out.println("Using Left Join Query: " + Depts.size());
	    
	    for (Department d : Depts) {
	    	System.out.println(d.getDeptName() + " - " + d.getEmployees().size());
	    }
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testHQLQueryInnerJoin() {
		
		// Inner also have inner join fetch & inner join, they have the same mechanism 
		// like Left in HQL
		String Hql = "From Department d Left Join Fetch d.Employees"; 
		TypedQuery<Department> query01 = session.createQuery(Hql);
		
		// will return a List only contains the intersection between Department and Employee
		List<Department> Depts = query01.getResultList(); 
		System.out.println("How many Departments the Query fetches: " + Depts.size());
		
		// fetch the record from Employee to Department, use inner because some employee does
		// not have assoiated department inside table
		
		Hql = "From Employee e Inner Join Fetch e.Dept"; // will initialize related Dept immediately 
		TypedQuery<Employee> query02 = session.createQuery(Hql);
		
		List<Employee> re = query02.getResultList();
		System.out.println(re.size());
		
		for (Employee e: re) {
			System.out.println(e.getName() + " - " + e.getDept().getDeptName());
		}
		
		Hql = "From Employee e Inner Join e.Dept"; // associated Dept will not be initialized 
		query02 = session.createQuery(Hql);
		
		re = query02.getResultList();
		
		for (Employee e: re) {
			System.out.println(e.getName() + " - " + e.getDept().getDeptName());
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testQBC() { // no longer use Criteria and Restriction
		
		// 1. create a CriteriaQuery Object and Root Object
		CriteriaQuery<Employee> cri01 = criteriaBuilder.createQuery(Employee.class);
		CriteriaQuery<Employee> cri02 = criteriaBuilder.createQuery(Employee.class);
		CriteriaQuery<Integer> cri03 = criteriaBuilder.createQuery(Integer.class);
		Root<Employee> root01 = cri01.from(Employee.class);
		Root<Employee> root02 = cri02.from(Employee.class);
		Root<Employee> root03 = cri03.from(Employee.class);
		
		
		// 2. customize the query requirements. For QBC, we will use CriteriaBuilder 
		//    and Root to display the requirements of the query.
		Predicate dept = criteriaBuilder.equal(root01.get("Dept"), 5);
		Predicate sal = criteriaBuilder.gt(root01.<Integer> get("Salary"), 7500);
		
		// 3. use where() for the query requirement
		// AND
		cri01.where(criteriaBuilder.and(dept, sal)); // customize the requirements dynamically
		
		// use createQuery() to wrap the criteria
		Employee emp = session.createQuery(cri01).uniqueResult();
		
		System.out.println(emp.getName() + " - " + emp.getSalary()); // Josh - 8000
		
		System.out.println("-----------------------Or------------------------");
		// OR
		sal = criteriaBuilder.gt(root01.<Integer> get("Salary"), 7500);
		// will return the record which have deptId = 5 or salary > 7500(maybe from different department)
		cri01.where(criteriaBuilder.or(dept, sal)); 
		List<Employee> re = session.createQuery(cri01).getResultList();
		System.out.println(re.size());
		
		for (Employee e : re) {
			System.out.println(e.getName() + " - " + e.getSalary());
		}
		
		System.out.println("-----------------------Sorting------------------------");
		
		// 4. use orderBy() to sort the records (descending or ascending)
		cri02.orderBy(criteriaBuilder.desc(root02.<Integer> get("Salary"))); // descending
		re = session.createQuery(cri02).getResultList();
		
		for (Employee e : re) {
			System.out.println(e.getName() + " - " + e.getSalary());
		}
		
		cri02.orderBy(criteriaBuilder.asc(root02.<Integer> get("Salary"))); // ascending
		re = session.createQuery(cri02).getResultList();
		
		System.out.println("ASC by Salary: ");
		for (Employee e : re) {
			System.out.println(e.getName() + " - " + e.getSalary());
		}
		
		// 5. find the record of specific max or min value inside table
		
		System.out.println("-----------------------Max or Min------------------------");
		
		Expression<Integer> salMax = criteriaBuilder.max(root03.<Integer> get("Salary"));
		cri03.select(salMax);
		int maxRe = session.createQuery(cri03).getSingleResult();
		System.out.println(maxRe + " - " + "Sal");
		
		Expression<Integer> salMin = criteriaBuilder.min(root03.<Integer> get("Salary"));
		cri03.select(salMin);
		int minRe = session.createQuery(cri03).getSingleResult();
		System.out.println(minRe + " - " + "Sal");
		
		// 6. pagination with customized amount of pages and records' number per page
		System.out.println("-----------------------Pagination------------------------");
		int pageSize = 4;
		int recordSize = 4; // 5 records per page
		int recordStart = (pageSize - 1) * recordSize; // start at last page
		
		re = session.createQuery(cri02).setFirstResult(recordStart).setMaxResults(recordSize).getResultList();
		
		for (Employee e : re) {
			System.out.println(e.getEID());
		}
				
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testNativeSQLAndHQLUpdate() {
		
		// use HQL to update the table, take delete as example
		String Hql = "Delete From Employee e Where e.EID = :eid";
		session.createQuery(Hql).setParameter("eid", 115).executeUpdate(); // 115 will be removed
		
		// SQL inserting data
		String Sql = "Insert Into Employee Values(?, ?, ?, ?)";
		TypedQuery<Employee> query = session.createSQLQuery(Sql);
		
		query.setParameter(1, 115).setParameter(2, 8800)
		     .setParameter(3, "Mark").setParameter(4, 5).executeUpdate();
		
	}
	
	
	@After 
	public void destroy() {
		// System.out.println("destroy");
		transaction.commit();
		session.close();
		sessionfactory.close();
	}
}
