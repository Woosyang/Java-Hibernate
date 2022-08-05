package com.vo;
import java.sql.Date;

/**
 * Vo class, used with searchJoin()
 * @author Woo
 *
 */
public class EmployeeVo {
	// select e.ID, e.Name, Salary + Bonus 'Income', e.Age, e.CheckInDate, 
	// d.Name 'DeptName', d.Address 'DeptAddr' from Employee e join Department d on 
	// e.DeptID = d.ID;
	private Integer ID;
	private String Name;
	private Double Income;
	private Integer Age;
	private Date CheckInDate;
	private String DeptName;
	private String DeptAddr;

	public EmployeeVo() {}

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

	public Double getIncome() {
		return Income;
	}

	public void setIncome(Double income) {
		Income = income;
	}

	public Integer getAge() {
		return Age;
	}

	public void setAge(Integer age) {
		Age = age;
	}

	public Date getCheckInDate() {
		return CheckInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		CheckInDate = checkInDate;
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
}
