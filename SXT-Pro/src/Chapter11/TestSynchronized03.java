package Chapter11;
import java.util.*;
import java.util.concurrent.*;

/**
 * SafeThread03
 * fix the unsafe thread03 -> ArrayList
 * solve the problem of concurrency:
 * use synchronized block, the target you lock is more accurate
 * guarantee the correctness of the data
 * increase the efficiency as best as it can
 * use the concurrent container
 * @author Woo
 *
 */
public class TestSynchronized03 {	
	public static void main(String[] args) throws InterruptedException {
		// ArrayList<String> L = new ArrayList<>()
		List<String> L = new ArrayList<>();
		// used for another method
		List<String> K = new  CopyOnWriteArrayList<>();
		// 1000 threads
		for (int i = 0; i < 1000; i ++) {
			// synchronized block
			new Thread(() -> { synchronized(L) {
				// it just locks L.add(Thread.currentThread().getName());
				L.add(Thread.currentThread().getName());
				} }).start();
			
			/* it also works, but it will take longer time
			 * it locks the whole Thread
			synchronized(L) {
				new Thread(() -> {
					L.add(Thread.currentThread().getName());
				}).start();
			}
			*/
		}
		// 1000 threads 
		for (int i = 0; i < 1000; i ++) {
			new Thread(()-> {
				K.add(Thread.currentThread().getName());
			}).start();
		}
		Thread.sleep(20000); // let the main thread sleep for 10s
		// if the main thread won't sleep, the for loop may not be finished
		// the size it prints out will not be correct
		System.out.println(L.size());
		System.out.println(K.size());
	}
}

