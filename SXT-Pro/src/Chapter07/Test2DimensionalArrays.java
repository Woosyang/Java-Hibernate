package Chapter07;

/**
 * Testing the 2D - Arrays
 * @author Woo
 *
 */

public class Test2DimensionalArrays {
	public static void main(String[] args) {
		// int[] a = new int[3];
		// Car[] cars = new Car[3];
		int[][] a /*int[] a[] */ = new int[3][] /* can't define the length of the second array */;
		a[0] = new int[] {20, 30};
		a[1] = new int[] {5, 10, 15};
		a[2] = new int[] {40, 60};
		System.out.println(a[1][2]);
		// static initialized 2D - Array
		int [][] b = {
						{1, 2, 3}, 
						{4, 5, 6},
						{7, 8, 9}
					 };
		System.out.println(b[1][2]);
	}
}

class Car {};