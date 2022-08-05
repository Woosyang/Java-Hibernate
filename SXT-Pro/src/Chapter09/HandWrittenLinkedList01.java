package Chapter09;

/**
 * create a linked list
 * @author Woo
 *
 */

public class HandWrittenLinkedList01 {
	private Node First; // null
	private Node Last; // null
	// private int Size;
	
	// []
	// ['a', 'b', 'c']
	public void Add(Object Obj) {
		Node N = new Node(Obj);
		// can't move Node First
		if (First == null) {
			First = N;
			Last = N;
			// N.Previous = null;
			// N.Next = null;
		} else {
			N.Previous = Last; // aims to the same address
			Last.Next = N; // connects each other
			// N.Next = null; optional
			Last = N; // rearrange the Node Last
		}
	}
	
	// print out the linked list
	public String toString() {
		// traverse every elements in the linked list
		StringBuilder Sb = new StringBuilder();
		Node Temp = First;
		Sb.append("[");
		while (Temp != null) {
			Sb.append(Temp.Element + " -> ");
			Temp = Temp.Next;
		}
		Sb.setCharAt(Sb.length() - 2, ' ');
		Sb.setCharAt(Sb.length() - 3, ' ');
		Sb.setCharAt(Sb.length() - 4, ']');
		return Sb.toString();
	}
	
	public static void main(String[] args) {
		HandWrittenLinkedList01 List = new HandWrittenLinkedList01();
		List.Add("a");
		List.Add("b");
		List.Add("c");
		System.out.println(List);
	}
}
