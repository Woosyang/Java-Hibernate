package Chapter06;

import java.util.regex.*;

/**
 * Testing the RuntimeException
 * @author Woo
 *
 */


public class TestException01 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		// ArithmeticException
		/*
		int a = 1;
		int b = 0;
		int i = a / b;
		System.out.println(i); exception happens
		*/
		int a = 1;
		int b = 0;
		if (b != 0) {
			System.out.println(a / b);
		}
		
		// NullPointerException
		/*
		String s = null;
		System.out.println(s.charAt(0));
		*/
		String s = null;
		if (s != null) {
			System.out.println(s.charAt(0));
		}
		
		// ClassCastException
		/*
		Animal c = new Dog();
		Cat d = (Cat) c;
		*/
		Animal c = new Dog();
		if (c instanceof Cat) {
			Cat d = (Cat) c;
		}
		
		// ArrayIndexOutOfBoundsException
		/*
		int A[] = new int[5];
		System.out.println(A[5]);
		 */
		int A[] = new int[5];
		int l = 5;
		if (l < A.length) {
			System.out.println(A[l]);
		}
		
		// NumberFormatException
		/*
		String S = "1234abcd";
		System.out.println(Integer.parseInt(S));
		*/
		String S = "1234abcd";
		Pattern p = Pattern.compile("^\\d+$");
		Matcher m = p.matcher(S);
		if (m.matches()) { // if S matches the Regex, then do the conversion
            System.out.println(Integer.parseInt(S));
        }
	}
}

class Animal {}
class Dog extends Animal {}
class Cat extends Animal {}
