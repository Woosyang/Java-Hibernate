package Chapter05;

/**
 * Test the anonymous inner class
 * @author Woo
 *
 */

public class TestInnerClass03 {
	// call the method here
	public static void test(H /*Object*/ a /*VARname*/) { // pass the parameter 
		System.out.println("-------------------");
		a.a();
	}
	public static void main(String[] args) {
		test(new H(){ // anonymous inner class
			// just like a class implements the Interface H
			// use only one time
			public void a() { // override
				System.out.println("a");
			}
		});
	}
}

interface H {
	void a();
}

