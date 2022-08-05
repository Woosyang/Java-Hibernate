package Chapter11;

/**
 * Test the usage of isAlive(): whether the thread is alive 
 * setName(), getName()
 * currentThread(): static method
 * getName() -> the name of proxy role
 * @author Woo
 *
 */
public class TestInfo {
	public static void main(String[] args) {
		System.out.println("whether the thread is alive:" + Thread.currentThread().isAlive());
		// setName: actual role + proxy role
		// actual 
		MyInfo M = new MyInfo("Geek");
		// proxy
		Thread T = new Thread(M);
		T.setName("God");
		T.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("whether the thread is alive:" + T.isAlive()); // false, it's finished
	}
}

class MyInfo implements Runnable {
	private String Name;
	// constructor
	
	public MyInfo(String Name) {
		this.Name = Name;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "----->" + Name);
	}
}