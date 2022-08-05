package Chapter05;

/**
 * Testing the super
 * @author Woo
 *
 */

public class TestSuper01 {
	public static void main(String[] args) {
		new ChildClass01().f();
		// ChildClass a = new ChildClass();
		// a.f();
	}
}

class FatherClass01 {
	public int value;
	public void f() {
		value = 100;
		System.out.println("FatherClass01.value = " + value);
	}
}

class ChildClass01 extends FatherClass01 {
	public int value; // create a new one and it is different with the value of the FatherClass
	public void f() {
		super.f(); // call the method of the FatherClass(Father)
		value = 200;
		System.out.println("ChildClass01.value = " + value);
		System.out.println(value);
		System.out.println(super.value); // call the member variable of the FatherClass(Father)
	}
}
