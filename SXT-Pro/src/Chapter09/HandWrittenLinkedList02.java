package Chapter09;

/**
 * create a linked list
 * have the method Get
 * @author Woo
 *
 * */

public class HandWrittenLinkedList02 {
	private Node First; // null
	private Node Last; // null
	private int Size = 0;
	
	// []
	// ['a', 'b', 'c']
	public void Add(Object Obj) {
		Node N = new Node(Obj);
		Size ++;
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
	
	public Object Get(int Index) {
		// Node Temp = First; traverse one by one in linked list
		Node Temp;
		if (Index < 0 || Index > Size - 1) {
			throw new RuntimeException("Illegal Index -> " + Index);
		}
		// the efficiency by doing in this way is not good 
		/*
		for (int i = 0; i < Index; i ++) {
			Temp = Temp.Next;
		}
		*/
		// look for the element in dual-direction
		// like a binary search
		if (Index < (Size >> 1) /* (Size / 2) */) {
			// search from the First
			Temp = First;
			// when Index = 0, skip that
			for (int i = 0; i < Index; i ++) {
				Temp = Temp.Next;
			}
		} else {
			// search from the Last
			Temp = Last;
			for (int i = Size - 1; i > Index; i --) {
				Temp = Temp.Previous;
			}
		}
		return Temp.Element;
		// return Temp; can't print it out, it needs toString() for class Node
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
		HandWrittenLinkedList02 List = new HandWrittenLinkedList02();
		List.Add("a");
		List.Add("b");
		List.Add("c");
		List.Add("d");
		List.Add("e");
		System.out.println(List);
		System.out.println(List.Get(0));
		System.out.println(List.Get(3));
	}
}
