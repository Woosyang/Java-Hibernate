package Chapter03;

/**
 * Testing the for loop
 * @author Woo
 *
 */

public class TestFor {
	public static void main(String[] args) {
		int sum = 0;
		for (int i = 1; i <= 100; i ++){
			sum = sum + i;
		}
		System.out.println(sum);
		/*
		int i = 1;
		int sum = 0;
		while(i <= 100){
			sum = i + sum;
			a ++;
		}
		 */
		
		/**
		 * 1. Do the init-statement
		 * 2. Make a decision that whether i <= 100
		 * 3. Do the loop
		 * 4. Iterate step by step
		 * 5. Return to the decision
		 */
		
		// "," split multiple init-statements
		
		for (int i = 1, j = i + 10; i < 5; i ++, j = i * 2) {
			System.out.println("i = " + i + "j = " + j);
		}
		
		for (; ;) { // while(true)
			System.out.println("Love U Forever~");
		}
	}
}

