package Chapter05;

/**
 * Testing the object and overriding of toSring
 * @author Woo
 *
 */

public class TestObject { // extends the object
	public static void main(String[] args) {
		// Object obj;
		TestObject to = new TestObject();
		System.out.println(to.toString());
		People a = new People("Tom", 40);
		System.out.println(a.toString()); // print it out
	}
	
	@Override
	
	public String toString() { //method
		return "Testing The Object";
	}
}

class People {
	String name;
	int age;
	
	@Override
	
	public String toString() { // method: type-String, name-toString
		return "Name: " + name + ", Age: " + age;
	}
	People(String name, int age) { // constructor
		this.name = name;
		this.age = age;
	}
}