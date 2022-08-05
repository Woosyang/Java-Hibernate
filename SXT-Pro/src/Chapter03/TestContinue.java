package Chapter03;

/**
 * Testing the continue
 * continue means ending this time's loop and starting the next time's loop
 * @author Woo
 *
 */

public class TestContinue {
	public static void main(String[] args) {
		int count = 0; // set a counter
		for (int i = 100; i < 150; i ++) {
			// if it is the multiple of 3, skip this the loop and start the next time's loop
			if (i % 3 == 0) {
				continue;
			}
			// else (not the multiple of 3) print out the number
			System.out.print(i + " ¡¢");
			count ++; // counter increases by 1 when it prints out a number
			// each line prints out 5 numbers in turn
			if (count % 5 == 0) {      // count == 5
				System.out.println();
				// count = 0; reset the counter
			}
		}
	}
}
