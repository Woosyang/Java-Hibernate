package Chapter13;

/**
 * Used with Person.xml to store the information
 * Used with AnalyzeXML.java
 * @author Woo
 *
 */
public class Person {
	private String Name;
	private int Age;
	
	public Person(){}
	
	public Person(String name, int age) {
		super();
		Name = name;
		Age = age;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}	
}
