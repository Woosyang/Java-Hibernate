package Chapter08;

/**
 * Test the wrapped class
 * the method of the other wrapped objects is the same as the Integer
 * @author Woo
 *
 */

public class TestWrappedClass {
	public static void main(String[] args) {
		// Convert the basic data type into wrapped object
		Integer a = new Integer(3);
		System.out.println("a = " + a);
		Integer b = Integer.valueOf(3); // Official way
		System.out.println("b = " + b);
		// Convert the wrapped object into basic data type
		int c = b.intValue();
		System.out.println("int c = " + c);
		double d = b.doubleValue(); // can convert the int type into double type
		// Convert the String into wrapped object
		Integer e = new Integer("999");
		System.out.println("e = " + e); 
		Integer f = Integer.parseInt("88");
		System.out.println("f = " + f);
		// Convert the wrapped object into String
		String str01 = f.toString();
		String str02 = f + " ";
		System.out.println(str01 + "\n" + str02);
		// Build-in constant
		System.out.println("the biggest value of the Integer: " + Integer.MAX_VALUE);
	}
}
