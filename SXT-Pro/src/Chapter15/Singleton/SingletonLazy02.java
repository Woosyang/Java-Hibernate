package Chapter15.Singleton;
import java.io.*;

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
// prevent Singleton from reflection and de-serialization
@SuppressWarnings("all")
public class SingletonLazy02 implements Serializable {
	private static SingletonLazy02 Instance;
	
	private SingletonLazy02() {
		// avoid reflection
		if (Instance != null) {
			throw new RuntimeException();
		}
	}
	
	public static synchronized SingletonLazy02 getInstance() {
		if (Instance == null) {
			Instance = new SingletonLazy02();
		}
		return Instance;
	}
	
	// call-back method
	/*
	 * During de-serialization, if readResolve() is defined, 
	 * the Instance of SingletonLzay02 will be returned as
	 * this.Instance(if it has been defined), and readObject()
	 * will not create a new Instance of SingletonLazy02
	 */
	private Object readResolve() throws ObjectStreamException { // no "()" after Exception
		return Instance;
	}
}
