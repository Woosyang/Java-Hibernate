package Chapter14;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import javassist.*;
import javassist.Modifier;

/**
 * Test The Javaassit API
 * @author Woo
 *
 */
public class TestJavaAssist02 {
	
	// test the method of the CtClass
	public static void Test01() throws Exception {
		ClassPool Pool = ClassPool.getDefault();
		CtClass C = Pool.get("Chapter14.Employee");
		byte[] Data = C.toBytecode();
		System.out.println(Arrays.toString(Data));
		System.out.println(C.getName()); // get the class name (package name + class name)
		System.out.println(C.getSimpleName()); // get just the class name
		System.out.println(C.getSuperclass()); // get the father class
		System.out.println(C.getInterfaces()); // get the interface
	}
	
	// test generating a new method
	public static void Test02() throws Exception {
		ClassPool Pool = ClassPool.getDefault();
		CtClass C = Pool.get("Chapter14.Employee");
		
		// one way to create the method
		CtMethod M01 = CtMethod.make("public int add(int a, int b) {return a + b;}", C);
		
		// another way to create the method
		//                          method type, int
		CtMethod M02 = new CtMethod(CtClass.intType, "sub", 
									//                $1              $2
									new CtClass[] {CtClass.intType, CtClass.intType}, C);
		M02.setModifiers(Modifier.PUBLIC);
		M02.setBody("{ System.out.println(\"Yo~\");"  
						+ "return $1 - $2;"
						+ "}"); // $0 -> this
		C.addMethod(M02);
		
		// use reflection to call the new method
		Class Clazz = C.toClass();
		Object Obj = Clazz.getConstructor().newInstance(); // use the default constructor of the employee to create a new object
		Method Mt = Clazz.getDeclaredMethod("sub", int.class, int.class);
		Object Re = Mt.invoke(Obj, 300, 100); // call the int sub() of the Employee.java
		System.out.println(Re);
	}
	
	// test modifying the method already existed
	public static void Test03() throws Exception {
		ClassPool Pool = ClassPool.getDefault();
		CtClass C = Pool.get("Chapter14.Employee");
		
		CtMethod Cm = C.getDeclaredMethod("SayHello", new CtClass[] {CtClass.intType});
		// will generate two lines before "System.out.println("Say Hi: " + a);" in the SayHello(int a)
		Cm.insertBefore("System.out.println($1); System.out.println(\"Start...\");"); // $1 -> a
		// will generate this line after "System.out.println("Say Hi: " + a);" in the SayHello(int a)
		Cm.insertAfter("System.out.println(\"Ok...\");"); 
		// will generate this line at line 33 in the Employee.java
		Cm.insertAt(33, "int b = 3; System.out.println(\"b = \" + b);"); // b = 3
		
		// use reflection
		Class Clazz = C.toClass();
		Object Obj = Clazz.getConstructor().newInstance();
		Method M = Clazz.getDeclaredMethod("SayHello", int.class);
		M.invoke(Obj, 300); // call the SayHello(int a) in Employee
	}
	
	// test generating a new field and setter & getter
	public static void Test04() throws Exception {
		ClassPool Pool = ClassPool.getDefault();
		CtClass C = Pool.get("Chapter14.Employee");
		// one way to create a new field
		// CtField  F1 = CtField.make("private int Sa", C);
		// another way to create a new field
		CtField F1 = new CtField(CtClass.intType, "Salary", C);
		F1.setModifiers(Modifier.PUBLIC);
		C.addField(F1);
		
		// get the specific field
		CtField Fd = C.getDeclaredField("Salary");
		System.out.println(Fd.getName()); // Salary
		
		// add the setter and getter
		C.addMethod(CtNewMethod.getter("getSalary", F1));
		C.addMethod(CtNewMethod.setter("setSalary", F1));
		
		// use the reflection to call the method above
		Class Clazz = C.toClass();
		Object Obj = Clazz.getConstructor().newInstance(); // create the object
		Method M1 = Clazz.getDeclaredMethod("setSalary", int.class);
		M1.invoke(Obj, 80000);
		Method M2 = Clazz.getDeclaredMethod("getSalary", null);
		System.out.println(M2.invoke(Obj, null)); // 80000
	}
	
	// test getting the constructor
	public static void Test05() throws Exception {
		ClassPool Pool = ClassPool.getDefault();
		CtClass C = Pool.get("Chapter14.Employee");
		CtConstructor[] Cs = C.getConstructors(); // get the constructors, similar with the reflection
 		for (CtConstructor Con : Cs) {
			System.out.println(Con.getLongName()); // get the constructor with parameters(default)
			// can modify the constructor by using insertBefore(), insertAfter()... like Test03()
		}
	}
	
	// test getting the annotaiton
	public static void Test06() throws Exception {
		ClassPool Pool = ClassPool.getDefault();
		CtClass C = Pool.get("Chapter14.Employee");
		Object[] Ant = C.getAnnotations();
		Author A = (Author) Ant[0]; // this Employee class only got one annotation, needs to be cast
		// if this class got multiple annotations applying to the class
		// the element of the Ant(Object[]) needs to be cast respectively corresponding to each annotation
		System.out.println("Name: " + A.Name());
		System.out.println("Year: " + A.Year());
	}
	public static void main(String[] args) throws Exception {
		// Test01(); // can't be used with the same class, it will cause frozen
		// Test02();
		// Test03();
		// Test04();
		// Test05();
		Test06();
	}
}
