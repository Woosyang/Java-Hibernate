package Chapter08;

/**
 * Test the trap of the usage in mutable and immutable strings 
 * @author Woo
 *
 */

public class TestStringBuilder03 {
	public static void main(String[] args) {
		// splice the string 
		String Str = " ";
		// actually it use StringBuilder to splice the string
		// but every loop will create a new StringBuilder object
		long num1 = Runtime.getRuntime().freeMemory(); // get how many memory the system has
		long time1 = System.currentTimeMillis(); // get the current time of the system 
		for (int i = 0; i < 5000; i ++) {
			Str = Str + i; // it will create 10000 objects
		}
		long num2 = Runtime.getRuntime().freeMemory(); // get how many memory the system left
		long time2 = System.currentTimeMillis(); 
		System.out.println("The token memory of using String: " + (num1 - num2));
		System.out.println("The token time of using String: " + (time2 - time1));
		// using StringBuilder to splice string
		StringBuilder Sb = new StringBuilder();
		long num3 = Runtime.getRuntime().freeMemory(); // get how many memory the system has
		long time3 = System.currentTimeMillis(); // get the current time of the system
		for (int i = 0; i < 5000; i ++) {
			Sb.append(i);
		}
		long num4 = Runtime.getRuntime().freeMemory(); 
		long time4 = System.currentTimeMillis();
		System.out.println("The token memory of using StringBuilder: " + (num3 - num4));
		System.out.println("The token time of using StringBuilder: " + (time4 - time3));
	}
}
