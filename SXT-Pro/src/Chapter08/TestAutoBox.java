package Chapter08;

/**
 * Test the auto-box 
 * @author Woo
 *
 */

public class TestAutoBox {
	public static void main(String[] args) {
		Integer a = 234; // auto boxing, Integer a = Integer.valueOf(234);
		int b = a; // Unboxing, compiler will convert it into: int b = a.intValue();
		/*
		Integer c = null; 
		int d = c;  Null pointer Exception
		*/
		// cache is an array which is between[-128,127]made by the system
		// when we call the valueOf(), we first check whether the value is in the 
		// [-128,127], if it is in the range, we can have it directly from the cache, if not,
		// we will create a new Integer object which has this value
		Integer a1 = 1234;
		Integer a2 = 1234;
		System.out.println(a1 == a2); // false, because a1 and a2 are not in the cache
		System.out.println(a1.equals(a2)); // true
		System.out.println("----------------------------------");
		Integer a3 = Integer.valueOf(-128);
		Integer a4 = -128;
		System.out.println(a3 == a4);
		System.out.println(a3.equals(a4));
	}
	
}
