package com.hibernate.entity;

/**
 * ManyToOne example 
 * One Way: Order has the reference of the Customer
 * @author Woo
 *
 */
public class Order {
	private Integer orderID;
	private String orderName;
	private Customer customer;
	
	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public Order(Integer orderID, String orderName, Customer customer) {
		super();
		this.orderID = orderID;
		this.orderName = orderName;
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order() {}
}
