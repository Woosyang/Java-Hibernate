package Chapter03;

/**
 * Testing the basic usage of the method
 * @author Woo
 *
 */

public class TestMethod {
	public static void main(String[] args) {
		//use the object to call the common method
		TestMethod a = new TestMethod();
		a.printSXT();
		a.add01(3, 4, 5);
		int c = 211 + a.add02(9, 8, 5); // print sum first
		System.out.println(c);
	}
	
	void printSXT() {
		System.out.println("BJSXT.");
		System.out.println("SHSXT~");
		System.out.println("GZSXT-");
	}
	
	void add01(int a, int b, int c) {
		int sum = a + b + c;
		System.out.println(sum);
	}
	
	int add02(int a, int b , int c) {
		int sum = a + b + c;
		System.out.println(sum);
		return sum;  // return has 2 functions: 1. end the operation of the method. 2. return a value
	}
}
