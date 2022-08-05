package Chapter15.Singleton;

/**
 * Singleton: This pattern involves a single class which is responsible to 
 * 			  create an object while making sure that only single object 
 * 			  gets created. This class provides a way to access its only object 
 * 			  which can be accessed directly without need to instantiate the object 
 * 			  of the class.
 * Test The Double-Checked Locking Initialization
 * @author Woo
 *
 */
// not recommended
// because of the compiler's optimization and the internal model of the JVM
public class SingletonDoubleCheckLocking {
	private static SingletonDoubleCheckLocking Instance = null;
	
	// not add the "synchronized" to describe the method
	// put the synchronized content in the if statement
	// increase the efficiency, there is no need to do 
	// the synchronization every time to get the Instance
	// only do the synchronization one time when the Instance is created
	/*
	 * consider the case that two threads enter the if statement at the same 
	 * time. Imagine Thread 1 enters the synchronized block and is preempted (occupied). 
	 * Subsequently, a second thread enters the if block. When Thread 1 
	 * exits the synchronized block, Thread 2 makes a second check to see if 
	 * the singleton instance is still null. Since Thread 1 set the singleton 
	 * member variable, Thread 2¡¯s second check will fail, and a second singleton 
	 * will not be created.
	 *
	 */
	public static SingletonDoubleCheckLocking getInstance() {
		if (Instance == null) {
			SingletonDoubleCheckLocking Sc;
			synchronized (SingletonDoubleCheckLocking.class) {
				Sc = Instance;
				if (Sc == null) {
					synchronized (SingletonDoubleCheckLocking.class) {
						if (Sc == null) {
							Sc = new SingletonDoubleCheckLocking();
						}
					}
				}
				Instance = Sc;
			}
		}
		return Instance;
	}
}
