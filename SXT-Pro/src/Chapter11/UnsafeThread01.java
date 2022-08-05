package Chapter11;

/**
 * example for unsafe thread: ticket rush
 * some of the threads will have the negative number as the outcome
 * different threads will get the same number as the result(overlapped)
 */
public class UnsafeThread01 {
	public static void main(String[] args) {
		TicketRush Tr = new TicketRush();
		new Thread(Tr, "A").start();
		new Thread(Tr, "B").start();
		new Thread(Tr, "C").start();
	}
}

class TicketRush implements Runnable {
	boolean Flag = true;
	private int Num = 99; // ticket's number
	public void running() {
		if (Num < 0) {
			Flag = false;
		}
		try {
			Thread.sleep(200); // sleep with the recourse
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "--->" + Num --);
	}
	
	@Override
	public void run() {
		while (Flag) {
			running();
		}
	}
}
