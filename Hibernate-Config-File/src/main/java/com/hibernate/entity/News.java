package com.hibernate.entity;
import java.sql.Blob;
import java.sql.Clob;
// import java.sql.Date;
import java.util.Date;
/**
 * java bean for the hibernate.cfg.xml
 * @author Woo
 *
 */
public class News {
	private Integer id;
	private String title;
	private String author;
	private Date date;
	// des = title : author, derived field
	private String des; // use the formula inside <property>
	private Clob Content; // CLOB
	private Blob Image; // BLOB
	
	public Clob getContent() {
		return Content;
	}
	public void setContent(Clob content) {
		Content = content;
	}
	
	public Blob getImage() {
		return Image;
	}
	public void setImage(Blob image) {
		Image = image;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
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
