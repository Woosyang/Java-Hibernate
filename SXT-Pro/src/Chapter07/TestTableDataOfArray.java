package Chapter07;

import java.util.Arrays;

/**
 * Testing the arrays used to save table data 
 * @author Woo
 *
 */

public class TestTableDataOfArray {
	public static void main(String[] args) {
		// display every line of the Table
		Object[] a1 = {001, "Sam", 22, "Teacher", "2000 - 1 - 1"};
		Object[] a2 = {002, "Amy", 24, "Saler", "2002 - 6 - 8"};
		Object[] a3 = {003, "Taylor", 30, "Singer", "1999 - 4 - 7"};
		// display a whole form by creating a new array
		Object[][] TableData = new Object[3][]; /* TableData points to the first array */
		TableData[0] = a1;
		TableData[1] = a2;
		TableData[2] = a3;
		// address
		System.out.println(Arrays.toString(TableData));
		// need Arrays.toString to print out the Array element in the TableData Array
		for (Object[] temp : TableData) {
			// Object[] temp is like an element to the Array TableData
			System.out.println(Arrays.toString(temp));
		}
		System.out.println("--------------");
		// another method
		for (int i = 0; i < TableData.length; i ++) {
			System.out.println(Arrays.toString(TableData[i]));
		}
	}
}
