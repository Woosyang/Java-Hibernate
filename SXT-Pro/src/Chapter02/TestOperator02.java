package Chapter02;

/**
 * Testing the relational operator
 * @author Woo
 *
 */

public class TestOperator02 {
	public static void main(String[] args) {
		int a = 3;
		System.out.println(a == 3);
		System.out.println(a != 3);
		System.out.println(a < 5);
		
		char b = 'a';
		char c = 'c';
		
		System.out.println((int)b); // convert to number,uni-code
		System.out.println(0 + b); // cast
		System.out.println(0 + c); // cast
		System.out.println(b < c); 
	}

}
