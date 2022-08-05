package Chapter02;

/**
 * Testing the constant
 * @author Woo
 *
 */

public class TestConstant {
	public static void main(String[] args) {
		int age = 18;
		
		final String NAME = "Lord";
		final double PI = 3.14;
		//PI = 3.15 // error, can not do the assignment again
		double r = 4;
		double area = PI * r * r;
		double circle = 2 * PI * r;
		System.out.println("area = " + area);
		System.out.println("circle = " + circle);
	}
}
