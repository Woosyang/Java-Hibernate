package com.hibernate.hello;
import java.sql.Date;
import org.hibernate.*;
import org.hibernate.cfg.*;

/**
 * Simple Hibernate Example with annotation of JPA
 * <mapping class="com.hibernate.hello.News"/> in hibernate.cfg.xml
 */
public class App {
	public static void main( String[] args ) {
        System.out.println( "Project Started..." );
        Configuration cfg = new Configuration(); // use hibernate.properties
        cfg.configure("hibernate.cfg.xml");
        // 1. create a session factory object
        SessionFactory factory = cfg.buildSessionFactory();
        // creating news
        News news = new News();
        news.setId(2);
        news.setAuthor("Bell");
        news.setTitle("C++");
        news.setDate(new Date(System.currentTimeMillis()));
        // 2. create a session object
        Session session = factory.openSession();
        // 3. begin the transaction
        Transaction tx = session.beginTransaction();
        // 4. execute the save action
        session.save(news);
        // 5. commit the transaction
        // session.getTransaction().commit();
        tx.commit();
        // 6. close the session
        session.close();
        
        // System.out.println(factory);
        // System.out.println(factory.isClosed());
    }
}
