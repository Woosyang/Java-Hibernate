package Chapter08;

/**
 * Test StringBuilder Pro
 * @author Woo
 *
 */

public class TestStringBuilder02 {
	public static void main(String[] args) {
		StringBuilder Sb = new StringBuilder();
		// print out a - z
		for (int i = 0; i < 26; i ++) {
			char Temp = (char)('a' + i); // cast
			Sb.append(Temp);
		}
		System.out.println(Sb);
		Sb.reverse();
		System.out.println(Sb);
		Sb.setCharAt(2, 'C'); // change the content at index of 3
		System.out.println(Sb);
		Sb.insert(0, 'W'); // has return this, so it can be called many times
		Sb.insert(1, 'L').insert(5, 'O').insert(2, 'V').insert(0, 'E');
		System.out.println(Sb);
		Sb.delete(20, 25);
		System.out.println(Sb);
	}
}
