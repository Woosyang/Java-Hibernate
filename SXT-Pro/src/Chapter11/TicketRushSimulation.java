package Chapter11;

/**
 * use thread to simulate ticket rush web site
 * share the resource
 * concurrency may cause the errors of the data
 * implements Runnable
 * @author Woo
 *
 */
public class TicketRushSimulation implements Runnable {
	private int TicketNum = 99; // amount of the tickets
	
	@Override
	public void run() {
		while (true) {
			if (TicketNum < 0) {
				break;
			}
			// which thread calls run()
			System.out.println(Thread.currentThread().getName() + "----->" + TicketNum --);
		}
		/* Concurrency
		 * increase the probability of the error taking place
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public static void main(String[] args) {
		// one resource
		TicketRushSimulation T = new TicketRushSimulation();
		System.out.println(Thread.currentThread().getName()); // main
		// multiple proxy classes
		// some times when one thread's ticket number becomes to 0 
		// other threads may be executed after it
		// it doesn't mean which thread has ticket's number becoming to 0 then the 
		// outcome of this thread will be the last one
		new Thread(T, "A").start();
		new Thread(T, "B").start();
		new Thread(T, "C").start();
	}
}
