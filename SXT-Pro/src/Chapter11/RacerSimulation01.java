package Chapter11;

/**
 * use multiple threads to simulate a running race
 * @author Woo
 *
 */
public class RacerSimulation01 implements Runnable {
	// only one resource
	private String Winner = null; // mark who is winner
	
	@Override
	public void run() {
		for (int Steps = 1; Steps <= 100; Steps ++) {
			// simulate the horse will take a break for 5ms in each 50 steps
			if (Thread.currentThread().getName().equals("Horse") && Steps % 50 == 0) {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + "--->" + Steps);
			// whether the game is over
			// if the String Winner is taken, it will return true
			// even though other thread is still running, the Flag will break the loop
			// sometimes even the loop is break and there still are some outcomes appear below the final outcome
			
			boolean Flag = GameOver(Steps);
			if (Flag) {
				break;
			}
		}
	}
	
	private boolean GameOver(int Steps) {
		// only one can win this game
		// if (Winner != null) doesn't exist, outcome will show 2 Winners
		/* wrong way -> show 2 Winners
		if (Steps == 100) {
			System.out.println("The winner is:" + Thread.currentThread().getName());
			return true;
		} else {
			return false;
		}
		*/ 
		if (Winner != null) {
			return true;
		} else {
			if (Steps == 100) {
				// Winner will not be null
				Winner = Thread.currentThread().getName();
				System.out.println("The winner is: " + Winner);
				return true;
			} else {
				return false;
			}
		}
	}
	
	public static void main(String[] args) {
		// one resource
		RacerSimulation01 R = new RacerSimulation01();
		// multiple thread, two paths
		new Thread(R, "Horse").start();
		new Thread(R, "Dog").start();
	} 
}
