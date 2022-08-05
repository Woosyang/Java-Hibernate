package Chapter05;

/**
 * Test the Encapsulation
 * @author Woo
 *
 */

public class TestEncapsulation01 {
	public static void main(String[] args) {
		Human A = new Human();
		// A.age = 8; age is not visible for other classes
		 A.name = "Bao";
		 A.height = 220;
		 PersonForEncapsulation a = new PersonForEncapsulation();
		 // a.age = 93;
		 a.setAge(93);
		 System.out.println(a.getAge());
		 a.setName("Toad");
		 System.out.println(a.getName());
	}
}

/*
class Human { 
// default class and it also can only be used in the same packet 
 but it can be used in different packets when it is public class //
	private int age;
	String name; // default, can be used in the same packet
	void SayAge() {
		System.out.println(age);
	}
}
*/

class Boy extends Human {
	void SayHello() {
	// System.out.println(age); Subclass can't use the private fields and methods of the superclass
	} 
}