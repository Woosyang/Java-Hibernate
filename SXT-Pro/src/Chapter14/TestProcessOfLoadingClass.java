package Chapter14;

/**
 * ClassLoader in Java is a class that is used to load class files in Java. 
 * Java code is compiled into a class file by javac compiler and JVM executes 
 * Java program by executing byte codes written in the class file.
 * Three major steps for loading a class
 * 1. Loading: Load the byte code of the class file into the memory,
 * 			   convert the static data into the runtime data structure 
 * 			   in the method area, and generate a Class object representing 
 * 			   this class in the heap.
 * 
 * 2. Linking: Combine the binary code of the class into the run time state JVM 
 * 			   (Verification, Preparation, Resolution)
 * 
 * 3. Initialization: Executing the <clinit>() method of the class constructor.
 * 					  When initializing a class, if you find that the parent class has not been initialized, 
 * 					  you need to start the initialization of the parent class first.
 * @author Woo
 *
 */
public class TestProcessOfLoadingClass {
	static {
		System.out.println("Static Initialization Test");
	}
	public static void main(String[] args) {
		/* without the A_Dad class
		// output order: static block -> constructor of A(initialized) -> A.a (200) after the initialization of A
		A a = new A();
		System.out.println(A.a);
		*/
		
		/*
		// with static block, output in main()
		// output order: static block of this class -> main() -> static block of A_Dad -> static block of A (static block before initialization)
		//											-> constructor of A_Dad(initialized) -> constructor of A(initialized)  
		//											-> A.a	 
		System.out.println("ooo");
		A a = new A();
		System.out.println(A.a);
		A b = new A();
		*/
		
		// passive reference, will not cause the initialization of A
		System.out.println(A.max); // constant
		A[] arrA = new A[10]; // array
		// B extends A, cause the initialization of A and A_Dad
		System.out.println(B.a); // will not cause the initialization of B
		
		// active reference, will cause the initialization of A
		new A();
		System.out.println(A.a); // call the static variable of A
		try {
			Class Clazz = Class.forName("Chapter14.A"); // reflection
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class A extends A_Dad {
	public static int a = 100;
	public static final int max = 50;
	static {
		System.out.println("Static Initialization A");
		a = 200;
	}
	
	public A() {
		System.out.println("Create An A");
	}
}

class A_Dad {
	public A_Dad() {
		System.out.println("Create An A_Dad");
	}
	static {
		System.out.println("Static Initialization A_Dad");
	}
}

class B extends A {
	static {
		System.out.println("Static Initialization B");
	}
}