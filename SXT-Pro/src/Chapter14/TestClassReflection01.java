package Chapter14;
import java.lang.annotation.*;
import java.lang.reflect.*;

/**
 * Test The Ways Of Getting The class Object(java.lang.Class) 
 * For Any Type: class/interface/enum/annotation/primitive type/void
 * Understand The Mechanism Of The Reflection
 * The Functions Of The Reflection: 
 * 1. Load the class and get the class's info dynamically
 * 2. Create the object dynamically
 * 3. Call the methods and constructors of class or object dynamically
 * 4. Call and process the fields dynamically
 * 5. Get the info of the generic
 * 6. Process the annotation
 * @author Woo
 *
 */
@SuppressWarnings("all")
public class TestClassReflection01 {
	public static void main(String[] args) {
		String Path = "Chapter14.User"; // stands for an object
		// object is used to represent an encapsulate some info
		try {
			// Class<?> Clazz = Class.forName(Path);
			// when a class is loaded, JVM will create an object corresponding to this class
			// the whole info(field, method..) of this class will be loaded in to the object
			// this object is just like a mirror, we can have all the info of the class with this mirror -> reflection
			Class Clazz01 = Class.forName(Path); // use @SuppressWarnings("all") to clear the warnings
			System.out.println(Clazz01);
			System.out.println(Clazz01.hashCode());
			
			Class Clazz02 = Class.forName(Path);
			System.out.println(Clazz02);
			System.out.println(Clazz02.hashCode()); // same as the Clazz01.hashCode()
			System.out.println(Clazz02 == Clazz01); // true, each class only maps to only one object
			
			// get the object of the String
			Class StrClazz01 = String.class;
			Class StrClazz02 = Path.getClass(); // Path is a String
			System.out.println(StrClazz01 == StrClazz02); // true, same hash code
			
			// primitive data type
			Class IntClazz = int.class;
			
			// array
			int[] Arr01 = new int[10];
			Class ArClazz01 = Arr01.getClass();
			int[] Arr02 = new int[30];
			Class ArClazz02 = Arr02.getClass();
			System.out.println(ArClazz01);
			System.out.println(ArClazz01 == ArClazz02); // true, cuz they both got the same dimension
			int[][] Arr03 = new int[2][6];
			Class ArClazz03 = Arr03.getClass();
			System.out.println(ArClazz03);
			System.out.println(ArClazz03 == ArClazz02); // false, cuz the dimension of each other is different 
			double[] Arr04 = new double[10];
			Class ArClazz04 = Arr04.getClass();
			System.out.println(ArClazz04); 
			System.out.println(ArClazz04 == ArClazz02); // false, the data type is different
			
			// method
			Method[] Methods = Clazz01.getMethods(); // setName, getName, setAge....
			for (Method M : Methods) {
				System.out.println(M);
			}
			
			// annotation
			Annotation[] A = Clazz01.getAnnotations();
			for (Annotation a : A) {
				System.out.println(a); // @MyTable("Tb_User")
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
