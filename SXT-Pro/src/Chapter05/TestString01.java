package Chapter05;

/**
 * Test the basic usage of the String
 * @author Woo
 *
 */

public class TestString01 {
	public static void main(String[] args) {
		String str1 = "abc";
		String str2 = new String("def");
		String str3 = "abc" + "defg";
		String str4 = "16" + 17; // not an addition, it is a String concatenation
		String str5 = "ABC";
		String str6 = "abcdefg";
		String str7 = "defabc";
		String s00 = str5.toLowerCase(); // a new object
		String s01 = str6.substring(0, 4); // a new object
		String s05 = str7.substring(3); // a new object
		System.out.println(str7.substring(3));
		System.out.println("whether str6 == str3:" + (str6 == str3)); // true
 		System.out.println("whether s00 == str1:" + (s00 == str1)); // false
		System.out.println("whether s01 == str1:" + (s01 == str1)); // false
		System.out.println("whether s05 == s01:" + (s01 == s05)); // false
		System.out.println(str4);
		System.out.println("---------------------");
		String s02 = "Woo";
		String s03 = "Woo";
		String s04 = new String("Woo");
		System.out.println("s02 ?= s03: " + (s02 == s03)); // true, same object
		System.out.println("s04 ?= s01: " + (s02 == s04)); // false, not the same object
		
		// we usually use equals() to compare the String(Content)
		System.out.println("if the content of s01 is the same as s02: " + s01.equals(s02));
		System.out.println("if the content of s01 is the same as s03: " + s03.equals(s01));
	}
}
