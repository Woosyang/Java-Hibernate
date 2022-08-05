package Chapter05;

/**
 * Test the static inner class
 * @author Woo
 *
 */

public class TestInnerClass02 {
	public static void main(String[] args) {
		Outer02.Inner02 a = new Outer02.Inner02();
		a.Show();
		Outer02 b = new Outer02();
		b.Show();
	}
}

class Outer02 { // can only be public or default
	int age = 30;
	static class Inner02 {
		int age = 20;
		void Show() {
			System.out.println("age-the member variable from Outer: " + new Outer02().age); // 30
		}
	}
	// Outer class can access the variable from the Inner class
	public void Show() {
		System.out.println("age-the member variable from Inner: " + new Inner02().age); // 20 
	}
}