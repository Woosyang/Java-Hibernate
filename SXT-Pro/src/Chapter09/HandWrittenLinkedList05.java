package Chapter09;

/**
* create a linked list by using encapsulation and generic
* have the method Get
* have the method Remove
* have the method Insert
* @author Woo
*
* */
public class HandWrittenLinkedList05<T> {
	private Node First; // null
	private Node Last; // null
	private int Size = 0;
	
	// []
	// ['a', 'b', 'c']
	public void Add(T Obj) {
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
			// N = Last; wrong !
		}
	}
	
	// revise from version02
	@SuppressWarnings("unchecked")
	public T Get(int Index) {
		// Node Temp = First; traverse one by one in linked list
		CheckRange(Index);
		Node Temp;
		Temp = Search(Index);
		// if Temp != null -> Temp.Element
		// if Temp = null -> null
		return Temp != null ? (T)Temp.Element : null;
	}
	
	public void Remove(int Index) { // no need to add one more CheckRange()
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
			if (Up == null /* Index == 0 */) {
				First = Temp.Next;
			}
			if (Down == null /* Index == Size - 1 */) {
				Last = Temp.Previous;
			}
		}
		Size --;
	}
	
	public void Insert(int Index, T Obj) { // no need to add one more CheckRange()
		Node New = new Node(Obj);
		Node Temp = Search(Index);
		if (Temp != null) {
			Node Up = Temp.Previous;
			if (Up != null) {
			Up.Next = New;
			New.Previous = Up;
			New.Next = Temp;
			Temp.Previous = New; // Temp.Previous is not the Up anymore
			}
			if (Up == null /* Index == 0 */) {
				New.Next = Temp;
				// New.Previous = Up; Up is null, New.Previous is also null
				Temp.Previous = New;
				First = New;
			}
		}
		Size ++;
	}
	
	private Node Search(int Index) {
		CheckRange(Index);
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
	
	// revise from version04
	private void CheckRange(int Index) {
		if (Index < 0 || Index > Size - 1) {
			throw new RuntimeException("Illegal Index -> " + Index);
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
		HandWrittenLinkedList05<String> List = new HandWrittenLinkedList05<>();
		List.Add("a");
		List.Add("b");
		List.Add("c");
		List.Add("d");
		List.Add("e");
		// List.Add(1);
		System.out.println(List);
		System.out.println(List.Get(0));
		System.out.println(List.Get(3));
		List.Remove(3);
		System.out.println(List);
		List.Remove(0); // remove the first one
		System.out.println(List);
		List.Remove(2); // remove the last one
		System.out.println(List);
		List.Insert(0, "j");
		List.Insert(0, "a");
		System.out.println(List);
		List.Insert(1, "d");
		System.out.println(List);
		List.Insert(3, "e");
		System.out.println(List);
	}
}
