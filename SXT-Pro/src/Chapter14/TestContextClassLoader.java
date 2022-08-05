package Chapter14;

/**
 *  class loader in a JVM follows a hierarchical model such that every class loader 
 *  has a single parent with the exception of the bootstrap class loader.
 *	However, sometimes when JVM core classes need to dynamically load classes or resources 
 *	provided by application developers, we might encounter a problem.
 *  For example, in JNDI the core functionality is implemented by bootstrap classes in rt.jar. 
 *  But these JNDI classes may load JNDI providers implemented by independent vendors 
 *  (deployed in the application class path). This scenario calls for the bootstrap class 
 *  loader(parent class loader) to load a class visible to application loader(child class loader).
 *	
 *	J2SE delegation doesn't work here and to get around this problem, we need to find 
 *	alternative ways of class loading. And it can be achieved using thread context loaders.
 * @author Woo
 *
 */
public class TestContextClassLoader {
	public static void main(String[] args) throws Exception {
		ClassLoader Loader01 = TestContextClassLoader.class.getClassLoader();
		System.out.println(Loader01);
		// thread's context class loader
		/*
		 * Each thread has an associated context class loader. If use the new Thread () method 
		 * to start a new thread, the new thread will inherit the parent thread's context class loader. 
		 * If the program does not make any changes to the thread context class loader, all threads 
		 * in the program will use the system class loader as the context class loader.
		 * 
		 */
		ClassLoader Loader02 = Thread.currentThread().getContextClassLoader();
		System.out.println(Loader02);
		
		Thread.currentThread().setContextClassLoader(new FileSystemClassLoader("D:/Eclipse/eclipseJavaPro/workspace/SXT-Pro/Text"));
		System.out.println(Thread.currentThread().getContextClassLoader()); // FileSystemClassLoader
		
		Class<?> C = Thread.currentThread().getContextClassLoader().loadClass("Chapter14.Good");
		System.out.println(C);
		System.out.println(C.getClassLoader()); // application because the delegation principle of the customized class loader 
	}
}
