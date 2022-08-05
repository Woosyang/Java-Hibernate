package Chapter09;
import java.util.*;
/**
 * Test the usage of the HashMap
 * @author Woo
 * 
 */

public class TestMap02 {
	public static void main(String[] args) {
		Employee A = new Employee(100, "James", 2000);
		Employee B = new Employee(101, "Keegan", 4000);
		Employee C = new Employee(102, "Siyang", 3000);
		Employee D = new Employee(100, "Joe", 5000);
		Map<Integer, Employee> M = new HashMap<>();
		M.put(100, A);
		M.put(101, B);
		M.put(102, C);
		Employee EP = M.get(102);
		System.out.println(EP.GetSalary()); // 3000
	    System.out.println(M.get(102).GetName()); // Siyang 
	    // if get(Key)'s Key doesn't match the Key which the Map has, it will return null
		System.out.println(M);
		M.put(100, D);
		// overlap
		System.out.println(M);
	}
}

class Employee {
	private int ID;
	private String Name;
	private int Salary;
	public Employee(int ID, String Name, int Salary) {
		this.ID = ID;
		this.Name = Name;
		this.Salary = Salary;
	}
	public void SetID(int ID) {
		this.ID = ID;
	}
	public int GetID() {
		return this.ID;
	}
	public void SetName(String Name) {
		this.Name = Name;
	}
	public String GetName() {
		return this.Name;
	}
	public void SetSalary(int Salary) {
		this.Salary = Salary;
	}
	public int GetSalary() {
		return this.Salary;
	}
	@Override
	public String toString() {
		return "ID: " + this.ID + ", Name: " + this.Name + ", Salary: " + this.Salary;
	}
}