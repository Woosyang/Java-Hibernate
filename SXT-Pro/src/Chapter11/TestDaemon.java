package Chapter11;

/**
 * Daemon thread is serving for User thread, JVM doesn't need to wait for the finish of 
 * the Daemon thread to stop itself(JVM)
 * JVM will be stopped when all of the User threads will be executed
 * By default, every thread is user thread
 * @author Woo
 *
 */
public class TestDaemon {
	public static void main(String[] args) {
		Human H = new Human();
		God G = new God();
		new Thread(H).start();
		Thread T = new Thread(G); // needs to be set up
		T.setDaemon(true); // make this thread to be a Daemon thread
		T.start(); // will not keep outputting the outcome
	}
}

class Human implements Runnable {
	@Override
	public void run() {
		for (int i = 1; i <= 36500; i ++) {
			System.out.println("Alive");
		}
		System.out.println("Rest");
	}
}

class God implements Runnable {
	@Override
	public void run() {
		while (true) {
			System.out.println("Beless");
		}
	}
}