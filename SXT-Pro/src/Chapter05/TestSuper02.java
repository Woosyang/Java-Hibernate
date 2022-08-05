package Chapter05;

/**
 * Testing the inheritance
 * @author Woo
 *
 */

public class TestSuper02 {
	public static void main(String[] args) {
		System.out.println("Create a new ChildClass");
		new ChildClass02();
	}
}

class FatherClass02 {
	public FatherClass02() {
		// super();
		System.out.println("Create a FatherClass02");
	}
}

class ChildClass02 extends FatherClass02 {
	public ChildClass02() {
		// super();
		System.out.println("Create a ChildClass02");
	}
}
