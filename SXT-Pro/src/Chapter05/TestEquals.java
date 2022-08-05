package Chapter05;

/**
 * Testing the Equals
 * @author Woo
 *
 */

public class TestEquals {
	public static void main(String[] args) {
		Object obj;
		String str; // Override the method "equals" in the class String like the class User
		User u1 = new User(1024, "Ted", "hama");
		User u2 = new User(1024, "Tod", "hama");
		System.out.println(u1 == u2);
		System.out.println(u1.equals(u2));
		String a = new String("RMB");
		String b = new String("RMB");
		System.out.println(a == b);
		System.out.println(a.equals(b));
	}
}

class User {
	int id;
	String name;
	String pwd;
	public User(int id, String name, String pwd) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj; // cast
		if (id != other.id)
			return false;
		return true; // when the argument satisfies all the conditions above, the method will return a true value
	}
}