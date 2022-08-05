package Chapter09;
import java.util.*;
/**
 * Test the usage of the HashMap
 * @author Woo
 *
 */

public class TestMap01 {
	public static void main(String[] args) {
		Map<Integer, String> M1 = new HashMap<>();
		M1.put(1, "One");
		M1.put(2, "Two");
		M1.put(3, "Three");
		System.out.println(M1.get(1));
		System.out.println(M1.size());
		System.out.println(M1.isEmpty()); // judge if the map is empty
		System.out.println(M1.containsKey(2)); // true, judge if the map has the key
		System.out.println(M1.containsValue("Four")); // false, judge if the map has the value 
		Map<Integer, String> M2 = new HashMap<>();
		M2.put(4, "Four");
		M2.put(5, "Five");
		M2.put(6, "Six");
		M1.putAll(M2); // let M1 contains all of the key - value in M2
		System.out.println(M1);
		// key can't be duplicated, if it happens, the new value of the new key
		// will cover the old one
		// use the equals() to judge whether the key is duplicated
		M2.put(3, "III");
		M1.putAll(M2);
		System.out.println(M1);
		M1.put(1, "I");
		System.out.println(M1);
		// null
		System.out.println(M1.get(null));
		// remove
		M1.remove(1);
		// M1.remove(18); no key is matched
		M1.remove(null);
		// M1.remove(100);
		System.out.println(M1);
	}
}
