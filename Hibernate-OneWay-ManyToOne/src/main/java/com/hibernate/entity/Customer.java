package com.hibernate.entity;

/**
 * example of the ManyToOne, one customer has got many orders
 * One Way(Customer doesn't have the reference of the Order)
 * @author Woo
 *
 */
public class Customer {
	private Integer CustID;
	private String CustName;
	
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
	
	public Customer(Integer custID, String custName) {
		super();
		CustID = custID;
		CustName = custName;
	}
	
	public Customer() {}
}
