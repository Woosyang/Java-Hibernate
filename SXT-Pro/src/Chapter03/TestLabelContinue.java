package Chapter03;

/**
 * Testing the break and continue with label
 * @author Woo
 *
 */

public class TestLabelContinue {
	public static void main(String[] args) {
		// print out all the prime numbers between 101 to 150
		outer: for (int i = 101; i <= 150; i ++) {
			// do this loop first
			for (int j = 2; j <= i / 2; j ++) { // 2 is the minimal quotient
				if (i % j == 0) {
					continue outer; // go to the label
				}
			}
			System.out.print(i + " ");
		}
	}
}
