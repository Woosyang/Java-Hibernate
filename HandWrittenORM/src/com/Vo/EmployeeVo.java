package com.Vo;
/**
 * Used with MySqlQuery to test the complicated Sql Query
 * eg: select e.ID, e.Name, e.Age, Bonus + Salary 'Income', d.name 'Departmentname' 
 * 	   from Employee e join Department d on e.DeptID = d.ID;
 * @author Woo
 *
 */
public class EmployeeVo {
	// select e.ID, e.Name, e.Age, Bonus + Salary 'Income', d.name 'DeptName', 
	// d.address 'DeptAddr' from Employee e join Department d on e.DeptID = d.ID;
	// create corresponding field to each parameter inside Sql query
	// each parameter's name in side java bean must remains the same from the Sql query 
	private Integer ID;
	private String Name;
	private Integer Age;
	private Double Income;
	private String DeptName;
	private String DeptAddr;
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Integer getAge() {
		return Age;
	}
	public void setAge(Integer age) {
		Age = age;
	}
	public Double getIncome() {
		return Income;
	}
	public void setIncome(Double income) {
		Income = income;
	}
	public String getDeptName() {
		return DeptName;
	}
	public void setDeptName(String deptName) {
		DeptName = deptName;
	}
	public String getDeptAddr() {
		return DeptAddr;
	}
	public void setDeptAddr(String deptAddr) {
		DeptAddr = deptAddr;
	}
	
	public EmployeeVo(Integer iD, String name, Integer age, Double income, String deptName, String deptAddr) {
		super();
		ID = iD;
		Name = name;
		Age = age;
		Income = income;
		DeptName = deptName;
		DeptAddr = deptAddr;
	}
	
	public EmployeeVo() {}
}
