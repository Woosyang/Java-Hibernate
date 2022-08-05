package Chapter08;

/**
 * Test the Math
 * @author Woo
 *
 */

public class TestMath {
	public static void main(String[] args) {
		// rounding operation
		System.out.println(Math.ceil(3.2));
		System.out.println(Math.floor(3.8));
		System.out.println(Math.round(3.2));
		System.out.println(Math.round(3.8));
		// operation relative to absolute value, root, a to b (power)
		System.out.println(Math.abs(- 40));
		System.out.println(Math.sqrt(64));
		System.out.println(Math.pow(2, 5));
		System.out.println(Math.pow(5, 2));
		// constant value in Math
		System.out.println(Math.PI);
		System.out.println(Math.E);
		// random number
		System.out.println(Math.random()); // [0,1)
	}
}
