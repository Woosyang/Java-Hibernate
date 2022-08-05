package Chapter09;
import java.util.*;
/**
 * Test the usage in Collection(interface), especially List
 * @author Woo
 *
 */

public class TestList {
	public static void main(String[] args) {
		Test01();
		Test02();
		Test03();
	}
	
	
	public static void Test01() {
		// <> template
		Collection<String> C = new ArrayList<String>();
		System.out.println(C.size());
		System.out.println(C.isEmpty());
		C.add("Ao");
		C.add("Em");
		// covert the collection into an array
		Object[] Obj = C.toArray();
		System.out.println(Arrays.toString(Obj));
		System.out.println(C); // call the toString()
		System.out.println(C.size());
		System.out.println(C.contains("Ao"));
		System.out.println(C.contains("Ka"));
		C.remove("Ao");
		C.clear(); // clear all the elements in the Collection
		System.out.println(C);	
		Collection<String> D = new LinkedList<String>();
		System.out.println(D.size());
	}
	
	public static void Test02() {
		// test the intersection and union in the List
		List<String> L01 = new ArrayList<>();
		L01.add("A");
		L01.add("B");
		L01.add("V");
		
		List<String> L02 = new ArrayList<>();
		L02.add("C");
		L02.add("B");
		L02.add("A");
		
		List<String> L03 = new ArrayList<>();
		L03.add("C");
		L03.add("D");
		L03.add("C");
		
		// L01 will change
		L01.addAll(L02);
		System.out.println("L01 after using addAll(L02): " + L01);
		// L01 -> [A, B, V, C, B, A], L02 -> [C, B, A]
		L01.removeAll(L02); // remove all "A", "B"
		System.out.println("L01 after using removeAll(L02): " + L01); // V
		L02.retainAll(L03);
		System.out.println("L01 after using retainAll(L02): " + L02); // C
		System.out.println("whether L01 contians all of the elements in L02: " + L01.containsAll(L02));
	}
	
	public static void Test03() {
		// test the operation relates to index
		List<String> L04 = new ArrayList<>();
		L04.add("W");
		L04.add("H");
		L04.add("A");
		L04.add("T");
		L04.add("?");
		L04.add(4, "!");
		System.out.println(L04);
		L04.remove(4);
		System.out.println(L04);
		L04.set(4, ".");
		System.out.println(L04);
		L04.add("W");
		L04.add("O");
		L04.add("W");
		System.out.println(L04.indexOf("W")); // count the element from the first
		System.out.println(L04.lastIndexOf("W")); 
	}
}
