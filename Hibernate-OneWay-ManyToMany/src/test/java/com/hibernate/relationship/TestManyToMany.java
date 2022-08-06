package com.hibernate.relationship;
import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.junit.*;
import com.hibernate.entity.*;

/**
 * Test Case of Hibernate ManyToMany
 * @author Woo
 *
 */
public class TestManyToMany {
	/*
	 * It is the One-Way Many-To-Many, need to use <set> with <key> 
	 * in the Category.hbm.xml, it needs to use the foreign key of Category
	 * table as the column inside the intermediate table(Category_Item).
	 * It needs to use <many-to-many> inside Category.hbm.xml, add
	 * the feature class="Item" for mapping and add the feature column as the 
	 * foreign key(I_ID) of the table Item inside the intermediate table.
	 */
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
	public void testManyToManySave() {
		Category c01 = new Category();
		c01.setName("C-AA");
		
		Category c02 = new Category();
		c02.setName("C-BB");
		
		Item i01 = new Item();
		i01.setName("I-AA");
		Item i02 = new Item();
		i02.setName("I-BB");
		
		// set up the mapping relationship
		c01.getItems().add(i01);
		c01.getItems().add(i02);
		
		c02.getItems().add(i01);
		c02.getItems().add(i02);
		
		// save
		session.save(c01);
		session.save(c02);
		
		session.save(i01);
		session.save(i02);
	}
	
	@Test
	public void testGet() {
		// similar with Many-To-One, use lazy loading for Item
		Category category = (Category) session.get(Category.class, 1);
		System.out.println(category.getName());
		
		// need to use the intermediate table, use inner join
		Set<Item> items = category.getItems();
		System.out.println(items.size());
	}
	
	@Test
	public void testUpdate() {
		// similar with Many-To-One, One-To-One
		Item i01 = (Item) session.get(Item.class, 1);
		i01.setName("I-DD");
	}
	
	@Test
	public void testDelete() {
		/* 
		// can not delete the item, because Category is maintaining the relationship 
		// with the Item, need to delete the Category first, similar with Many-To-One(2way)
		Item i = (Item) session.get(Item.class, 1);
		session.delete(i);
		*/
		// the rows of the intermediate table will be decrease, table Items remain the
		// same but rows of the table Categories will be removed
		Category c = (Category) session.get(Category.class, 1);
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
