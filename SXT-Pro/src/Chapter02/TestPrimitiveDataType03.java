package Chapter02;

/**
 * Testing the char and the boolean
 * @author Woo
 *
 */

public class TestPrimitiveDataType03 {
	public static void main(String[] args) {
		char a = 'T';
		char b = 'Íõ';
		char c = '\u0061';
		System.out.println(c); // a
		
		// escape character
		System.out.println('a' + 'b'); // numbers
		System.out.println("" + 'a' + 'b'); // "" can show the character
		System.out.println("" + 'a' + '\n' + 'b');
		System.out.println("" + 'a' + '\t' + 'b'); // Tab
		System.out.println("" + 'a' + '\'' + 'b');
		
		// String is a sequence of char
		String d = "abc";
		// char d = 'abc'; //error
		
		// Testing the boolean
		boolean man = true;
		
		if(man){   // man == true Less is More
			System.out.println("male"); 
		}
	}

}
