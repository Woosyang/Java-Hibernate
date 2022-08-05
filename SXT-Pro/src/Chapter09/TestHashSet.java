package Chapter09;
import java.util.*;
/**
 * Test the usage in Collection(interface), especially Set
 * @author Woo
 *
 */

public class TestHashSet {
	public static void main(String[] args) {
		Set<String> S01 = new HashSet<>();
		S01.add("A");
		S01.add("B");
		S01.add("C");
		S01.add("A"); // not added
		S01.remove("B");
		System.out.println(S01.contains("A"));
		Set<String> S02 = new HashSet<>();
		S02.add("H");
		S02.add("A");
		S02.add("L");
		S01.addAll(S02); // S01 already has "A"
		System.out.println(S01);
		System.out.println(S01.size());
	}
}
