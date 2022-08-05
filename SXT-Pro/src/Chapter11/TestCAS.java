package Chapter11;
import java.util.concurrent.atomic.*;

/**
 * Compare and Swap
 * @author Woo
 *
 */
public class TestCAS {
	private static AtomicInteger Stock = new AtomicInteger(5);
	public static void main(String[] args) {
		for (int i = 0; i < 5; i ++) {
			new Thread (() -> {
				// simulate the delay
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
				Integer Left = Stock.decrementAndGet(); // CAS
				if (Left < 1) {
					System.out.println(Thread.currentThread().getName() + " ---> Out Of Stock....");
					return;
				} else {
					System.out.println(Thread.currentThread().getName() + " has already purchased one" +
									  ", There is only " + Left + " available"); }
			}).start();
		}
	}
}
