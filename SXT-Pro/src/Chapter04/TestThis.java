package Chapter04;

/**
 * Testing the this
 * @author Woo
 *
 */

public class TestThis {
	int a, b, c;
	
	TestThis(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	TestThis(int a, int b, int c) { // overload
		this(a,b); // calling the constructor must be the first statement; TestThis(int a, int b);
		this.c = c;
	}
	
	void sing() {
		System.out.println("EdShrean");
	}

	void eat() {
		this.sing(); // sing() also works
		System.out.println("Foster the people");
	}
	
	public static void main(String[] args) {
		TestThis hi = new TestThis(2,3);
		hi.eat();
	}
}
