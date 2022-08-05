package Chapter09;
import java.util.*;
/**
 * Test the usage of Collections
 * Collection is an interface, Collections is a class
 * @author Woo
 *
 */

public class TestCollections {
	public static void main(String[] args) {
		List<String> L = new ArrayList<>();
		for (int i = 0; i < 10; i ++) {
			L.add("A" + i);
		}
		System.out.println(L);
		Collections.shuffle(L);
		System.out.println(L); // order the elements of the List randomly
		// opposite to shuffled List
		Collections.reverse(L); // order the elements of the List in an opposite direction
		System.out.println(L);
		Collections.sort(L);
		System.out.println(L); // order the elements of the List according to ascending sort
		System.out.println(Collections.binarySearch(L, "B")); // can't find B, return -11
		System.out.println(Collections.binarySearch(L, "A0")); // return the index of element
	}
}
