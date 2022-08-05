package Chapter14;
import java.lang.reflect.*;

/**
 * Use Reflection API, get the info of the class(its name, its method, its constructor...)
 * Used With User
 * @author Woo
 *
 */
@SuppressWarnings("all")
public class TestClassReflection02 {
	public static void main(String[] args) {
		String Path = "Chapter14.User";
		try {
			Class Clazz = Class.forName(Path);
			
			// get the name of the class
			System.out.println(Clazz.getName()); // get the completed path: package name + class name
			System.out.println(Clazz.getSimpleName()); // get just the class name, User
			
			// get the feild of the class
			System.out.println("get the feilds of the class: ");
			Field[] F = Clazz.getFields();
			System.out.println(F.length); // 0 -> can only get the public feilds of the corresponding class
			F = Clazz.getDeclaredFields(); // get all of the feilds of the corresponding class, including public, private
			System.out.println(F.length); // 3, private int ID, int Age, String Name
			for (Field f : F) {
				System.out.println("Field: " + f); 
			}
			
			// get the method of the class
			System.out.println("get the method of the class");
			Method[] M = Clazz.getMethods(); // can only get the public methods
			M = Clazz.getDeclaredMethods(); // get all of the methods in the corresponding class
			for (Method m : M) {
				System.out.println("Method: " + m);
			}
			System.out.println("Use getDeclaredMethod(name, parameterTypes) to obtain the specific method: ");
			// eg getName()                    parameterTypes null if there is no parameter inside the method 
			Method m = Clazz.getDeclaredMethod("getName", null);
			// if the method has parameters inside, then parameterTypes must be the object of the corresponding parameter type 
			// parameterTypes can avoid the polymorphism when doing the reflection to the method
			// eg: setName(), setName(String Name) are different 
			m = Clazz.getDeclaredMethod("setName", String.class); // maps to the setName(String Name) Not SetName()
			System.out.println(m);
			
			// get the constructor of the class
			System.out.println("get the constructor of the class: ");
			Constructor[] Con = Clazz.getConstructors(); // only public
			Con = Clazz.getDeclaredConstructors(); // all of the constructors
			for (Constructor con : Con) {
				System.out.println("Constructor: " + con);
			}
			// get the specifc constructor
			Constructor C = Clazz.getDeclaredConstructor(null); // deafault constructor
			System.out.println(C); // no parameter inside
			C = Clazz.getConstructor(int.class, String.class, int.class);
 			System.out.println(C); // get the constructor with parameters
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
