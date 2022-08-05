package Chapter14;
import java.lang.reflect.*;

/**
 * Use Reflection API To Call The Constructor, Method, Field Dynamically
 * Used With User
 * @author Woo
 *
 */
public class TestClassReflection03 {
	public static void main(String[] args) {
		// call the constructor dynamically
		String Path = "Chapter14.User";
		try {
			Class<User> Clazz = (Class<User>) Class.forName(Path); // cast
			
			// use reflection api to call the constructor to create the object 
			User U = Clazz.newInstance(); // call the default constructor of the User
			System.out.println(U);
			// when you create a java bean, you must have a default constructor
			// otherwise newInstance() will throws Exception
			
			// call the constructor has parameters inside
			Constructor<User> C = Clazz.getConstructor(int.class, String.class, int.class);
			U = C.newInstance(101, "Sam", 18);
			System.out.println(U.getName()); // Sam
			
			// use reflection api to call the normal method
			// U.setName("Sim"); // call the method directly, replaced by the code below
			Method M = Clazz.getDeclaredMethod("setName", String.class); // method and parameter are both varible
			M.invoke(U, "Sim"); // dynamic
			System.out.println(U.getName()); // Sim
			
			// use reflection api to set the field
			Field F = Clazz.getDeclaredField("Name"); // get the field of the User, String Name
			// no security check -> increase the efficiency
			F.setAccessible(true); // cuz the field is private can't be accessed, use 
			// set the "Sum" to the field "Name"
			// set(obj, para)
			F.set(U, "Sum"); // use reflection to set the field
			System.out.println("Java Bean: " + U.getName());  // get the field directly
			System.out.println("Reflection: " + F.get(U)); // Sum, use reflection to get the field
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
