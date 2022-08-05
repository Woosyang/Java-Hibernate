package Chapter02;

/**
 * Testing the logical operators
 * @author Woo
 *
 */

public class TestOperator03 {
	public static void main(String[] args) {
		boolean b1 = true;
		boolean b2 = false;
		System.out.println(b1 & b2);
		System.out.println(b1 | b2);
		System.out.println(b1 ^ b2);
		
		// short-circuit
		// int c = 3/0; Exception
		boolean b3 = 1 > 2 && 2 < (3/0);
		// short-circuit AND. If the first operand is wrong, then there is no need to do the rest of it
		System.out.println(b3);
		System.out.println(!b3); // NOT
	}
}
