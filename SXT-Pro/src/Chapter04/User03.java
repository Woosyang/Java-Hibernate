package Chapter04;

/**
 * Testing the static block
 * @author Woo
 *
 */

public class User03 {
	int id;
	String name; // account
	String pwd; // password
	static String company; // title of the company
	
	static { // can't call the fields above
		System.out.println("execute the initialization of the class");
		company = "BJSXT";
		printCompany();
	}
	
	public static void printCompany(){
		System.out.println(company);
	}
	
	public static void main(String[] args) {
		User03 u3 = null;
	}
}
