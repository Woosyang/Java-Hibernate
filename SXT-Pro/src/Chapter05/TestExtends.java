package Chapter05;

/**
 * Testing the extend
 * @author Woo
 *
 */

public class TestExtends {
	public static void main(String[] args) {
		Student stu01 = new Student();
		stu01.name = "Woo";
		stu01.height = 175;
		stu01.major = "Engineering";
		stu01.rest();
		Student stu02 = new Student("Zhu", 160, "Computer");
		System.out.println(stu02 instanceof Student);
		System.out.println(stu02 instanceof Person);
		System.out.println(stu02 instanceof Object);
		Person p = new Person();
		System.out.println(p instanceof Student);
	}
}

class Person { // control + t
	String name;
	int height;
	
	public void rest() {
		System.out.println("Have a rest~");
	}
}

class Student extends Person {
	// String name; can ignore it when using extend
	// int height;  can ignore it when using extend
	String major;
	
	public void study() {
		System.out.println("Study for 2 hours.");
	}
	
	/*
	 public void rest() {
		System.out.println("Have a rest~");
	}
	*/ // can ignore it when using extend
	
	public Student(String name, int height, String major) { // Constructor
		this.name = name;
		this.height = height;
		this.major = major;
	}
	
	public Student() {
		
	}
}