package com.hibernate.inheritance;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.junit.*;
import com.hibernate.entity.*;
import java.util.*;

/**
 * Test Case of Hibernate Inheritance Mapping
 * @author Woo
 *
 */
public class TestInheritance {
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
	
	// test the save method for mapping of the inheritance
	@Test
	public void testSubClassSave() {
		// used with class Student and People
		// used with <subclass> in People.hbm.xml
		/*
		 * map each entity of the domain model to the single one table, which means 
		 * that you don’t need to consider the relationship of the inheritance and 
		 * polymorphism of the domain model for the relational data model
		 */
		// parent class and subclass share the same table
		// add one more column as the discriminator to tell the value of each entity
		// use discriminator-value in both <class> and <subclass> to define the value
		// all fields defined by subclasses cannot have not-null constraints
		// because the parent class may not have these fields(cause the collision in table)
		People p = new People();
		p.setAge(20);
		p.setName("PP");
		
		Student s = new Student();
		s.setAge(18);
		s.setName("SS");
 		s.setSchool("Yale"); // people doesn't have
 		
 		session.save(p);
 		session.save(s);
 		
 		// disadvantage: if the subclass have too many fields, it will make the
 		// 				 table have too many columns which are irrelevant to the
 	    //               parent class (no not-null constraints for subclass)
	}
	
	@Test
	public void testSubClassQuery() {
		// whether we search the people or the student, we always use the same table
		List<People> peoples = session.createQuery("From People").list();
		System.out.println(peoples.size()); // 2, includes the student
		
		List<Student> students = session.createQuery("From Student").list();
		System.out.println(students.size()); // 1
	}
	
	@Test
	public void testJoinedSubclassSave() {
		// used with class Animal and Dog
		// used with <joined-subclass> in Animal.hbm.xml
		/*
		 * for the inheritance, subclass and parent class share the same table which
		 * means we need to add extra column in the table to tell the extra field of
		 * the subclass
		 */
		
		// parent class and subclass share the same table for the common fields
		// the extra fields of the subclass are saved in the another tables and
		// the foreign key of the shared table is the primary key of the another table
		Animal a = new Animal();
		a.setName("AA");
		
		Dog d = new Dog();
		d.setName("DD");
		d.setFood("Bones");
		
		session.save(a);
		session.save(d);
		
		// disadvantage: for the insert query of the subclass, it will generate at
		//               least 2 insert query(parent and its own table)
	}
	
	@Test
	public void testJoinedSubclassDelete() {
		Dog d = (Dog) session.get(Animal.class, 2);
		// the record of the subclass will be also deleted
		session.delete(d); // will use the left outer join to delete it
	}
	
	@Test
	public void testJoinedSubclassQuery() {
		List<Animal> animals = session.createQuery("From Animal").list();
		System.out.println(animals.size()); // use left-join outer to search
		
		List<Dog> dogs = session.createQuery("From Dog").list();
		System.out.println(dogs.size()); // use inner-join to search
	}
	
	@Test
	public void testUnionSubclassSave() {
		// used with class Fruit and Grape
		// used with <union-subclass> in Fruit.hbm.xml
		/*
		 * Each entity of the domain model mapping to a certain table, and the relationship
		 * of the inheritance between table is described by the foreign key in the relational 
		 * data model. Which means establishing the tables in the database according to the 
		 * structure of the domain model, and the inheritance relationship between the tables 
		 * through foreign keys
		 */
		
		// parent class and subclass use different table but the common fields will 
		// be saved in both parent class and subclass
		// subclass can have not-null constraints
		// the record of subclass only be saved in its own table not its parent's table
		// for the generator, we can't not use the native, because subclass and parent
		// class use the same primary key and the primary key should be consecutive
		Fruit f = new Fruit();
		f.setName("FF");
		
		Grape g = new Grape();
		g.setName("GG");
		g.setColor("Purple");
		
		// it will generate 2 insert queries
		session.save(f);
		session.save(g);
		
		// disadvantage: the common fields of the parent class and the subclass
		//				 will be saved twice(both in parent class and subclass)
	}
	
	@Test
	public void testUnionSubclassQuery() {
		List<Fruit> fruits = session.createQuery("From Fruit").list();
		// need to merge the subclass table into parent class table first
		// then do the search query, will be slow
		System.out.println(fruits.size());
		
		List<Grape> grapes = session.createQuery("From Grape").list();
		// only do the select query for the subclass table
		System.out.println(grapes.size());
	}
	
	@Test
	public void testUnionSubclassUpdate() {
		// if we want to update the information of the parent table, we need to
		// update the information of the subclass also, because subclass and parent
		// class save the common information(the one parent and children both have)
		// it will generate extra SQL queries and be slow
		String hql = "Update Fruit f Set f.Name = 'FO'";
		session.createQuery(hql).executeUpdate();
	}
	
	@After 
	public void destroy() {
		// System.out.println("destroy");
		transaction.commit();
		session.close();
		sessionfactory.close();
	}
}
