package Chapter07;
import java.util.Arrays;

/**
 * Testing the Binary Searching (Half Searching)
 * @author Woo
 *
 */

public class TestBinarySearch {
	public static void main(String[] args) {
		int[] A = {30, 20, 50, 10, 80, 9, 7, 12, 100, 40, 8};
		Arrays.sort(A); // must be sorted
		System.out.println(Arrays.toString(A));
		System.out.println(MyBinarySearch01(A, 20));
		MyBinarySearch02(A, 2);
	}
	public static int MyBinarySearch01(int[] A, int a) {
		int low = 0;
		int high = A.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2; // don't forget the parenthesis!
			if (a == A[mid]) {
				return mid;
			}
			if (a < A[mid]) {
				high = mid - 1;
			} 
			if (a > A[mid]) {
				low = mid + 1;
			}
		}
		return -1; // if there is no one element of the given array match the given number
	}
	public static void MyBinarySearch02(int[] A, int a) {
		int low = 0;
		int high = A.length - 1;
		boolean flag = true;
		for (int i = 0; i < A.length / 2; i ++) { // A.length / 2 is more accurate but you can do A.length
			int mid = (low + high) / 2;
			if (a == A[mid]) {
				System.out.println(mid);
				flag = false;
				break; // when the key is found, then the program stop
			}
			else if (a < A[mid]) {
				high = mid - 1;
			}
			else if (a > A[mid]) {
				low = mid + 1;
			}
		}
		if (flag) {
			System.out.println(-1);
		}
	}
}
