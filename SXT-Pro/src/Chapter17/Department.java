package Chapter17;

/**
 * Java Bean, table refers to a class
 * Used With TestORM03
 * @author Woo
 * 
 */
public class Department {
	private int ID;
	private String Name;
	private String Address;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	
	public Department(int iD, String name, String address) {
		super();
		ID = iD;
		Name = name;
		Address = address;
	}
	
	public Department(String name, String address) {
		super();
		Name = name;
		Address = address;
	}
	
	public Department() {}
}
