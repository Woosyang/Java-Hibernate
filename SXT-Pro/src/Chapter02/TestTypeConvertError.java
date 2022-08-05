package Chapter02;

/**
 * Testing the type converting error
 * @author Woo
 *
 */

public class TestTypeConvertError {
	public static void main(String[] args) {
		int money = 1000000000;
		int years = 20;
		// the value of total is negative, out of the range of int
		int total = money * years;
		System.out.println("total=" + total);
		// the value of total1 is still negative
		// the default type is int, and the result is casted into long(causing the lost of data)
		long total1 = money * years;
		System.out.println("total1=" + total);
		// the value of total2 is correct, one of the factors is casted into long
		// the whole expression is promoted and all of factors can be calculated as long 
		long total2 = ((long)money) * years;
		System.out.println("total2=" + total2);
	
		long total3 = 34L * 233333 * years * 231414;
		// L means long
		System.out.println(total3);
		
		// naming issue
		int l = 2; // can't tell which is 1 or L
		long a = 2345l; // use the capital L
		System.out.println(l + 1);
	}
}
