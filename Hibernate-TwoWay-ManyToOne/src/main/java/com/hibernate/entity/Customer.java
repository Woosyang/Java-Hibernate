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
	
	// Os needs to be initialized, otherwise it will cause Null pointer exception
	// declare the Collection should use interface type(Set, List, Map), not their
	// realization. Interface type will match up with the Hibernate built-in Collection type 
	private Set<Order> Os = new HashSet<Order>();
	
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
	
	public Set<Order> getOs() {
		return Os;
	}
	public void setOs(Set<Order> os) {
		Os = os;
	}
	
	public Customer(Integer custID, String custName) {
		super();
		CustID = custID;
		CustName = custName;
	}
	
	public Customer() {}
}
