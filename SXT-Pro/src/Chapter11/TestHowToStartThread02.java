package Chapter11;

/**
 * create a thread£»
 * implements Runnable interface + override void run()
 * starts: create an implementing class + Thread object + start
 * recommended: avoid the limit of the inheritance, easy to share the resource
 * @author Woo
 *
 */
public class TestHowToStartThread02 implements Runnable {
	// entrance of the thread
	@Override
	public void run() {
		for (int i = 0; i < 100; i ++) {
			System.out.println("Hahah");
		}
	}
	
	public static void main(String[] args) {
		// create an implementing class
		TestHowToStartThread02 T = new TestHowToStartThread02();
		// create a proxy class 
		Thread t = new Thread(T);
		t.start();
		// or :if the object is only used in one time use anonymous class
		// new Thread(new TestHowToStartThread02()).start();
		for (int i = 0; i < 100; i ++) {
			System.out.println("Happy");
		}
	}
}
