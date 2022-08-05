package Chapter08;

/**
 * Test StringBuilder
 * @author Woo
 *
 */

public class TestStringBuilder01 {
	public static void main(String[] args) {
		String Str;
		// StringBuilder has a unsafe thread but is efficient
		// StringBuffer has a safe thread but is inefficient
		StringBuilder Sb = new StringBuilder("May you be loved");
		System.out.println(Integer.toHexString(Sb.hashCode())); // print out the address
		System.out.println(Sb);
		Sb.setCharAt(4, 'Y');
		System.out.println(Integer.toHexString(Sb.hashCode())); // same address
		System.out.println(Sb); // the content is changed
	}
}
