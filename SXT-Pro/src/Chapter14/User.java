package Chapter14;

/**
 * Used With TestClass
 * @author Woo
 *
 */
@MyTable("Tb_User")
public class User {
	private int ID;
	private String Name;
	private int Age;
	public int getID() {
		return ID;
	}
	
	public User() {}

	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	public void setName() {}
	
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}

	public User(int iD, String name, int age) {
		super();
		ID = iD;
		Name = name;
		Age = age;
	}
}
