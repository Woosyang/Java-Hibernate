package Chapter15.Prototype;
import java.util.*;

/**
 * Object Example For Prototype(Deep Copy)
 * @author Woo
 *
 */
// Cloneable is a marketer interface
// Prototype realized by the clone() in class Object
// clone() is a native method, using Prototype Pattern
// has to implements the Cloneable interface (it is a standard)
public class DogPrototype02 implements Cloneable {
	private String Name;
	private Date BirthDay;
	
	// default constructor
	public DogPrototype02(String Name, Date BirthDay){
		this.Name = Name;
		this.BirthDay = BirthDay;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Object Obj = super.clone(); // call the clone() directly from the Object Obj
		// fulfill the deep copy
		DogPrototype02 D = (DogPrototype02) Obj;
		D.BirthDay = (Date) this.BirthDay.clone(); // clone() to the field
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
