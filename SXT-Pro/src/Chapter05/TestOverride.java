package Chapter05;

/**
 * Testing the override / overlap
 * @author Woo
 *
 */

public class TestOverride {
	public static void main(String[] args) {
		Horse ma = new Horse();
		ma.run();
	}
 
}

class Vehicle {
	public void run() {
		System.out.println("Run~");
	}
	public void stop() {
		System.out.println("Stop.");
	}
	public Person /* type */ WhoIsPsg /* object */ () { // different class can use the same name
		return new Person();
	}
}

class Horse extends Vehicle {
	public void run() {
		System.out.println("Stamp..");
	}
	public Person /* type */ WhoIsPsg /* object */ () { // different class can use the same name
		return new Student();
	}
	public Student /* type */ WhoIsPsgs /* object */ () {
		return new Student();
		// error: return new Person(); mismatch but can be fixed by using cast "new (Student) Person()"
	}
}