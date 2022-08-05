package Chapter03;

/**
 * Testing the method overloading
 * @author Woo
 *
 */

public class TestOverload {
	public static void main(String[] args) {
		System.out.println(add(3,5)); // 8
		System.out.println(add(3,5,10)); // 18
		System.out.println(add(3.0,5)); // 8.0
		System.out.println(add(3,5.0)); // 8.0
	
	
	// the method overloading we have already seen
		System.out.println(); // no argument
		System.out.println(1); // argument belongs to a int type
		System.out.println(3.0); // argument belongs to a double type
	}
	
	/*sum method*/
	public static int add(int n1, int n2) {
		int sum = n1 + n2;
		return sum;
	}
	
	// error: only having the different names of the arguments is not the Overload
	/*
	public static int add(int n2, int n1) {
		int sum = n1 + n2;
		return sum;
	}
	*/
	
	// error: only having the different return value is not the Overload
	/*
	public static double add(int n1, int n2) {
		double sum = n1 + n2;
		return sum;
	}
	*/
	
	// sharing the same method name but having different numbers of the arguments can achieve the Overload
	public static int add(int n1, int n2, int n3) {
		int sum = n1 + n2 + n3;
		return sum;
	}
	
	// sharing the same method name but having a different type of the arguments can achieve the Overload
	public static double add(double n1, int n2) {
		double sum = n1 + n2;
		return sum;
	}
	
	// sharing the same method name but having a different order of the arguments can achieve the Overload
	public static double add(int n1, double n2) {
		double sum = n1 + n2;
		return sum;
	}
}
