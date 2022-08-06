package com.hibernate.entity;

public class Department {
	private int DeptID;
	private String DeptName;
	private Manager Mgr;
	
	public int getDeptID() {
		return DeptID;
	}
	
	public void setDeptID(int deptID) {
		DeptID = deptID;
	}
	
	public String getDeptName() {
		return DeptName;
	}
	
	public void setDeptName(String deptName) {
		DeptName = deptName;
	}
	
	public Manager getMgr() {
		return Mgr;
	}
	
	public void setMgr(Manager mgr) {
		Mgr = mgr;
	}

	public Department() {}

	public Department(int deptID, String deptName, Manager mgr) {
		super();
		DeptID = deptID;
		DeptName = deptName;
		Mgr = mgr;
	}
}
