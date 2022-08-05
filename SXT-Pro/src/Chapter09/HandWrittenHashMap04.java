package Chapter09;

/**
* create a hash map by using encapsulation and generic
* have the method Put
* have the method Remove
* have the method Get
* have the ability to extend the capacity of the bucket array
* @author Woo
*
* */
// T, E
public class HandWrittenHashMap04<E, T> {
	public static NodeHash Table[];
	public static int Length = 16;
	public int Size = 0;
	
	public HandWrittenHashMap04() {
		Table = new NodeHash[Length];
	}
	
	public static int MyHash(int V, int Length) {
		return V & (Length - 1);
	}
	
	public void Put(Object Key, Object Value) {
		// must be initialized
		NodeHash New = new NodeHash();
		New.Key = Key;
		New.Hash = MyHash(Key.hashCode(), Length);
		New.Value = Value;
		NodeHash Temp = Table[New.Hash];
		NodeHash Last = null;
		boolean Flag = true;
		if (Temp == null) {
			// head
			Table[New.Hash] = New;
			Size ++;
		} else {
			while (Temp != null) {
				if (Temp.Key.equals(Key)) {
					// overlap
					Temp.Value = Value;
					Flag = false;
					break;
				} else {
					Last = Temp;
					Temp = Temp.Next;
				}
			}
			if (Flag) {
				// add to the tail          
				Last.Next = New;
				Size ++;
			}
		}
		// extend the capacity of bucket array
		if (Size >= 0.75 * Length) {
			Resize();
		}
	}
	
	public Object Get(Object Key) {
		Object Value = null;
		boolean Flag = true;
		if (Key == null) {
			Flag = false;
		}
		if (Flag) {
			int Hash = MyHash(Key.hashCode(), Length);
			NodeHash Temp = Table[Hash];
			while (Temp != null) {
				if (Temp.Key.equals(Key)) {
					Value = Temp.Value;
					break;
				} else {
					Temp = Temp.Next;
				}
			}
		}
		return Value;
	}
	
	public void Remove(Object Key) {
		if (Key == null) {
			return;
		}
		int Hash = MyHash(Key.hashCode(), Length);
		NodeHash Temp = Table[Hash];
		NodeHash Tail = null;
		while (Temp != null) {
			if (Temp.Key.equals(Key) && Temp.Next != null) {
				NodeHash Down = Temp.Next;
				Temp.Hash = Down.Hash;
				Temp.Key = Down.Key;
				Temp.Value = Down.Value;                                                                                                                                                                                                                                                                                                             
				Temp.Next = Down.Next;
				Size --;
				break;
			}
			// head
			if (Temp.Key.equals(Key) && Temp.Next == null && Temp == Table[Hash]) {
				Table[Hash] = Temp.Next;
				// Table[Hash] = null;
				Size --;
				// break;
			}
			// tail
			// Tail must not be the null pointer
			if (Temp.Key.equals(Key) && Temp.Next == null && Tail != null) {
				Tail.Next = null;
				Size --;
				// break;
			}
			Tail = Temp;
			Temp = Temp.Next;
		}
	}
	
	// Reput the Key-Value pairs in to the new bucket array
	public static void Resize() {
		Length = 2 * Length;
		NodeHash[] NewT = new NodeHash[Length];
		for (int i = 0; i < Table.length; i ++) {
			NodeHash Temp = Table[i];
			// find the head
			// bucket array only store the address of the head
			if (Temp != null) {
				int Hash = MyHash(Temp.Key.hashCode(), Length);
				NewT[Hash] = Temp;
			}
		}
		Table = NewT;
	}
	
	@Override
	public String toString() {
		StringBuilder Sb = new StringBuilder();
		Sb.append("{");
		for (int i = 0; i < Length; i ++) {
			NodeHash Temp = Table[i];
			while (Temp != null) {
				Sb.append(Temp.Key + ": " + Temp.Value + ", ");
				Temp = Temp.Next;
			}
		}
		Sb.setCharAt(Sb.length() - 2, '}');
		return Sb.toString();
	}
	
	public static void main(String[] args) {
		HandWrittenHashMap04<Integer, String> M = new HandWrittenHashMap04<>();
		M.Put(1, "a");
		M.Put(2, "b");
		M.Put(3, "c");
		M.Put(4, "d");
		M.Put(5, "e");
		M.Put(6, "f");
		M.Put(7, "g");
		M.Put(8, "h");
		M.Put(10, "j");
		M.Put(11, "k");
		M.Put(12, "l");
		System.out.println("Size: " + M.Size); // 11
		System.out.println("Length: " + Length);
		M.Put(13, "m"); // Size = 12
		System.out.println("Length: " + Length);
		System.out.println(M.Get(4)); // d
		System.out.println(M.Get(100)); // null
		System.out.println(M);	
		M.Remove(4); // remove the head
		System.out.println(M);
		System.out.println("Size: " + M.Size); // Size = 11
		/*
		M.Remove(null);
		System.out.println(M);
		M.Remove(5);
		System.out.println(M);
		*/
		System.out.println(M.Get(4)); // null
		System.out.println(M.Get(null));
		// hash collision
		M.Put(26, "z");
		M.Put(90, "Z");
		System.out.println(M);
		M.Remove(90); // remove the tail
		System.out.println(M);
	}
}
