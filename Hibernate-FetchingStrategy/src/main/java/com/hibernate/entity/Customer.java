package com.hibernate.entity;
import java.util.*;

/**
 * example of the ManyToOne, one customer has got many orders
 * Two Way(Customer has the reference of the Order)
 * @author Woo
 *
 */
public class Customer {
	private Integer CustID;
	private String CustName;
	private Set<Order> Orders = new HashSet<Order>();
	
	public Integer getCustID() {
		return CustID;
	}
	public void setCustID(Integer custID) {
		CustID = custID;
	}
	
	public String getCustName() {
		return CustName;
	}
	public void setCustName(String custName) {
		CustName = custName;
	}
	
	public Set<Order> getOrders() {
		return Orders;
	}
	public void setOrders(Set<Order> orders) {
		Orders = orders;
	}
	
	public Customer(Integer custID, String custName) {
		super();
		CustID = custID;
		CustName = custName;
	}
	
	public Customer() {}
}
