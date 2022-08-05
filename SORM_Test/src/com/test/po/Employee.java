package com.test.po;
import java.sql.*;
import java.util.*;

public class Employee {

	private Double Salary;
	private java.sql.Date CheckInDate;
	private Integer Bonus;
	private Integer ID;
	private Integer DeptID;
	private Integer Age;
	private String Name;


	public Double getSalary() {
		return Salary;
	}
	public java.sql.Date getCheckInDate() {
		return CheckInDate;
	}
	public Integer getBonus() {
		return Bonus;
	}
	public Integer getID() {
		return ID;
	}
	public Integer getDeptID() {
		return DeptID;
	}
	public Integer getAge() {
		return Age;
	}
	public String getName() {
		return Name;
	}
	public void setSalary(Double Salary) {
		this.Salary = Salary;
	}
	public void setCheckInDate(java.sql.Date CheckInDate) {
		this.CheckInDate = CheckInDate;
	}
	public void setBonus(Integer Bonus) {
		this.Bonus = Bonus;
	}
	public void setID(Integer ID) {
		this.ID = ID;
	}
	public void setDeptID(Integer DeptID) {
		this.DeptID = DeptID;
	}
	public void setAge(Integer Age) {
		this.Age = Age;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public Employee() {}
}
