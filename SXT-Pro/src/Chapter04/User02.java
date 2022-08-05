package Chapter04;

/**
 * Testing the usage of static
 * @author Woo
 *
 */

public class User02 {
	int id;
	String name; // account
	String pwd; // password
	static String company = "BJSXT"; // title of the company
	
	public User02(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void login() {
		System.out.println("Log: " + name);
	}
	
	public static void printCompany() {
		// login() it will be an error when you call the non-static method
		System.out.println(company);
	}

	public static void main(String[] args) {
		User02 u = new User02(1024, "toad");
		User02.printCompany(); // printCompany(), call the method by the class
		User02.company = "BJalibaba"; 
		User02.printCompany();
	}
}
