package Chapter07;

/**
 * Testing the traverse
 * @author Woo
 *
 */

public class TestArrays03 {
	public static void main(String[] args) {
		int[] a = new int [4];
		
		// initialize the values of the elements in the array
		for (int i = 0; i < a.length; i ++) {
			a[i] = 100 * i;
		}
		
		// read out the value of each element in the array
		for (int i = 0; i < a.length; i ++) {
			System.out.println(a[i]);
		}
		
		// for - each loop
		// read out the value of each element in the array but can't modify the value
		for (int i : a) {
			System.out.println(i);
		}
		
		String[] w = {"A", "B", "C", "D"};
		for (String m : w) {
			System.out.println(m);
		}
	}
}
