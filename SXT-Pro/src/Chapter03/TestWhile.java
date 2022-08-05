package Chapter03;

/**
 * Testing the while loop
 * @author Woo
 *
 */

public class TestWhile {
	public static void main(String[] args) {
		// calculate the sum of 1 + 2 + 3 ......+ 100, 5050
		int i = 0;
		int sum = 0; // initialization 
		while (i < 100) { // make a decision
			i ++;
			sum = sum + i; // execution
		}
		System.out.println(sum);
		
		System.out.println("----------");
		
		int a = 1;
		int sum01 = 0;
		while (a <= 100) {
			sum01 = a + sum01;
			a ++;
		}
		System.out.println(sum01);
		
		/**
		 * 1. Do the init-statement
		 * 2. Make a decision that whether i <= 100
		 * 3. Do the loop
		 * 4. Iterate step by step
		 * 5. Return to the decision
		 */
		
		// do while
		int b = 1;
		int sum02 = 0;
		do {
			sum02 = sum02 + b;
			b ++;
		} while (b <= 100);
		System.out.println(sum02);
	}
}
