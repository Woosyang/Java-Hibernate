package Chapter11;

/**
 * Normal Priority: 5
 * Min Priority: 1
 * Max Priority: 10
 * Priority doesn't represent the absolute executing order
 * it means the probability of the thread will be executed
 * @author Woo
 *
 */
public class TestPriority {
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getPriority()); // 5
		MyPriority Mp = new MyPriority();
		
		// creating 
		Thread t1 = new Thread(Mp, "A");
		Thread t2 = new Thread(Mp, "B");
		Thread t3 = new Thread(Mp, "C");
		Thread t4 = new Thread(Mp, "D");
		Thread t5 = new Thread(Mp, "E");
		Thread t6 = new Thread(Mp, "F");
		
		// setting           1~10
		t1.setPriority(Thread.MAX_PRIORITY); // t1.setPriority(10)
		t2.setPriority(Thread.MIN_PRIORITY);
		t3.setPriority(Thread.MAX_PRIORITY);
		t4.setPriority(Thread.MAX_PRIORITY);
		t5.setPriority(Thread.MIN_PRIORITY);
		t6.setPriority(Thread.MIN_PRIORITY);
		// setting must be in advance of the starting
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
	}
}

class MyPriority implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "----->" + Thread.currentThread().getPriority());
	}
	
}