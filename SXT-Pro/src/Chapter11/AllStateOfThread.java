package Chapter11;
import java.lang.Thread.State;

/**
 * Test all states of the thread
 * @author Woo
 *
 */
public class AllStateOfThread {
	public static void main(String[] args) {
		Thread T = new Thread(() -> { 
			for (int i = 0; i < 5; i ++) {
				try {
					// T will sleep for 5ms
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Moonlight"); });
		// observe the state of the thread
		State S = T.getState();
		System.out.println(S); // NEW
		T.start();
		S = T.getState();
		System.out.println(S); // runnable
		// the condition for breaking the loop
		// method I
		while (S != Thread.State.TERMINATED) {
			try {
				// observe the state in each 200ms
				Thread.sleep(200); // let the main sleep for 200ms 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			S = T.getState(); // timed waiting or terminated
			System.out.println(S);
		}
		
		/*
		// method II
		while (true) {
			int Num = Thread.activeCount(); // the amount of the active threads
			System.out.println(Num);
			if (Num == 1) { // only main runs
				break;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			S = T.getState();
			System.out.println(S);
		}
		*/
	}
}
