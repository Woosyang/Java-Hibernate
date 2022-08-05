package Chapter09;

/**
 * Create a HashMap
 * fulfill the method put, add the key - value pairs
 * when the key - value is duplicated, overlap the value inside the previous key - value
 * fulfill toString(), for checking the information of the key - value
 * @author Woo
 *
 */
public class HandWrittenHashMap02 {
	NodeHash Table[];
	int Size;
	public HandWrittenHashMap02() {
		Table = new NodeHash[16];
	}
	public void Put(Object Key, Object Value) {
		NodeHash New = new NodeHash();
		New.Key = Key;
		New.Value = Value;
		New.Hash = MyHash(Key.hashCode(), Table.length);
		NodeHash Temp = Table[New.Hash];
		if (Temp == null) {
			Table[New.Hash] = New;
		} else {
			NodeHash Last = null;
			boolean Flag = true;
			while (Temp != null) {
				if (Key.equals(Temp.Key)) {
					Temp.Value = Value;
					Flag = false;
					break;
				} else {
					Last = Temp;
					Temp = Temp.Next;
				}		
			}
			if (Flag) {
				Last.Next = New; // Last.Next points to New, assign New to Last.New
			}
		}
	}
		
	public static int MyHash(int V, int Length) {
		return V & (Length - 1);
	}
	
	@Override
	public String toString() {
		StringBuilder Sb = new StringBuilder();
		Sb.append("{");
		// traverse the bucket array
		for (int i = 0; i < Table.length; i ++) {
			NodeHash Temp = Table[i];
			// traverse the LinkedList in the array
			while (Temp != null) {
				Sb.append(Temp.Key + ": " + Temp.Value + ", ");
				Temp = Temp.Next; // don't forget
			}
		}
		Sb.setCharAt(Sb.length() - 2, '}');
		return Sb.toString();
	}
	
	public static void main(String[] args) {
		HandWrittenHashMap02 M = new HandWrittenHashMap02();
		M.Put(10, "A");
		M.Put(20, "B");
		M.Put(30, "C");
		System.out.println(M);
		M.Put(20, "a");
		System.out.println("Overlap the value by the same key: ");
		System.out.println(M);
		M.Put(26, "You");
		System.out.println("Add the value by the same HashCode: ");
		System.out.println(M);
	}
}
