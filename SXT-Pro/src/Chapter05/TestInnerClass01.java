package Chapter05;

/**
 * Test the non-static inner class
 * @author Woo
 *
 */

public class TestInnerClass01 {
	public static void main(String[] args) {
		// or can do in this way:
		// Outer a = new Outer();
		// Outer.Inner b = a.new Inner();
		// a.Show();
		// b.Show();
		Outer01.Inner01 a = new Outer01().new Inner01();
		a.Show();
	}
}

class Outer01 { // only can be public or default
	private int age = 10;
	public void Show() {
		System.out.println(age); // 10
	}
	// Outer$Inner.class, member 
	public class Inner01 { // can be public, private, default, static
		// if the inner class is non-static, it can't have the static variable
		private int age = 20;
		public void Show() {
			final int age = 30;  // can only be final
			System.out.println("age-the member variable from the Outer: " + Outer01.this.age);
			System.out.println("age-the member variable from the Inner: " + this.age);// 20
			System.out.println("age-the local variable from the Inner: " + age); // 30
		}
	}
}