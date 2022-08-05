package Chapter09;

/**
 * Create a HashMap
 * fulfill the method put, add the key - value pairs
 * when the key - value is duplicated, overlap the value inside the previous key - value
 * fulfill toString(), for checking the information of the key - value
 * fulfill the method get
 * @author Woo
 *
 */
public class HandWrittenHashMap03 {
	NodeHash Table[];
	int Length = 16;
	// int Size = 0;
	
	public HandWrittenHashMap03() {
		Table = new NodeHash[Length];
	}
	
	public static int MyHash(int V, int Length) {
		return V & (Length - 1);
	}
	
	public void Put(Object Key, Object Value) {
		NodeHash New = new NodeHash();
		New.Key = Key;
		New.Value = Value;
		// int Num = Length;
		New.Hash = MyHash(Key.hashCode(), Length);
		NodeHash Temp = Table[New.Hash];
		if (Temp == null) {
			Table[New.Hash] = New;
			// Size ++;
		} else {
			boolean Flag = true;
			NodeHash Last = null;
			while (Temp != null) {
				if (Temp.Key.equals(Key)) {
					Temp.Value = Value;
					Flag = false;
					break;
				} else {
					Last = Temp;
					Temp = Temp.Next;
					// Size ++;
				}
			}
			if (Flag) {
				Last.Next = New;
			}
		}	
		// extend the capacity of the bucket array
		/* not a good idea
		if (Size >= 0.75 * Length) {
			Length = Length * 2;
			NodeHash NewT[] = new NodeHash[Length];
			System.arraycopy(Table, 0, NewT, 0, Num);
			Table = NewT;
		}
		*/
	}
	
	public Object Get(Object Key) {
		Object Value = null;
		boolean Flag = true;
		if (Key == null) {
			Flag = false;
		}
		if (Flag) {
			int Hash = MyHash(Key.hashCode(), Length);
			if (Table[Hash] != null) {
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
			}
		return Value;
	}
	
	public void Remove(Object Key) { // Key can be Integer, String.....
		if (Key == null) {
			return;
		}
		int Hash = MyHash(Key.hashCode(), Length);
		NodeHash Last = null;
		if (Table[Hash] != null) {
			NodeHash Temp = Table[Hash];
			while (Temp != null) {
				if (Temp.Key.equals(Key) && Temp.Next != null) {
					NodeHash Down = Temp.Next;
					Temp.Hash = Down.Hash;
					Temp.Key = Down.Key;
					// Temp = Down; wrong!
					Temp.Next = Down.Next;
					break;
				}
				// only one Node in the Linked List
				if (Temp.Key.equals(Key) && Temp.Next == null && Temp == Table[Hash]) {
					// change the address of Temp doesn't change the Table[Hash]!
					Table[Hash] = null;
					break;
				}
				// right now Temp is the tail and Last is the previous one to the Temp
				if (Temp.Key.equals(Key) && Temp.Next == null && Last != null) {
					Last.Next = null;
				}
				Last = Temp;
				Temp = Temp.Next;
			}
		}
	} 
	
	@Override
	public String toString() {
		StringBuilder Sb = new StringBuilder();
		Sb.append("{");
		for (int i = 0; i < Length; i ++) {
			if (Table[i] != null) {
				NodeHash Temp = Table[i];
				while (Temp != null) {
					Sb.append(Temp.Key + ": " + Temp.Value + ", ");
					Temp = Temp.Next;
				}
			}
		}	
		Sb.setCharAt(Sb.length() - 2, '}');
		return Sb.toString();
	}
	
	public static void main(String[] args) {
		HandWrittenHashMap03 M = new HandWrittenHashMap03();
		M.Put(10, "T"); // 10
		M.Put(20, "A"); // 4
		M.Put(30, "T"); // 14
		// System.out.println("Size: " + M.Size);
		M.Put(30, "O");
		M.Put(26, "W"); // 10
		M.Put(15, "D"); // 15
		M.Put(25, "A"); // 9
		M.Put(40, "Q"); // 8
		M.Put(60, "L"); // 12
		M.Put(70, "H"); // 6
		M.Put(80, "A"); // 0
		M.Put(90, "I"); // 10
		M.Put(100, "M"); // 4
		M.Put(110, "S"); // 14
		M.Put(120, "I"); // 8
		System.out.println(M);
		// System.out.println("Size: " + M.Size);
		// System.out.println("Length: " + M.Length);
		System.out.println(M.Get(20)); // A
		System.out.println(M.Get(120)); // I
		System.out.println(M.Get(30)); // O
		System.out.println(M.Get(25));
		M.Remove(90); // can be deleted
		M.Remove(120);
		// M.Remove(null);
		System.out.println(M);
		// M.Remove(26);
		// System.out.println(M);
		System.out.println(M.Get(null));
	}
}
