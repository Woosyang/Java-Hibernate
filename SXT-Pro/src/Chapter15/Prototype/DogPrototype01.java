package Chapter15.Prototype;
import java.io.*;
import java.util.*;

/**
 * Object Example For Prototype(Shallow Copy)
 * @author Woo
 *
 */
// Cloneable is a marketer interface
// Prototype realized by the clone() in class Object
// clone() is a native method, using Prototype Pattern
// has to implements the Cloneable interface (it is a standard)
@SuppressWarnings("all")
public class DogPrototype01 implements Cloneable, Serializable {
	private String Name;
	private Date BirthDay;
	
	// default constructor
	public DogPrototype01(String Name, Date BirthDay){
		this.Name = Name;
		this.BirthDay = BirthDay;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Object Obj = super.clone(); // call the clone() directly from the Object Obj
		return Obj;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Date getBirthDay() {
		return BirthDay;
	}

	public void setBirthDay(Date birthDay) {
		BirthDay = birthDay;
	}
	
	@Override
	public String toString() {
		return "Dog-" + this.Name;
	}
}
