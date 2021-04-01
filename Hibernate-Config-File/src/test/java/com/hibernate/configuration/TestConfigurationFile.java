package com.hibernate.configuration;
import java.sql.Clob;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.io.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.junit.*;
import com.hibernate.entity.*;
 
/**
 * Test Case of Hibernate Configuration File
 * Hibernate use OID to establish the relationship between the record inside table and
 * the object inside memory. OID corresponds to the primary key of the table.
 * Hibernate use IdentifierGenerator to assign the value to the primary key and it use
 * surrogate primary key inside table and the its type is int
 * <id> setup the OID and <generator> setup the identifier generator
 * In java, java.util.Date and java.util.Calendar can display Time and Date respectively.
 * In JDBC, there are 3 child classes of java.Util.Date: java.sql.Date, java.sql.Time and
 * java.sql.Timestamp. This 3 maps to DATE, TIME and TIMESTAMP respectively of the SQL.
 * Because java.util.Date is the parent class, it can map to DATE, TIME and TIMESTAMP.
 * The type of the Date for the persistent object should be set up as java.util.Date, and 
 * we can use "type" inside <property> to map the mentioned three object accurately
 * eg: <property name="date" type="timestamp">
 *     <property name="date" type="time">
 *     <property name="date" type="date">
 *     "timestamp", "time", "date" is the data type from the Hibernate
 * @author Woo
 *
 */
public class TestConfigurationFile {
	private SessionFactory sessionfactory;
	private Session session;
	private Transaction transaction;
	
	@Before
	public void init() {
		Configuration configuration = new Configuration().configure();
		sessionfactory = configuration.buildSessionFactory();
		session = sessionfactory.openSession();
		transaction = session.beginTransaction();
	}
	
	@Test
	public void Test() {
		News news = new News("Java", "Sun", new Date(System.currentTimeMillis()));
		session.save(news);
	}
	
	/*
	 * used with <class name="News" table="Info" dynamic-update="true">
	 * so that it can only send a certain update SQL query based on the 
	 * field information which has been updated
	 */
	@Test
	public void testDynamicUpdate() {
		News news = (News) session.get(News.class, 1);
		news.setAuthor("Bao");
	}
	
	/*
	 * used with <class name="News" table="Info" dynamic-insert="true">
	 * so that it can only send a certain insert SQL based the field information which
	 * is not null 
	 */
	@Test
	public void testDynamicInsert() {
		News news = new News();
		news.setAuthor("BellLab");
		news.setTitle("C++");
		session.save(news);
	}
	
	@Test // increment
	public void testIdGenerator01() {
		// used with <generator class="increment" /> in <id>
		// Hibernate will read the max value of the primary key in the table
		// when it insert a record into the table, the OID will increase based
		// on the max value.
		// In the old time, increment may cause the concurrent problem and use
		// <generator class="hilo"/> to solve the problem
		// hilo can convert the OID into different value in each SQL query to
		// avoid the concurrent problem
		News news = new News("OOP", "Java", new Date(System.currentTimeMillis()));
		session.save(news);
	}
	
	@Test // identity
	public void testIdGenerator02() {
		// used with <generator class="identity" /> in <id>
		// this generator is created by the database and it requires the primary
		// key to be auto-increment
		// no id as the parameter inside the SQL query
		News news = new News("A", "Java", new Date(System.currentTimeMillis()));
		session.save(news);
	}
	
	@Test
	public void testProperty01() {
		// tested with 
		// <property name="title" type="string" column="TITLE" unique="true" update="false"
		// index="info_index" length="3000">
		// </property>
		// unique: whether add the unique constraint to the column of the data mapped
		//         to the certain field
		// update: whether the column can be changed
		// index: designate a string as the index for a certain column of the table,
		//        by which mapped by its corresponding field, to speed up the efficiency
		//        of the select query
		// length: setup the String length
		News news = new News("B", "C++", new Date(System.currentTimeMillis()));
		news.setTitle("O"); // can't be changed
		session.save(news);
	}
	
	@Test // derived field
	public void testProperty02() throws Exception {
		// test with 
		// <property name="desc" formula="(select concat(author, ': ', title) from Info n where n.id = id)"></property>
		// formula: create a certain SQL expression, Hibernate will figure out the 
		// derived value according to the SQL expression, "formula = ()"
		// the column name and the table name of SQL query should be consistent with 
		// the names of the table
		News news = (News)session.get(News.class, 1);
		System.out.println(news.getDate()); // use with java.util.Date
		System.out.println(news.getDate().getClass()); // type = "timestamp" -> java.sql.Timestamp
		System.out.println(news.getDes()); // "Java: A"
		
		// test with 
		// <property name="image" type="blob">
	    //    <column name="Image" sql-type="mediumblob"></column>
	    // </property>
		Blob Img = news.getImage();
		InputStream Is = Img.getBinaryStream();
		System.out.println(Is.available());
	}
	
	@Test
	public void testClobAndBlob() throws Exception {
		News news = new News();
		news.setAuthor("Woo");
		news.setDate(new Date(System.currentTimeMillis()));
		news.setDes("A");
		news.setTitle("Full-Stack");
		Clob text = Hibernate.getLobCreator(session).createClob("Interview");
		news.setContent(text);
		InputStream Is = new FileInputStream("Cat.jfif");
		Blob pic = Hibernate.getLobCreator(session).createBlob(Is, Is.available());
		news.setImage(pic);
		session.save(news);
	}
	
	@Test // <component>, Worker.hbm.xml
	public void testComponent() {
		Worker W = new Worker();
		// Pay is the domain model, it can refine the persistent object and simplify
		// the code inside the persistent class
		// the field's type of the persistent object can be divided into 2:
		// 1. Value: there is no OID, can't be persisted separately, its life cycle
		//           depends on the life cycle of the persistent object 
		// 2. Entity: has its own OID and life cycle, can be persisted separately
		Pay P = new Pay();
		P.setPname("Brandi");
		P.setAmount(100);
		W.setName("Love");
		W.setPay(P);
		session.save(W);
	}
	
	@After
	public void destroy() {
		transaction.commit();
		session.close();
		sessionfactory.close();
	}
}
