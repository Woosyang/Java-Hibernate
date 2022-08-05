package Chapter02;

/**
 * Testing the auto type conversion and cast
 * @author Woo
 *
 */

public class TestTypeConvert {
	public static void main(String[] args) {
		int a = 324;
		long b = a;
		double c = b;
		// a = b; out of range
		// long = 3.34F; out of range
		float d = 234215L;
		
		//Exception
		byte b2 = 123; // -127~128;
		
		double x = 3.24;
		int nx = (int)x;
		char e = 'a';
		int f = e + 1;
		System.out.println(nx);
		System.out.println(f);
		System.out.println((char)f);
	}
}
