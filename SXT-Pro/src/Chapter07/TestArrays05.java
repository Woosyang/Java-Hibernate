package Chapter07;

import java.util.Arrays;

/**
 * Testing the usage of java.util.Arrays
 * @author Woo
 *
 */

public class TestArrays05 {
	public static void main(String[] args) {
		int[] a = {10, 20, 30};
		int[] b = {20, 50, 10, 5, 25};
		System.out.println(a);
		// static void, it's different from String toString
		System.out.println(Arrays.toString(a));
		Arrays.sort(b);
		System.out.println(Arrays.toString(b));
		System.out.println(Arrays.binarySearch(b, 50));
	}
}
