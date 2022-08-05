package Chapter09;

/**
 * Create the ArrayList by using encapsulation and generic
 * have the method Add
 * have the method Get
 * have the method Remove
 * have the ability to extend its capacity
 * @author Woo
 *
 */
public class HandWrittenArrayListPro<T> {
	private Object[] ElementData;
	private int Size = 0;
	private static final int Default_Capacity =  10;
	
	public HandWrittenArrayListPro() {
		ElementData = new Object[Default_Capacity]; // original capacity
	}
	
	public HandWrittenArrayListPro(int Capacity) {
		if (Capacity < 0) {
			throw new RuntimeException("Capacity can't be negative");
		} else if (Capacity == 0) {
			// assume capacity is the default number
			ElementData = new Object[Default_Capacity];
		} else {
			ElementData = new Object[Capacity];
		}
	}
	
	public void Add(T O) {
		// when to extend the capacity
		if (Size == ElementData.length) {
			// how to extend
			// right shift 1 bit <-> divided by 2
			// bitwise operation has the lower priority than arithmetic 
			// 10 + 10 / 2
			Object[] NewArray = new Object[ElementData.length + (ElementData.length >> 1)];
			System.arraycopy(ElementData, 0, NewArray, 0, ElementData.length);
			//                 src           des                num
			ElementData = NewArray;
		}
		// at first, Index = 0, then Index becomes to 1
		// assign the value first and then increase the Index
		// ElmentData[Size] = O; Size = 0 -> Index = 0
		// Size ++; right now the array has one element
		ElementData[Size ++] = O;
	}
	
	@SuppressWarnings("unchecked")
	public T Get(int Index) {
		CheckRange(Index);
		return (T) ElementData[Index];
	}
	
	public void Set(T O, int Index) {
		CheckRange(Index);
		ElementData[Index] = O;
	}
	
	public void CheckRange(int Index) {
		// make a decision whether the Index is legal
		// [0, Index)
		if (Index < 0 || Index >= Size) {
			throw new RuntimeException("Illegal Index: " + Index);
		}
	}
	
	public void Remove(T Ele) {
		// match every element in the array to the target
		// when the target be matched at once, return
		for (int i = 0; i < Size; i ++) {
			// in the Collection, always use .equals() to compare the elements inside itself
			if (Ele.equals(ElementData[i])) {
				Remove(i);
			} 
		}
		// if there is no element be matched, loop will keep running until it ends
	}
	
	public void Remove(int Index) {
		CheckRange(Index);
		// A, B, C, D -> delete B -> copy the array from C to D to itself
		// 0, 1, 2, 3
		// the position starts at the Index of B
		int num = ElementData.length - Index - 1;
		// num can't be negative
		// the array may have null element, so the Size is not the length
		// toString() only display the element is not null
		if (num > 0) {
			System.arraycopy(ElementData, Index + 1, ElementData, Index, num);
			// ElementData[Size - 1] = null;
			// Size --;
			// eg: A, B, C, D -> delete B
			//     0, 1, 2, 3 -> after copy
			//     A, C, D, D -> set the final D to null
		} /* 
			else {
				// delete the last one
				ElementData[Size - 1] = null;
				Size --;
			}
		*/
		// Size decreases first
		ElementData[-- Size] = null;
	}
	
	public int Size() {
		return Size;
	}
	
	public boolean IsEmpty() {
		return Size == 0 ? true : false;
	}
	
	@Override
	public String toString() {
		StringBuilder Sb = new StringBuilder();
		Sb.append("[");
		// not i < ElementData.length because the array may not be full
		for (int i = 0; i < Size; i ++) {
			Sb.append(ElementData[i] + ", ");
		}
		Sb.setCharAt(Sb.length() - 2, ']');
		return Sb.toString();
	}
	
	public static void main(String[] args) {
		HandWrittenArrayListPro<String> A = new HandWrittenArrayListPro<>();
		// HandWrittenArrayListPro B = new HandWrittenArrayListPro(- 1);
		/* test the extension
		for (int i = 0; i < 20; i ++) {
			A.Add(1);
		}
		*/
		A.Add("S");
		A.Add("U");
		A.Add("P");
		System.out.println(A);
		A.Remove("P");
		A.Add("S");
		A.Add("P");
		A.Add("E");
		A.Add("N");
		A.Add("D");
		System.out.println(A);
		A.Remove(3);
		System.out.println(A);
		System.out.println(A.Size);
		System.out.println(A.IsEmpty());
		System.out.println(A.Get(2));
		A.Set("M", 2);
		System.out.println(A);
	}
}
