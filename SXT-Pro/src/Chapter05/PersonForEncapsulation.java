package Chapter05;

/**
 * Still test the Encapsulation
 * @author Woo
 *
 */

public class PersonForEncapsulation {
	private int id;
	private String name;
	private int age;
	private boolean man;
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public void setAge(int age) {
		if (age >= 1 && age <= 110) {
		this.age = age;
		} else {
			System.out.println("Please input a correct age");
		}
	}

	public int getAge() {
		return this.age;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setMan(boolean man) {
		this.man = man;
	}
	
	public boolean isMan() { // like "void getMan"
		return man;
	}
}

