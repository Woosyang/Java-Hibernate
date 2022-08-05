    package Chapter02;
import java.util.*;

/**
 * Testing the input of the keyboard
 * @author Woo
 *
 */

public class TestScanner {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("please type your name: ");
		String name = scanner.nextLine();
		System.out.println("please type your favor: ");
		String favor = scanner.nextLine();
		System.out.println("please type your age: ");
		int age = scanner.nextInt();
		
		System.out.println("------------");
		System.out.println(name);
		System.out.println(favor);
		System.out.println(age);
		System.out.println("existing days: " + 365 * age);
		System.out.println("the rest of days: " + 365 * (100 - age));
	}
}
