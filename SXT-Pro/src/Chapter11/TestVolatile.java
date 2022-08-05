package Chapter11;

/**
 * Volatile: guarantee the synchronization of the data -> visibility of the data
 * @author Woo
 *
 */
public class TestVolatile {
	private static volatile int Num = 0; // like synchronized
	public static void main(String[] args) {
		new Thread(() -> {
			// if there is no volatile to declare the Num
			// this thread will keep going 
			while (Num == 0) {/* no code */}
		}).start();
		try {
			Thread.sleep(1000); // 1s later to stop the thread
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Num = 1; // right now the thread will be finished
		// because Num has been changed break the while()
	}
}
