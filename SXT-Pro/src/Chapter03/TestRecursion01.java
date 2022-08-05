package Chapter03;

/**
 * Testing the recursion
 * @author Woo
 *
 */

public class TestRecursion01 {
	public static void main(String[] args) {
		a();
	}
	
	static int count = 0;
	static void a() {
		System.out.println("a");
		//b();
		count ++;
		if (count < 5){
		a();// run out of the storage causing the exception
		} else {
		return; // ending the recursion
		}
	}
	
	static void b() {
		System.out.println("b");
	}
}
