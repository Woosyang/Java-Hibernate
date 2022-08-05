package Chapter09;

/**
 * Create a HashMap
 * fulfill the method put, add the key - value pairs
 * when the key - value is duplicated, overlap the value of the previous key - value
 * @author Woo
 *
 */
public class HandWrittenHashMap01 {
	NodeHash[] Table; // bucket array, core, name is Table
	int Size; // the amount of the key - value
	
	public HandWrittenHashMap01() {
		Table = new NodeHash[16]; // the size must be the power of 2 
	}
	public void Put(Object Key, Object Value) {
		// define a new Node
		NodeHash New = new NodeHash();
		New.Hash = MyHash(Key.hashCode() /* each object has its own hashCode */, Table.length);
		New.Key = Key;
		New.Value = Value;
		New.Next = null;
		NodeHash Temp = Table[New.Hash]; // Table[New.Hash] is the head of LinkedList
		// NodeHash Last = null;
		// boolean Flag = true;
		// use Temp to traverse
		if (Temp == null) {
			// if the element in the Table is null, put this Node Temp into the Table Array
			Table[New.Hash] = Temp;
		} else {
			// if the element is not null, then traverse the LinkedList 
			NodeHash Last = null; // for storing the tail of LinkedList, must be initialized
			boolean Flag = true;
			while (Temp != null) {
				if (Temp.Key.equals(Key)) {
					Temp.Value = Value;
					Flag = false;
					break; 
				} else {
					Last = Temp;
					Temp = Temp.Next; // if loop ends, Temp will be null
				}
			}
			if (Flag) {
				Last.Next = New;
			} 
		}
	}
	public static int MyHash(int V, int Length) {
		// System.out.println("Hash in MyHash(BitOperation): " + (V & (Length - 1))); // the efficiency is high
		// System.out.println("Hash in MyHash(Module): " + (V % (Length - 1))); // the efficiency is low
		return V & (Length - 1);
	}
	public static void main(String[] args) {
		HandWrittenHashMap01 M = new HandWrittenHashMap01();
		M.Put(10, "A");
		M.Put(20, "B");
		M.Put(30, "C");
		M.Put(20, "a");
		M.Put(10, "Call");
		M.Put(26, "You");
		// find the same HashCode
		for (int i = 10; i <= 130; i ++) {
			System.out.println(i + "---->" + MyHash(i, 16)); // HashCode = 10 => 10, 26, 42, 58
		}
		System.out.println(M);
	}
}
