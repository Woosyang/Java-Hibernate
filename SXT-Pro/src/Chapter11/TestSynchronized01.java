package Chapter11;

/**
 * SafeThread01
 * fix the unsafe thread01 -> TicketRush Simulation
 * solve the problem of concurrency:
 * use synchronized method
 * guarantee the correctness of the data
 * increase the efficiency as best as it can 
 * @author Woo
 *
 */
public class TestSynchronized01 {
	public static void main(String[] args) {
		TicketRushPro T = new TicketRushPro();
		new Thread(T, "R").start();
		new Thread(T, "S").start();
		new Thread(T, "O").start();
	}		
}

// use synchronized method
class TicketRushPro implements Runnable {
	private int Num = 20;
	boolean Flag = true;
	
	@Override
	public void run() {
		while (Flag) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Execute();
		}
	}
	
	// safe thread, synchronization locks the variable Flag and Num which belong to
	// the fields of this object's
	public synchronized void Execute() {
		if (Num < 0) {
			Flag = false;
			return; // do not forget it, otherwise it will keep going to output the negative number
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "---->" + Num --);
	}
}

