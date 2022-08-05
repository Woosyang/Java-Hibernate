package Chapter15.Singleton;

/**
 * Singleton: This pattern involves a single class which is responsible to 
 * 			  create an object while making sure that only single object 
 * 			  gets created. This class provides a way to access its only object 
 * 			  which can be accessed directly without need to instantiate the object 
 * 			  of the class.
 * Test The Eager Initialization
 * @author Woo
 *
 */
public class SingletonEager {
	private static SingletonEager Instance = new SingletonEager(); // load the class immediately when it is initialized
	
	// problem: if we never use getInstance(), it will cost memory resource
	private SingletonEager() {}
	
	// there is no need to add "synchronized" to describe this method
	// it is thread safe because the JVM only load this class one time according 
	// to "static SingletonEager Instance" -> Instance.class will be created first 
	// then starts the initialization 
	// no "synchronized" -> increase the efficiency
	public static SingletonEager getInstance() {
		return Instance;
	}
}
