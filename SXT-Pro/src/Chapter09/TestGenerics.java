package Chapter09;

/**
 * Test Generic
 * @author Woo
 *
 */

public class TestGenerics {
	public static void main(String[] args) {
		MyCollection Mc = new MyCollection();
		//   value, index
		Mc.Set("Nan", 0);
		Mc.Set(1, 1);
		// if you want a get those objects above,
		// you have to do the cast otherwise you can't get the element you want
		String A = (String) Mc.Get(0);
		Integer B = (Integer) Mc.Get(1);
		System.out.println(A);
		System.out.println(B);
		// System.out.println(Mc.Get(0));
		// System.out.println(Mc.Get(1));
		/*if we want a efficient way to get the data, we have to make MyCollection to store only one type data*/
		MyCollectionPro<String> M = new MyCollectionPro<>();
		M.Set("Hao", 0);
		M.Set("De", 1);
		String C = M.Get(0);
		String D = M.Get(1);
		System.out.println(C);
		System.out.println(D);
		// System.out.println(M.Get(0));
		// System.out.println(M.Get(1));
	}
}

class MyCollection { // E stands for generic
	Object[] Obj = new Object[5];
	
	public void Set(Object O, int index) {
		Obj[index] = O;
	}
	
	public Object Get(int index) {
		return Obj[index];
	}	
}

class MyCollectionPro <T> /*template*/ {
	// everything in java is all an object
	Object[] Obj = new Object[3]; // remains the same as the MyCollection does, can't be T
	
	public void Set(T J, int index) {
		Obj[index] = J;
	}
	
	@SuppressWarnings("unchecked")
	public T Get(int index) {
		return (T) Obj[index]; // needs to be cast, child is not father, can assign child to father
	}
}