package Chapter15.Singleton;
import java.util.concurrent.*;

/**
 * Compare The Performance Of Each Singleton Mode With Multi-Threading Technology
 * (5 ways to create a singleton design pattern, CountDownLatch)
 * Used With SingletonDoubleCheckLocking
 * Used With SingletonEager
 * Used With SingletonEnumerate
 * Used With SingletonLazy01
 * Used With SingletonStaticInnerClass
 * @author Woo
 *
 */
/*
 * when you don't care about the memory resource and there is no need to lazy load
 * Enumerate > Eager Mode
 * when you care about the memory resource and need to lazy load
 * Static Inner Class > Lazy Mode
 */
public class SingletonTest02 {
	public static void main(String[] args) throws Exception {
		long Start = System.currentTimeMillis();
		int ThreadNum = 10; // how many threads u want to create
		CountDownLatch CountDownLatch = new CountDownLatch(ThreadNum);
		
		// start 10 threads, each thread create Singleton Instance 100000 times
		for (int i = 0; i < ThreadNum; i ++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 100000; i ++) {
						Object O = SingletonEager.getInstance(); // 43 ms
						// Object O = SingletonLazy01.getInstance(); // 113 ms
						// Object O = SingletonDoubleCheckLocking.getInstance(); // 46 ms
						// Object O = SingletonStaticInnerClass.getInstance(); // 47 ms
						// Object O = SingletonEnumerate.Instance; // 40 ms
					}
					// must inside the run()
					CountDownLatch.countDown(); // counter, counter --, when a thread is finished
				}
			}).start();
		}
		
		// let the main to wait, otherwise the other threads will not finish
		CountDownLatch.await(); 
		// block the main thread until the counter becomes to 0
		// then the program can be continued
		long End = System.currentTimeMillis();
		System.out.println("Spend: " + (End - Start) + "ms");
	}
}
