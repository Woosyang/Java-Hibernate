package Chapter04;

/**
 * Testing the overload of the constructor
 * @author Woo
 *
 */

public class User01 {
	int id; // id
	String name; // account
	String pwd; // password
	
	public User01() {} // constructor I
	
	public User01(int id, String name) {
		super(); // the first statement of the constructor always is super();
		this.id = id; // principle of proximity, this means the object that has been created
		this.name = name; // principle of proximity
	}
	
	public User01(int id, String name, String pwd) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
	}
	
	public static void main(String[] args) {
		User01 u1 = new User01();
		User01 u2 = new User01(1926, "Elder Jiang");
		User01 u3 = new User01(1989, "Song", " + 1 ");
		System.out.println(u2.id);
	}
}
