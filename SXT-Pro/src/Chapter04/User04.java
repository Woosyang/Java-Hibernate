package Chapter04;

/**
 * Testing the passing value mechanism in arguments
 * @author Woo
 *
 */

public class User04 {
	int id; // id
	String name; // 
	String pwd; // password
	
	public User04(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public void testParameterTransfer01(User04 u) { // copy
		u.name = "AXK"; 
		u = null ;
		System.out.println(u);
	}
	
	public void testParameterTransfer02(User04 u) { // copy
		u = new User04(1010, "CXK");
		System.out.println(u.name);
	}
	
	public static void main(String[] args) {
		User04 u1 = new User04(1024, "BXK");
		u1.testParameterTransfer01(u1);
		System.out.println(u1.name);
		u1.testParameterTransfer02(u1);
		System.out.println(u1.name);
		System.out.println(u1); // not null, still exists
	}
}
