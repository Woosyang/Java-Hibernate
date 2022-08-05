package Chapter02;

/**
 * Testing the variable
 * @author Woo
 *
 */

public class TestVariable {
	
	int a; // member variable, can be initialized automatically
	static int size; // static variable, belongs to the class
	public static void main(String[] args) {
		{
			int age;
			age = 24; // local variable, belongs to the statement block
		}
		int salary = 30000; // local variable, belongs to the method
		int S = 8;
		System.out.println(S);
		int i;
		//int j = i + 5; // error, variable has not been initialized
	}

}
