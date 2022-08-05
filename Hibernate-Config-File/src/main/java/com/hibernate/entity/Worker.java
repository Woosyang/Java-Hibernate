package com.hibernate.entity;
import javax.persistence.*;

/**
 * Used with the domain model class Pay
 * @author Woo
 *
 */
public class Worker {
	private Integer id;
	private String name;
	private Pay pay;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Pay getPay() {
		return pay;
	}
	public void setPay(Pay pay) {
		this.pay = pay;
	}
	
	public Worker(Integer id, String name, Pay pay) {
		super();
		this.id = id;
		this.name = name;
		this.pay = pay;
	}
	
	public Worker() {}
}
