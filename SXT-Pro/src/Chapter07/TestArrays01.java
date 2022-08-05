package Chapter07;

/**
 * Testing the arrays
 * @author Woo
 *
 */

public class TestArrays01 {
	public static void main(String[] args) {
		int[] ar01;
		int[] s = null; // declare an array
		ar01 = new int[10]; // allocate space for arrays with default values
		/* initialize the array */
		ar01[0] = 2;
		ar01[1] = 4;
		ar01[2] = 6;
		// ar01[10] = 0; index: [0, length - 1];  java.lang.ArrayIndexOutOfBoundsException
		
		// initialize the array by using a loop
		for (int i = 0; i < ar01.length /* i < 10 */; i ++) {	
			ar01[i] = 10 * i;
			// System.out.println(ar01[i]);  the same function like the method below
		}
		
		// read out the elements of the array by using a loop
		for (int i = 0; i < ar01.length; i ++) {
			System.out.println(ar01[i]);
		}
		
		String[] ar02;
		ar02 = new String[5]; // index: 0,1,2,3,4
		
		User[] ar03;
		ar03 = new User[3]; // index: 0,1,2
		ar03[0] = new User(001, "Mark");
		ar03[1] = new User(002, "Tod");
		ar03[2] = new User(003, "Jack");
		
		// traverse to require the value we want
		for (int i = 0; i < ar03.length; i ++) {
			System.out.println(ar03[i].getName());
		}
	}
}

