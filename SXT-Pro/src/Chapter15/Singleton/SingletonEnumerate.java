package Chapter15.Singleton;

/**
 * Singleton: This pattern involves a single class which is responsible to 
 * 			  create an object while making sure that only single object 
 * 			  gets created. This class provides a way to access its only object 
 * 			  which can be accessed directly without need to instantiate the object 
 * 			  of the class.
 * Test The Enumerate
 * @author Woo
 *
 */
// can not lazy load
public enum SingletonEnumerate {
	// it is just a singleton, based on JVM
	// avoid the bug brought by reflection and deserialization
	Instance;
	
	// add the operation to the Instance of the Singleton
	public void SingletonEnumerateOperation() {
		// code line
	}
}
