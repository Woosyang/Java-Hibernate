package com.hibernate.hello;
import java.sql.Date;
import javax.persistence.*;

/**
 * java bean for the hibernate.cfg.xml
 * @author Woo
 *
 */
// @Entity specifies that the class is an entity and is mapped to a database table.
@Entity
// @Table specifies the name of the database table to be used for mapping.
/*
 * @GeneratedValue will automatically generate values for that using an internal sequence
 * , we don't need to set it manually
 * @Column is used to specify column mappings
 * @Transient tells hibernate not to save this field
 * @Temporal over a date field tells hibernate the format in which the date needs to be saved
 * @Lob tells hibernate it is a large object(Clob, Blob)
 */
/*
 * feature of the persistence object:
 * 1. has a default constructor -> reflection by using constructor.newInstance()
 * 2. has an identifier property -> map it as the primary key in the database
 * 3. has the setter and getter for each field 
 * 4. not use the final, if use the final to describe or a final class, hibernate can't
 * 	  generate the proxy(CGLIB) for the persistence object
 * 5. override equals and hashCode method, if you want to put the instance of the persistence
 	  class in to the Set
 */
@Table(name = "News")
public class News {
	@Id // primary id
	private Integer id;
	private String title;
	private String author;
	private Date date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public News(String title, String author, Date date) {
		super();
		this.title = title;
		this.author = author;
		this.date = date;
	}
	
	public News() {}
	
	@Override
	public String toString() {
		return "News [id = " + id + ", title = " + title + ", author = " + author + ", date = " + date + "]";
	}
}
