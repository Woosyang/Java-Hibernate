package com.hibernate.entity;

public class Manager {
	private int MgrID;
	private String MgrName;
	private Department Dept;
	
	public int getMgrID() {
		return MgrID;
	}
	
	public void setMgrID(int mgrID) {
		MgrID = mgrID;
	}
	
	public String getMgrName() {
		return MgrName;
	}
	
	public void setMgrName(String mgrName) {
		MgrName = mgrName;
	}
	
	public Department getDept() {
		return Dept;
	}
	
	public void setDept(Department dept) {
		Dept = dept;
	}
	
	public Manager() {}

	public Manager(int mgrID, String mgrName, Department dept) {
		super();
		MgrID = mgrID;
		MgrName = mgrName;
		Dept = dept;
	}
}
