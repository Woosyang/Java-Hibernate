package Chapter13;

/**
 * Reflection: the process of analyzing and modifying 
 * 			   all the capabilities of a class at runtime.
 * 			   mapping the fields, methods, constructor, class name of the class to an java object
 * important way to get the class -> Class.forName("package name.class name")
 * with this way, we can create objects dynamically
 * A class is an abstraction of an object, and an object is an instance of the class
 * @author Woo
 *
 */
public class TestReflection {
	public static void main(String[] args) throws Exception {
		// three ways to get a class
		
		// 1. Object.getClass(), depends one class Iphone
		Class C01 = new Iphone().getClass();
		Iphone Iph = new Iphone();
		C01 = Iph.getClass();
		
		// 2. Class.class(), depends on class Iphone
		Class C02 = Iphone.class;
		
		// 3. Class.forName("package name.class name") "package name.class name" -> completed file path
		// reduce the coupling -> no need to create the class first
		Class C03 = Class.forName("Chapter13.Iphone");
		
		// create objects
		/*
		Iphone Ip01 = (Iphone)C03.newInstance(); // old JDK not recommend
		System.out.println(Ip01);
		*/
		// new way-> recommend
		Iphone Ip01 = (Iphone)C03.getConstructor().newInstance();
		System.out.println(Ip01);
	}
}

class Iphone {
	public Iphone() {}
	public String toString() {
		return "Iphone 12 Pro Max";
	}
}