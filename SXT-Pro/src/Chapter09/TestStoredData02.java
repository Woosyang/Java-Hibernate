package Chapter09;
import java.util.*;
/**
 * Use the Collection to store the data of form
 * ORM -> Object Relational Mapping
 * Use the JavaBean to store each data of the row
 * Use the List or Map to store each each row of the form
 * @author Woo
 *
 */

/*
Eg.Form:
ID		Gender	Name	Age		Salary		Enrolling Date
1024	Male	James	21		4000		2015.8.10
2048	Male	Keegan	21		5000		2015.8.10
0256	Male	Ted		26		10000		2018.8.10
*/
public class TestStoredData02 {
	public static void main(String[] args) {
		Student A = new Student(1024, "Male", "James", 21, 4000, "2015.8.10");
		Student B = new Student(2048, "Male", "Keegan", 21, 5000, "2015.8.10");
		Student C = new Student(2560, "Male", "Ted", 26, 10000, "2018.8.10");
		List<Student> L = new ArrayList<>();
		L.add(A);
		L.add(B);
		L.add(C);
		System.out.print("ID" + "\t" + "Gender" + "\t" + "Name" + "\t" + "Age" + "\t" + "Salary" + "\t" + "Enrolling Date");
		System.out.println();
		for (Student U : L) {
			System.out.println(U);
		}
		System.out.println("Another Version");
		Map<String, Student> m = new HashMap<>();
		m.put("A", A);
		m.put("B", B);
		m.put("C", C);
		Set<String> S = m.keySet();
		for (String s : S) {
			System.out.println(s + "---->" + m.get(s));
		}
	}
}

class Student {
	private int ID;
	private String Gender;
	private String Name;
	private int Age;
	private int Salary;
	private String EnrollingDate;
	public Student(int ID, String Gender, String Name, int Age, int Salary, String EnrollingDate) {
		this.ID = ID;
		this.Gender = Gender;
		this.Name = Name;
		this.Age = Age;
		this.Salary = Salary;
		this.EnrollingDate = EnrollingDate;
	}
	public Student() {} // Default constructor
	// set(), get()
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		Salary = salary;
	}
	
	public String getEnrollingDate() {
		return EnrollingDate;
	}
	public void setEnrollingDate(String enrollingDate) {
		EnrollingDate = enrollingDate;
	}
	
	@Override 
	public String toString() {
		return ID + "\t" + Gender + "\t" + Name + "\t" + Age + "\t" + Salary + "\t" + EnrollingDate; 
	}
}