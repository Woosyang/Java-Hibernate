package Chapter09;

/**
 * create a linked list
 * have the method Get
 * have the method Remove
 * @author Woo
 *
 * */

public class HandWrittenLinkedList03 {
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
	
	// revise from version02
	public Object Get(int Index) {
		// Node Temp = First; traverse one by one in linked list
		Node Temp;
		if (Index < 0 || Index > Size - 1) {
			throw new RuntimeException("Illegal Index -> " + Index);
		}
		Temp = Search(Index);
		// if Temp != null -> Temp.Element
		// if Temp = null -> null
		return Temp != null ? Temp.Element : null;
	}
	
	public void Remove(int Index) {
		Node Temp = Search(Index);
		if (Temp != null) {
			Node Up = Temp.Previous;
			Node Down = Temp.Next;
			// should consider whether the node is first one
			if (Up != null) {
				Up.Next = Down; // if Down = null, then there are only 2 Nodes
			}
			if (Down != null) { // means there are more than 2 Nodes
				Down.Previous = Up;
			}
			if (Index == 0) {
				First = Temp.Next;
			}
			if (Index == Size - 1) {
				Last = Temp.Previous;
			}
		}
		Size --;
	}
	
	public Node Search(int Index) {
		Node Temp;
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
			return Temp; // return Temp; can't print it out, it needs toString() for class Node
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
		HandWrittenLinkedList03 List = new HandWrittenLinkedList03();
		List.Add("a");
		List.Add("b");
		List.Add("c");
		List.Add("d");
		List.Add("e");
		System.out.println(List);
		System.out.println(List.Get(0));
		System.out.println(List.Get(3));
		List.Remove(3);
		System.out.println(List);
		List.Remove(0); // remove the first one
		System.out.println(List);
		List.Remove(2); // remove the last one
		System.out.println(List);
	}
}
