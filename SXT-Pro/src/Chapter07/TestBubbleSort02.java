package Chapter07;
import java.util.Arrays;

/**
 * Test the original bubble sort
 * @author Woo
 *
 */

public class TestBubbleSort02 {
	public static void main(String[] args) {
		int[] A = {2, 3, 1, 5, 8, 9, 4, 7, 6};
		int store = 0;
		// outside loop only needs to execute A.length - 1 times
		for (int j = 0; j < A.length - 1; j ++) {
			for (int i = 0; i < A.length - 1 /* i + 1 maybe out of boundary */; i ++) {
				// Execute A.length - 1 times
				if (A[i] > A[i + 1]) {
					store = A[i];
					A[i] = A[i + 1];
					A[i + 1] = store;
				}
				System.out.println(Arrays.toString(A));
			}
			// mark the times of the outside loop
			System.out.println("===========================");
		}
	}
}
