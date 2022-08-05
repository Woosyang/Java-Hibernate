package com.hibernate.entity;

/**
 * Data model
 * @author Woo
 *
 */
public class Pay {
	private String pname;
	private Integer amount;
	private Worker worker;
	
	// used with <parent name="worker"/>
	public Worker getWorker() {
		return worker;
	}
	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	public Pay() {}
	
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}
}
