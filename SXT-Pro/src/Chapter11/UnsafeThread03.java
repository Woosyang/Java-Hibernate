package Chapter11;
import java.util.*;

/**
 * example: operating the collections
 * @author Woo
 *
 */
public class UnsafeThread03 {
	public static void main(String[] args) {
		List<String> L = new ArrayList<>();
		for (int i = 0; i < 10000; i ++) {
			// 10000 threads
			new Thread(() -> {
				L.add(Thread.currentThread().getName());
			}).start(); 
		}
		System.out.print(L.size()); // is not correct, some of the thread is missing
	}
}
