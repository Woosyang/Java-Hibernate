package Chapter11;

/**
 * SafeThread04
 * analyze the performance of the synchronized method an block
 * @author Woo
 *
 */
public class TestSynchronized04 {
	public static void main(String[] args) {
		TicketRusher T = new TicketRusher();
		new Thread(T, "FOX").start();
		new Thread(T, "CNN").start();
		new Thread(T, "BBC").start();
	}
}

class TicketRusher implements Runnable {
	private int Num = 100;
	private boolean Flag = true;
	
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
	
	// right way01
	// synchronized method
	/*
	public synchronized void Execute() {
		if (Num < 0) {
			Flag = false;
			return;
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "---->" + Num --); 
	}
	*/
	
	// right way02
	// synchronized block
	// the scope going to lock the resource is too wide
	// is same as using the synchronized method -> the performance is not good enough
	/*
	public void Execute() {
		// lock the resources: Flag, Num
		synchronized (this) {
			if (Num < 0) {
				Flag = false;
				return;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "---->" + Num --);
		}
	}
	*/
	
	// according to the right way02
	// improving: reduce the scope of locking the resources as far as possible
	// double check
	// has the better performance
	public void Execute() {
		synchronized (this) {
			if (Num < 0) { // whether there are tickets out there
				Flag = false;
				return;
			}
		}
		if (Num < 0) { // whether it is the final ticket, avoid other threads to fight for the same resource
			Flag = false;
			return;
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "---->" + Num --);
	}
	
	/*
	// wrong solution01 -> unsafe thread
	// the scope of locking the resources is too small
	// the outcome will be same as solution02
	public void Execute() {
		synchronized (this) {
			if (Num < 0) {
				Flag = false;
				return;
			}
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "---->" + Num --);
	}
	*/
	
	/*
	// wrong solution02 -> unsafe thread
	// the object locked is changing
	// the object is locked can't be changed
	// some outcome will be negative or overlapped by other threads
	public void Execute() {
		// inside parentheses must be the object
		synchronized ((Integer) Num) {
			// didn't lock the flag
			if (Num < 0) {
				Flag = false;
				return;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "---->" + Num --);
		}
	}
	*/
}