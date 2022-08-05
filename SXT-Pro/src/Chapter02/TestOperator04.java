package Chapter02;

/**
 * Testing the bitwise operators
 * @author Woo
 *
 */

public class TestOperator04 {
	public static void main(String[] args) {
		int a = 3;
		int b = 4;
		System.out.println(a & b);
		System.out.println(a | b);
		System.out.println(a ^ b);
		System.out.println(~ a);
		
		//Shift
		int c = 3 << 2; // *4
		System.out.println(c);
		System.out.println(12 >> 2); // /4
	}
}
