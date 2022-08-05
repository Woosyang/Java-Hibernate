package Chapter15.Singleton;

/**
 * Singleton: This pattern involves a single class which is responsible to 
 * 			  create an object while making sure that only single object 
 * 			  gets created. This class provides a way to access its only object 
 * 			  which can be accessed directly without need to instantiate the object 
 * 			  of the class.
 * Test The Static Inner Class Initialization
 * @author Woo
 *
 */
// ultimate thread-safe and efficient singleton
// lazy load
// there is no static field in the outer class, 
// will not load the object(Instance) immediately like the eager mode
public class SingletonStaticInnerClass {
	/*
	 * static inner class will only be loaded when getInstance() is called.
	 * when loading the class, it is thread-safe because instance is "static final"
	 * which means it ensure there is only one instance in the memory and can be
	 * assigned with a value only one time
	 *
	 */
	private static class SingletonInstance {
		// use final -> read only -> can't be changed
		private static final SingletonStaticInnerClass  Instance = new SingletonStaticInnerClass();
	}
	
	public static SingletonStaticInnerClass getInstance() {
		return SingletonInstance.Instance;
	}
	
	private SingletonStaticInnerClass() {}
}
