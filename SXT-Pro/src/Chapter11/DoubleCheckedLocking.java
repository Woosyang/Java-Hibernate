package Chapter11;

/**
 * Singleton Pattern: DCL
 * based on the lazy loading design pattern, has the concurrency of the multiple threads
 * to make sure there is only one object for outside under the circumstance 
 * which is called Multiple Threads
 * 1. the constructor has to be private, avoid to create a new constructor from outside
 * 2. offer the private static fields ---> store the address of the object
 * 3. offer a public static method  ---> require the fields
 * @author Woo
 *
 */
public class DoubleCheckedLocking {
	// 2. offer the private static fields
	private static volatile DoubleCheckedLocking Instance;
	
	// 1. private constructor
	private DoubleCheckedLocking() {}
	
	// 3. offer the public static method -> require the fields
	public static DoubleCheckedLocking GetInstance(/* long Time */) {
		// double check
		// avoid the unnecessary synchronization
		// if the Instance has been already taken
		if (Instance != null) {
			// simulate the delay
			/* try {
			 *		Thread.sleep(Time);
			 * } catch {
			 *  	e.printStackTrace();
			 * }			  
			 */
			return Instance;
		}
		// make sure there is only one thread to access the Instance
		synchronized (DoubleCheckedLocking.class) {
			if (null == Instance) {
				Instance = new DoubleCheckedLocking();
				// 1. create a new space for the object
				// 2. initialize the information of the object
				// 3. return the address of the object as reference
			}
			// if the happen - before happens, Instance may not has been initialized yet
			// then the Instance it returns will be null pointer
			// volatile is necessary, without it, other thread will access the object hasn't been initialized
			return Instance;
		}
	}
	
	// wrong way with no synchronization
	public static DoubleCheckedLocking Get(long Time) {
		if (null == Instance) {
			try {
				Thread.sleep(Time);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Instance = new DoubleCheckedLocking();
		}
		return Instance;
	}
	
	public static void main(String[] args) {
		Thread t = new Thread(() -> { System.out.println(DoubleCheckedLocking.GetInstance()); });
		t.start();
		System.out.println(DoubleCheckedLocking.GetInstance()); // same address as above
		
		// see the difference without the code above, the code below is not singleton
		/* 
		 * Thread d = new Thread(() -> {
		 * System.out.println(DoubleCheckedLocking.Get(1000));}); 
		 * d.start();
		 * System.out.println(DoubleCheckedLocking.Get(200)); // different address
		 */	
	}
}
