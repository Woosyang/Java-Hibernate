package com.hibernate.entity;
import java.util.*;

public class Department {
	private int DeptID;
	private String DeptName;
	private Set<Employee> Employees = new HashSet<>();
	
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
	
	public Set<Employee> getEmployees() {
		return Employees;
	}

	public void setEmployees(Set<Employee> employees) {
		Employees = employees;
	}
	
	public Department() {}

	public Department(int deptID, String deptName, Set<Employee> employees) {
		super();
		DeptID = deptID;
		DeptName = deptName;
		Employees = employees;
	}

	@Override
	public String toString() {
		return "[DeptID=" + DeptID + ", DeptName=" + DeptName + "]";
	}

}
