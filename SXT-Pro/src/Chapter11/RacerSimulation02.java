package Chapter11;
import java.util.concurrent.*;

/**
 * implements Callable interface to start the thread
 * @author Woo
 *
 */
public class RacerSimulation02 implements Callable<Integer> {
	private String Winner;
	
	@Override
	public Integer call() throws Exception {
		for (int Steps = 0; Steps <= 100; Steps ++) {
			boolean Flag = GameOver(Steps);
			System.out.println(Thread.currentThread().getName() + "--->" + Steps);
			if (Flag) {
				return Steps; // break
			}
		}
		return null;
	}
	
	private boolean GameOver(int Steps) {
		if (Winner != null) {
			return true;
		} else {
			if (Steps == 100) {
				Winner = Thread.currentThread().getName();
				System.out.println(Winner + " has won");
				System.out.println("Game Over");
				return true;
			} else {
				return false;
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		RacerSimulation02 T = new RacerSimulation02();
		// 1. creating an executing service
		ExecutorService Ser = Executors.newFixedThreadPool(2);
		// 2. submit the execution
		// fight for one resource
		Future<Integer> R1 = Ser.submit(T);
		Future<Integer> R2 = Ser.submit(T);
		// 3. get the result
		int r1 = R1.get();
		int r2 = R2.get();
		System.out.println(r1 + "--->" + r2);
		// 4. shutdown the service
		Ser.shutdownNow();
	}
}
