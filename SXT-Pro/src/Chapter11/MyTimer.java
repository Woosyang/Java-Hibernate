package Chapter11;
import java.util.*;

/**
 * Scheduled: Timer & TimerTask
 * @author Woo
 *
 */
public class MyTimer {
	public static void main(String[] args) {
		Timer T = new Timer();
		// scheduling
		// 10s later then start the thread
		// T.schedule(new MyTask(), 10000); // only execute the task one time
		// 10s later to start the thread, after it starts, it will be executed in every 200ms
		T.schedule(new MyTask(), 10000, 200); // execute multiple times
		//Calendar C = new GregorianCalendar(2020, 7, 11, 10, 18, 59); // designated time
		//               first time to be executed
		// T.schedule(new MyTask(), C.getTime(), 200);
	}
}

// TimerTask is a Thread
class MyTask extends TimerTask {
	@Override
	public void run() {
		for (int i = 0; i < 10; i ++) {
			System.out.println("Hello~");
		}
		System.out.println("--------");
	}
}