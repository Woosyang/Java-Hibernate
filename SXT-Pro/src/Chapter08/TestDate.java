package Chapter08;
import java.util.*; // time

/**
 * Test the common usage of Date
 * @author Woo
 *
 */

public class TestDate {
	public static void main(String[] args) {
		Date a = new Date(); // System.currentTimeMillis()
		System.out.println(a);
		Date b = new Date(2000);
		System.out.println(b); // time zone
		System.out.println(b.getTime());
		System.out.println(a.before(b)); // compare the milliseconds between this two
		Date c = new Date(2020 - 1900, 4, 20); // Y must be 2020 - 1900, mon 04 -> May
		System.out.println(c);
		// we can use Calendar to deal with date
	}
}
