package Chapter14;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

/**
 * Compare The Performance Of Calling the method from the class In Normal Way, Reflection And Reflection With SetAccessible(true)
 * Use Reflection To Get The Generic -> ParameterizedType, eg: Map<Integer, String> -> get Integer, String
 * Use Reflection To Read The Annotation Of The Class
 * @author Woo
 *
 */
@SuppressWarnings("all")
public class TestClassReflection04 {
	
	// normal way
	public static void Test01() {
		User U = new User();
		long StartTime = System.currentTimeMillis();
		// execute 10000 times
		for (int i = 0; i < 10000000; i ++) {
			U.getName();
		}
		long EndTime = System.currentTimeMillis();
		System.out.println("Using General Way To Call The getName() 10000 Times, Cost " + (EndTime - StartTime) + "ms");
	}
	
	// reflection without setAccessible(true)
	public static void Test02() {
		User U = new User();
		Class Clazz = U.getClass();
		try {
			Method M = Clazz.getDeclaredMethod("getName", null);
			// M.setAccessible(true); // security check
			long StartTime = System.currentTimeMillis();
			for (int i = 0; i < 10000000; i ++) {
				M.invoke(U, null);
			}
			long EndTime = System.currentTimeMillis();
			System.out.println("Using Reflection Without 'setAccessible(true)' To Call The getName() 10000 Times, Cost " + (EndTime - StartTime) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	// reflection using setAccessible(true)
	public static void Test03() {
		User U = new User();
		Class Clazz = U.getClass();
		try {
			Method M = Clazz.getDeclaredMethod("getName", null);
			M.setAccessible(true); // security check
			long StartTime = System.currentTimeMillis();
			for (int i = 0; i < 10000000; i ++) {
				M.invoke(U, null);
			}
			long EndTime = System.currentTimeMillis();
			System.out.println("Using Reflection To Call The getName() 10000 Times, Cost " + (EndTime - StartTime) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	// method with Generic, use reflection to get the <String, User>
	public static void Test04(Map<String, User> M, List<User> L) {
		System.out.println("God Bless");
	}
	
	// method with Generic and returns the Generic, use reflection to get the <Integer, User>
	public static Map<Integer, User> Test05() {
		System.out.println("Study !!!");
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		// setAccessible(true) can increase the efficiency but using reflection to call the methods is not the best choice
		Test01(); // best
		Test02(); // worst
		Test03(); // medium
		System.out.println("------------------------------------------");
		
		// get the generic of the specific method (no return)
		Method M = TestClassReflection04.class.getMethod("Test04", Map.class, List.class); // get the method "Test04()"
		Type[] T = M.getGenericParameterTypes(); // Map<String, User>, List<User>
		System.out.println("For Test04: ");
		for (Type t : T) {
			System.out.println("# " + t);
			// use ParameterizedType
			if (t instanceof ParameterizedType) {
				// cast, get the generic, String, User
				Type[] GenrTypes = ((ParameterizedType) t).getActualTypeArguments();
				for (Type Gt : GenrTypes) {
					System.out.println("The Type Of The Generic: " + Gt);
				}
			}
		}
		
		// get the generic of the method having a returned data
		M = TestClassReflection04.class.getMethod("Test05", null);
		Type ReturnType = M.getGenericReturnType(); // Map<Integer, User>, only return one object
		// use ParameterizedType
		System.out.println("For Test05: ");
		System.out.println("# " + ReturnType);
		if (ReturnType instanceof ParameterizedType) {
			// cast, get the generic, Integer, User
			Type[] GenerTypes = ((ParameterizedType) ReturnType).getActualTypeArguments();
			for (Type Gt : GenerTypes) {
				System.out.println("The Type Of The Generic: " + Gt); // Integer, User
			}
		}
		
		// use reflection api to read the annotation (TestAnnotation02)
		System.out.println("Annotation:  ");
		Class Clazz = Class.forName("Chapter14.Student");
		// get all the annotations applying to the class
		Annotation[] Ant = Clazz.getAnnotations(); // get all of the annotations
		for (Annotation A : Ant) {
			System.out.println(A); // print out all of the annotations
		}
		
		// get the specific field of the annotation applying to the class, eg value() -> Tb_Student
		MyTable Mt = (MyTable)Clazz.getAnnotation(MyTable.class);
		System.out.println(Mt.value()); // Annotation Table only got one field
		
		// get the specific annotation of the field from the class, eg SName
		Field F = Clazz.getDeclaredField("SName"); // ID, Age
		MyField Mf = F.getAnnotation(MyField.class); // reflection
		System.out.println("ColName: " + Mf.ColName() + " --- " + "Type: " + Mf.Type() + " --- " + "Len: " + Mf.Length());
		
		// get the specific annotation of the method from the class, eg setID(int iD)
		Method Me = Clazz.getDeclaredMethod("setID", int.class);
		// get MyMethod object
		MyMethod MT = Me.getAnnotation(MyMethod.class);
		System.out.println(MT); 
		// if value() is an array in the Annotation, it will print out the address
		System.out.println("Field Of This Annotation: " + MT.value()); // Number
	}
}
