package com.hibernate.entity;

public class Employee {
	private int EID;
	private int Salary;
	private String Name;
	private Department Dept;
	
	public int getEID() {
		return EID;
	}

	
	public void setEID(int eID) {
		EID = eID;
	}

	public int getSalary() {
		return Salary;
	}

	public void setSalary(int salary) {
		Salary = salary;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Department getDept() {
		return Dept;
	}
	
	public void setDept(Department dept) {
		Dept = dept;
	}
	
	public Employee() {}
	
	public Employee(int eID, int salary, String name, Department dept) {
		super();
		EID = eID;
		Salary = salary;
		Name = name;
		Dept = dept;
	}
    
	// need to be consistent with the HQL "new Employee(e.Name, e.Salary, e.Dept)
	// the order of the parameters really matter
	public Employee(String name, int salary, Department dept) {
		super();
		Salary = salary;
		Name = name;
		Dept = dept;
	}


	@Override
	public String toString() {
		return "Employee [EID=" + EID + ", Salary=" + Salary + ", Name=" + Name + ", Dept=" + Dept + "]";
	}
	
}
