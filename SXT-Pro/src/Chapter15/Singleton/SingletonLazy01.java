package Chapter15.Singleton;

/**
 * Singleton: This pattern involves a single class which is responsible to 
 * 			  create an object while making sure that only single object 
 * 			  gets created. This class provides a way to access its only object 
 * 			  which can be accessed directly without need to instantiate the object 
 * 			  of the class.
 * Test The Lazy Initialization
 * @author Woo
 *
 */
public class SingletonLazy01 {
	// when the class is initialized do not initialize the object
	// lazy load, create the object when it is needed
	private static SingletonLazy01 Instance;
	// constructor must be private
	private SingletonLazy01() {}
	// not waste the memory resource but the efficiency is low
	public static synchronized SingletonLazy01 getInstance() {
		if (Instance == null) {
			Instance = new SingletonLazy01();
		}
		return Instance;
	}
}
