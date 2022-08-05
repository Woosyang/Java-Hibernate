package Chapter11;

/**
 * DeadLock: too much synchronization may cause the thread not 
 * 			 to release the recourse for other threads, then it will make the threads 
 * 			 wait for each other. Normally occurs when 
 * 			 holding multiple object locks during synchronization.	
 * 			 synchronized block has other synchronized blocks inside
 * @author Woo
 *
 */
public class TestDeadLock {
	public static void main(String[] args) {
		new MakeUp(0, "Beyonce").start(); 
		new MakeUp(1, "CardiB").start();
	}
}

class Lipstick {}
 
class Mirror {}

class MakeUp extends Thread {
	String Lady;
	int Choice; // each number stands for a different lady
	// must be initialized otherwise it will have null pointer error
	static Lipstick Lipstick = new Lipstick(); // means only one resource
	static Mirror Mirror = new Mirror(); // means only one resource
	public MakeUp(int Choice, String Lady) {
		this.Choice = Choice;
		this.Lady = Lady;
	}
	
	@Override
	public void run() {
		MakingUp();
	}
	
	// holding each other's lock of the object
	public void MakingUp() {
		if (Choice == 0) {
			synchronized (Lipstick) {
				System.out.println(this.Lady + "is using the lipstick~");
				// 1s later want to use the mirror
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// if you want to solve the problem, move the code block below
				// out of this block
				synchronized (Mirror) {
					System.out.println(this.Lady + "is using the mirror...");
				}
			}
			/*
			 * one way to solve the problem above:
			 * don't let the synchronization has other synchronization inside
			 * synchronized (Mirror) {
					System.out.println(this.Lady + "is using the mirror...");
				}
			 */
		} else {
			synchronized (Mirror) {
				System.out.println(this.Lady + " is using the mirror...");
				// delay can cause each thread won't release the recourse mutually
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// if you want to solve the problem, move the code block below
				// out of this block
				synchronized (Lipstick) {
					System.out.println(this.Lady + " is using the Lipstick~");
				}
			}
			/*
			 * one way to solve the problem above:
			 * don't let the synchronization has other synchronization inside
			 * synchronized (Lipstick) {
					System.out.println(this.Lady + "is using the lipstick~");
				}
			 */
		}
	}
}