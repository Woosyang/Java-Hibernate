package Chapter04;

/**
 * Testing the class and object
 * @author Woo
 *
 */

// javac -> Sxtstu.java -> Sxtstu(virtual machine)
public class SxtStu {
	
	// fields
	int id;
	String name;
	int age;
	
	Computer comp;
	
	// methods
	void study() {
		System.out.println("I NEED TO STUDY HARDER AND HARDER! BY USING THE PC OF " + comp.brand); // comp.brand means access the property
	}
	
	void play() {
		System.out.println("I AM PLAYING THE MOBA GAME~");
	}
	
	// build a method to create an object of the class
	// building a method without the arguments can be created by the system automatically
	
	SxtStu() {
	}
	
	// interface of the program, indispensable
	public static void main(String[] args) {
		SxtStu a = new SxtStu(); // creating a new object
		a.id = 1024;
		a.name = "Woo";
		a.age = 25;
		Computer b = new Computer();
		b.brand = "Apple";
		a.comp = b;
		a.study(); // order matters
		a.play();
	}
}

	class Computer {
		String brand;
	}