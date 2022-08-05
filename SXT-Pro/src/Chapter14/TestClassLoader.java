package Chapter14;

/**
 * Hierarchy of the ClassLoader: BootStrap -> Extension -> Application(father -> child)
 * 
 * @author Woo
 *
 */
public class TestClassLoader {
	public static void main(String[] args) {
		System.out.println(ClassLoader.getSystemClassLoader()); // application
		System.out.println(ClassLoader.getSystemClassLoader().getParent()); // extension
		System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent()); // bootstrap -> null compiled with C ++
		
		// get the path of the current class file
		// delegation principle:
		/*
		 * Suppose you have an application-specific class, the first request of loading 
		 * this class will come to Application ClassLoader which will delegate to its parent 
		 * Extension ClassLoader which further delegates to Bootstrap class loader. 
		 * BootStrap will look for that class in rt.jar and since that class is not there, 
		 * a request comes to Extension class loader which looks on JRE/LIB/EXT directory 
		 * and tries to locate this class there, if the class is found there then 
		 * Extension class loader will load that class and Application class loader 
		 * will never load that class but if it's not loaded by extension class loader then
		 * Application class loader loads it from class path in Java.
		 * 
		 */
		System.out.println(System.getProperty("java.class.path"));
		System.out.println("=====================");
		String S = "Hahah."; 
		System.out.println(S.getClass().getClassLoader()); // null, not application from the defined String class in default package
		System.out.println(S); // not "A" from the defined String class in default package 
	}
}
