package Chapter09;
import java.util.*;

/**
 * Create my own HashSet(using HashMap)
 * have the method Add
 * have the method Remove
 * have the method Size
 * have the generic
 * @author Woo
 *
 */

public class HandWrittenHashSet<T> {
	// Object can't be change, only has the same value
	private static final Object Present = new Object();
	HashMap<T, Object> M; // has the type
	
	public HandWrittenHashSet() {
		M = new HashMap<>();
	}
	
	public void Add(T O) {
		M.put(O, Present);
	}
	
	public void Remove(T O) {
		M.remove(O);
	}
	
	public boolean IsEmpty() {
		return M.isEmpty();
	}
	
	public int Size() {
		return M.size();
	}
	
	@Override
	public String toString() {
		StringBuilder Sb = new StringBuilder();
		Sb.append("[");
		for (T Key : M.keySet()) {
			Sb.append(Key + ", ");
		}
		Sb.setCharAt(Sb.length() - 2, ']');
		return Sb.toString();
	}
	
	public static void main(String[] args) {
		HandWrittenHashSet<String> S = new HandWrittenHashSet<>();
		S.Add("T");
		S.Add("H");
		S.Add("E");
		S.Add("T");
		S.Remove("E");
		System.out.println(S);
		System.out.println("Size: " + S.Size());
		System.out.println(S.IsEmpty());
		S.Add("I");
		S.Add("S");
		// outcome will be no ordered
		System.out.println(S);
	}
}
