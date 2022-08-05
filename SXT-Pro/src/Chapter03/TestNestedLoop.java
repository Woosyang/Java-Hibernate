package Chapter03;

/**
 * Testing the nested loop
 * @author Woo
 *
 */

public class TestNestedLoop {
	public static void main(String[] args) {
		for (int i = 1; i <= 5; i ++) {
			for (int j = 1; j <= 5; j ++) {
					System.out.print(i + " ");	
				}
				System.out.println();
			}
		
		System.out.println("------------");
		
		// sample 1 * 2 = 2, 2 * 2 = 4
		
		for (int m = 1; m <= 2 ; m ++) {
			System.out.print(m + " * " + 2 + " = " + 2 * m + "\t");
		}
		System.out.println();
		
		// 9 * 9 multiplication table
		
		for (int a = 1; a <= 9; a ++) {
			for (int b = 1; b <= a; b ++) {
				System.out.print(b + " * " + a + " = " + a * b + "\t");
			}
			System.out.println(); // start a new line when a inner loop is over
		}
		
		// example
		
		System.out.println("-------------");
		for (int z = 1; z <= 3; z ++) {
			for (int x = 1; x <= 3; x ++) {
				for (int c = 1; c <= 3; c ++) {
					System.out.print(z + " "); // replace the factor as you want to see how it works 
				}
			}
		}
		System.out.println();
		System.out.println("-------------");
		
		// assignment01: use the loop to calculate the sum of odd numbers and even numbers within 100
		
		int sum01 = 0;
		int sum02 = 0;
		for (int i = 1; i <= 100; i ++) {
			if (i % 2 == 0) {
				sum01 = sum01 + i;
			} else {
				sum02 = sum02 + i;
			}
		}
		System.out.println("The sum of even numbers: " + sum01);
		System.out.println("The sum of odd numbers: " + sum02);
		
		// other solution:
		
		System.out.println("-------------");
		int a , b = 0;
		int sum03 = 0;
		int sum04 = 0;
		for (int i = 1; i <= 50; i ++) {
			a = 2 * i - 1;
			b = 2 * i;
			sum03 = sum03 + a;
			sum04 = sum04 + b;
		}
		System.out.println("The sum of odd numbers: " + sum03);
		System.out.println("The sum of even numbers: " + sum04);
	
		// assignment02: use the loop to print out the numbers that can be divided by 5 within 1000, and each line has five of them in order 
		
		System.out.println("-------------");
		for (int i = 1; i <= 1000; i ++) {
			if (i % 5 == 0) {
				System.out.print(i + "\t");
			}
			if (i % 25 == 0) {
				System.out.println(); // can be nested in the last if structure
			}
		}
		
		// solution02:
		
		System.out.println("-------------");
		int count01 = 0;
		for (int i = 1; i <= 1000; i ++) {
			if (i % 5 == 0) {
				System.out.print(i + "\t");
				count01 ++;
				if (count01 % 5 == 0) {
					System.out.println();
					// count01 = 0; not necessary
				}
			}
		}
		
		// solution03:
		
		System.out.println("-------------");
		int count02 = 0;
		for (int i = 1; i <= 1000; i ++) {
		if (i % 5 == 0) {
				System.out.print(i + "\t");
				count02 ++;
			}
		if (count02 == 5 ) { // count02 % 5 == 0 will generate more than one new line if count02 is greater than 5 and the if structure is not in the last if structure
			System.out.println();
			count02 = 0;
			}
		}
	}
}
		
