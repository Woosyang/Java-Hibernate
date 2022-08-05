package Chapter07;

/**
 * Testing the copy of the array
 * @author Woo
 *
 */

public class TestArrays04 {
	public static void main(String[] args) {
		TestBasicCopy01();
		System.out.println("                     ");
		TestBasicCopy02();
		System.out.println("                     ");
		String[] a = {"NewYork", "London", "HongKong", "ShenZhen"};
		RemoveElement(a, 2);
		System.out.println("                     ");
		String[] b = {"A", "B", "C", "D"};
		ExtendRange(b);
		System.out.println("                     ");
		InsertElement(b, "E", 2);
	}
	
	public static void TestBasicCopy01() {
		String[] a = {"a", "b", "c", "d", "e"};
		String[] b = new String[10];
		// System.arraycopy(src, srcPos, dest, destPos, length);
		System.arraycopy(a, 2, b, 6, 3); /* copy from the seventh place whose index is 6 */
		for (int i = 0; i < b.length; i ++) {
			System.out.println(i + " -- " + b[i]);
		}
	}

	// Test how to remove an element from the array
	// It is still essentially a copy of the array
	public static void TestBasicCopy02() {
		String[] a = {"a", "b", "c", "d", "e"};
		// String[] b = new String[5]; try copy itself 
		System.arraycopy(a, 3 /* start from d */, a, 3 - 1, a.length - 3 /* copy 2 elements */);
		// remove the last duplicate e
		a[a.length - 1] = null;
		for (int i = 0; i < a.length; i ++) {
			System.out.println(i + " -> " + a[i]);
		}
	}
	
	// delete the element of designated index from the array and return the array
	// still is a copy of the array
	public static String[] RemoveElement(String[] a, int index) {
		System.arraycopy(a, index + 1, a, index, a.length - index - 1);
		a[a.length - 1] = null;
		for (int i = 0; i < a.length; i ++) {
			System.out.println(i + " => " + a[i]);
		}
	return a;
	}
	
	// extend the capacity of the array
	// basic idea : define an larger array first
	// then copy exact every elements of the original array to the larger one
	public static String[] ExtendRange(String[] a) {
		// String[] a = {"a", "b", "c"}; try to pass a String to this method
		String[] b = new String[a.length + 5 /* add the number U want */];
		System.arraycopy(a, 0, b, 0, a.length);
		for (String temp : b) {
			System.out.println(temp);
		}
		return b;
	}
	
	// insert an element as you want of the array
	public static String[] InsertElement(String[] a, String b, int index) {
		String[] c = new String[a.length + 1];	
		String[] d = new String[a.length + 1];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(a, 0, d, 0, a.length);
		c[index] = b;
		System.arraycopy(d, index, c, index + 1, d.length - index - 1);
		for (String temp : c) {
			System.out.println(temp);
		}
		return c;
	}
}
