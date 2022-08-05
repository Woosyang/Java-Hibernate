package Chapter11;

/**
 * stop the thread for another thread: yield
 * go in to Runnable state not Blocked state
 * @author Woo
 *
 */
public class TestYield {
	public static void main(String[] args) {
		MyYield M = new MyYield();
		// two threads fight for the M, one of them will yield for another
		new Thread(M, "A").start();
		new Thread(M, "B").start();
		new Thread(() -> {
			for (int i = 0; i < 88; i ++) {
				System.out.println("Lambda" + i);
			}
		}).start();
		for (int i = 0; i < 100; i ++) {
			// main will be yielded
			if (i % 10 == 0) {
				Thread.yield();
			}
			System.out.println("Main" + i);
		}
	}
}

class MyYield implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "----> Start" );
		// go back to the scheduler to wait for scheduling
		Thread.yield(); // yield for another thread
		System.out.println(Thread.currentThread().getName() + "----> End");
	}
}