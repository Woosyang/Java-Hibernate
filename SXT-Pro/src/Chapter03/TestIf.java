package Chapter03;
 
/**
 * Testing the single-choice structure of if
 * @author Woo
 *
 */

public class TestIf {
	public static void main(String [] args) {
		double d = Math.random(); // return a random number between [0,1)
		System.out.println(d);
		//System.out.println((int)(6 * Math.random() + 1)); [0,6]
		int h = (int)(6 * Math.random() + 1);
		System.out.println(h);
		if (h <= 3) {
			System.out.println("Small");
			System.out.println("small small");
		}
		
		System.out.println("----------------------");
		// rolling a die three times to test your luck
		int i = (int)(6 * Math.random() + 1);
		int j = (int)(6 * Math.random() + 1);
		int k = (int)(6 * Math.random() + 1);
		int count = i + j + k;
		// if the sum of numbers on the die at three times are greater than 15, then good luck
		if (count > 15) {
			System.out.println("Good Luck!");
		}
		// if the sum of numbers on the die at three times are between 10 to 15, then so-so
		if (count >= 10 && count <= 15) {
			System.out.println("SO-SO.");
		}
		// if the sum of numbers on the die at three times are below 10, then no good
		if (count < 10) {
			System.out.println("No Good...");
		}
	}
}
