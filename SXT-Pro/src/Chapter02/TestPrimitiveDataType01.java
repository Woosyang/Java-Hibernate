package Chapter02;

/**
 * Testing
 * @author Woo
 *
 */

public class TestPrimitiveDataType01 {
	public static void main(String[] args) {
		//Testing the int variable
		int a = 15;
		int b = 015; // Starting with the "0" is octal
		int c = 0x15; // Starting with the "0x" is hexadecimal
		
		//int d = 0b1101;  starting with the"0b" is binary
		//System.out.println(d); 13
		
		System.out.println(b);
		System.out.println(c);
		
		byte age = 30;
		short salary = 30000;
		int population = 2000000000; // the default of integer constants is a "int"
		long globalPopulation = 7400000000L; // ending with "L" means this is a "long"	
	}
}
