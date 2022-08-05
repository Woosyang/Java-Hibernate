package Chapter07;
import java.util.Arrays;

/**
 * Testing the bubble sort and Optimizing it
 * @author Woo
 *
 */

public class TestBubbleSort01 {
	public static void main(String[] args) {
		int[] value = {2, 3, 1, 5, 8, 9, 4, 7, 6};
		int temp = 0;
		for (int i = 0; i < value.length - 1; i ++) {
			boolean flag = true;
			for (int j = 0; j < value.length - 1 - i /* don't be out of boundary */; j ++) {
				// make the comparison and exchange the order
				if (value[j] > value[j + 1]) {
					temp = value[j];
					value[j] = value[j + 1];
					value[j + 1] = temp;
					flag = false;
				}
				System.out.println(Arrays.toString(value));
				// System.out.println(value); don't make that mistake	
			}
			if (flag == true) {
				System.out.println("Done");
				break; // end the whole loop
			}
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}
}
