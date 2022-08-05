package Chapter17;
import java.util.Date;

/**
 * Java Bean, table refers to a class
 * Used With TestORM03
 * @author Woo
 *
 */
public class Employee {
	// each field matches to each column 
	private int ID;
	private String Name;
	private double Salary;
	private int Age;
	private Date CheckInDate;
	private int DeptID;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public double getSalary() {
		return Salary;
	}
	public void setSalary(double salary) {
		Salary = salary;
	}
	
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	
	public Date getCheckInDate() {
		return CheckInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		CheckInDate = checkInDate;
	}
	
	public int getDeptID() {
		return DeptID;
	}
	public void setDeptID(int deptID) {
		DeptID = deptID;
	}
	
	public Employee(int iD, String name, double salary, int age, Date checkInDate, int deptID) {
		super();
		ID = iD;
		Name = name;
		Salary = salary;
		Age = age;
		CheckInDate = checkInDate;
		DeptID = deptID;
	}
	
	public Employee(String name, double salary, int age, Date checkInDate, int deptID) {
		super();
		Name = name;
		Salary = salary;
		Age = age;
		CheckInDate = checkInDate;
		DeptID = deptID;
	}
	
	public Employee(String name, double salary, int age) {
		super();
		Name = name;
		Salary = salary;
		Age = age;
	}
	
	public Employee() {}
}
