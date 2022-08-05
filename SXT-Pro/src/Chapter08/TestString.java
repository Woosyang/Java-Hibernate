package Chapter08;

/**
 * Test String
 * @author Woo
 *
 */

public class TestString {
	public static void main(String[] args) {
		String s1 = new String("abcdef");
        String s2 = s1.substring(2, 4);
        // Print: ab199863
        System.out.println(Integer.toHexString(s1.hashCode()));
        // Print: c61 
        System.out.println(Integer.toHexString(s2.hashCode()));
        // Obviously, s1 and s2 are not the same object
        
        // Compiler will optimize for "a" + "b" => "ab"
        String a = "a" + "b";
        String b = "ab";
        String c = "a";
        String d = "b";
        String e = c + d;
        System.out.println(a == b); // true
        System.out.println(a.equals(b)); // true
        System.out.println(a == e); // false
        System.out.println(a.equals(e)); // true
        // compareTo()  is used for comparing two strings lexicographically. 
        // Each character of both the strings is converted into a Unicode value for comparison. 
        // If both the strings are equal then this method returns 0 else it returns positive or negative value. 
        // The result is positive if the first string is lexicographically greater than the second string else the result would be negative.
        System.out.println(a.compareTo(b));
        System.out.println(a.compareTo(c));
	}
}
