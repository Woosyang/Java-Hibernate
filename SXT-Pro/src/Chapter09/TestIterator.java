package Chapter09;
import java.util.*;
import java.util.Map.*;
/**
 * Use the iterator to traverse each element in Map, List, Set
 * @author Woo
 *
 */

public class TestIterator {
	
	public static void main(String[] args) {
		ListIterator();
		System.out.println("================");
		SetIterator();
		System.out.println("================");
		MapIterator();
	}
	
	public static void ListIterator() {
		List<String> List = new ArrayList<>();
		List.add("Mao");
		List.add("Deng");
		List.add("Hua");
		List.add("Jiang");
		List.add("Hu");
		List.add("Xi");
		//   initialization                        decision
		for (Iterator<String> a = List.iterator(); a.hasNext();) {
			// like array a[i ++];
			// return a[i] then points to a[i + 1]
			String s = a.next(); // return the first element
			// need a String s to store each element in the List
			System.out.println(s);
			// not System.out.println(a)
		}
	}
	
	public static void SetIterator() {
		// proves HashSet is not ordered
		Set<Integer> Set = new HashSet<>();
		Set.add(83);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
		Set.add(92);
		Set.add(87);
		Set.add(93);
		Set.add(77);
		Set.add(66);
		for (Iterator<Integer> a = Set.iterator(); a.hasNext();) {
			Integer I = a.next();
			System.out.println(I);
		}
	}
	
	public static void MapIterator() {
		Map<Integer, String> Map = new HashMap<>();
		Map.put(100, "RickRoss");
		Map.put(200, "50Cent");
		Map.put(300, "Jay-Z");
		Map.put(400, "Future");
		// util.Map.*
		// covert it into a Set by using Entry
		// first way to traverse Map
		Set<Entry<Integer, String>> S = Map.entrySet();
		// the content inside Set is same as Iterator
		for (Iterator<Entry<Integer, String>> a = S.iterator(); a.hasNext();) {
			Entry<Integer, String> E = a.next();
			System.out.println(E.getKey() + "--->" + E.getValue());
		}
		// second way to traverse Map
		System.out.println("+++++++++++++++++++++++");
		Set<Integer> Key = Map.keySet();
		for (Iterator<Integer> a = Key.iterator(); a.hasNext();) {
			Integer T = a.next();
			System.out.println(T + "--->" + Map.get(T));
		}
	}
}
