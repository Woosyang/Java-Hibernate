package Chapter03;

/**
 * Testing the break of the loop
 * @author Woo
 *
 */

public class TestBreak {
	public static void main(String[] args) {
		int total = 0; // set a counter
		System.out.println("Begin");
		long a = 0;
		while (true) {
			total ++;
			int i = (int)Math.round(100 * Math.random()); // cast: Math.round is the long type
			System.out.println(i);
			// when i equals 88, it will break the loop
			if (i == 88) {
				break;
			}
		}
		// print out the how many times will the loop exist
		System.out.println("Game Over, used " + total + " times");
		System.out.println("------------------");
		// test break with nested for-loop
		for (int i = 0; i < 10; i ++) {
			for (int j = 1; j < 2; j ++) {
				if (i * j < 3) {
					break; // jump out the inner loop and then continue the outer loop
				}
				System.out.print(i * j + " ");
			}
			System.out.print("----> " + i);
			System.out.println();
		}
	}

}
