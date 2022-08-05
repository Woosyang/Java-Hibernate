package Chapter02;

/**
 * Testing the arithmetic operator
 * @author Woo
 *
 */

public class TestOperator01 {
	public static void main(String[] args) {
		byte a = 1;
		int b = 2;
		long c = 3;
		// byte d = a + b; error! type must be int
	    // int e = b + c; error! type must be long
		
		float d = 3.14F;
		float e = b + c; // 8 bits, enough
		// float f = d + 6.2; error! type must be double
		
		System.out.println(-9%5);
		
		// testing the auto-increment and the auto-decrement
		int f = 3;
		int g = f++; 
		// after executing, f = 3. 
		// assign the value to g first, then do the auto-increment
		System.out.println("f=" + f + "\n" + "g=" + g); // "\ng="
		
		f = 3;
		g = ++f;
		// after executing, f = 3.
		// do the auto-increment first, then assign the value to g
		System.out.println("f=" + f + "\ng=" + g);
		// So does the auto-decrement
		
		int o = 3;
		int p = 4;
		o += p; // o = o + p
		System.out.println("o=" + o + "\np=" + p);
		o = 3;
		o *= p + 3;// o = o * (p+3);
		System.out.println("o=" + o + "\np=" + p);
	}

}
