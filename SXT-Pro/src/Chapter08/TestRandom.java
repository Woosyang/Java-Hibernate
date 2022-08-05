package Chapter08;
import java.util.*; // random

/**
 * Test Random
 * @author Woo
 *
 */

public class TestRandom {
	public static void main(String[] args) {
		Random Rand = new Random();
		// get a random double number between [0,1)
		System.out.println(Rand.nextDouble());
		// get a random int number at an acceptable range
		System.out.println(Rand.nextInt()); 
		// get a random float number between between [0,1)
		System.out.println(Rand.nextFloat());
		// get a random boolean value from true or false
		System.out.println(Rand.nextBoolean());
		// get a random number between [0, 10)
		System.out.println(Rand.nextInt(10));
		// get a random number between [10, 20)
		System.out.println(10 + Rand.nextInt(10));
		System.out.println(10 + (int) (Rand.nextDouble() * 10)); // this one is more complicated
	}
}
