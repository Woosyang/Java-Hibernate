package Chapter11;

/**
 * ThreadLocal: each thread has its own local memory
 * each thread store its own data, changing the data won't affect the data of other threads
 * get/set/initialValue
 * context, where the thread starts from (main() or MyRun03()) 
 * constructor: thread belongs to which is calling it
 * run(): call the thread in run(), then this thread belongs to itself 
 * copy the data to other threads
 * @author Woo
 *
 */
public class TestThreadLocal {
	// nothing is initialized, it will be null
	private static ThreadLocal<Integer> T01 = new ThreadLocal<>();
	// use anonymous class to initialize the value
	private static ThreadLocal<Integer> T02 = new ThreadLocal<>() {
		// according to the T02, the value will be changed as 100
		protected Integer initialValue() {
			return 100;
		}
	};
	// use Lambda
	private static ThreadLocal<Integer> T03 = ThreadLocal.withInitial(() -> 300); // ThreadLocal.withInitial(() -> { return 300; });
	// for MyRun02
	private static ThreadLocal<Integer> T04 = ThreadLocal.withInitial(() -> 1);
	// copy the data
	private static ThreadLocal<Integer> T05 = new InheritableThreadLocal<>();
	
	public static class MyRun02 implements Runnable {
		@Override
		public void run() {
			Integer L = T04.get();                                                    // L
			System.out.println(Thread.currentThread().getName() + "---->" + " has " + T04.get() + " left");
			T04.set(L - 1);
			System.out.println(Thread.currentThread().getName() + "---->" + " has " + T04.get() + " left"); // 0 
		}
	}
	
 	// create another thread
	public static class MyRun01 implements Runnable {
		@Override
		public void run() {
			// T02.set((int) Math.random() * 100); 0 - 99
			T02.set(99);
			// has nothing to do with the T02.get() in main()
			System.out.println(Thread.currentThread().getName() + "---->" + T02.get());
		}	
	}
	
	public static class MyRun03 implements Runnable {
		public MyRun03() {
			T04.set(- 100);
			//                     main                                       -100
			System.out.println(Thread.currentThread().getName() + "---->" + T04.get());
		}
		@Override
		public void run() {
			// belongs to MyRun03                                             1
			System.out.println(Thread.currentThread().getName() + "---->" + T04.get());
		}
	}
	
	public static void main(String[] args) {
		// only for thread main()
		// get() -> get the value
		System.out.println(Thread.currentThread().getName() + "---->" + T01.get()); // null
		System.out.println(Thread.currentThread().getName() + "---->" + T02.get()); // 100
		System.out.println(Thread.currentThread().getName() + "---->" + T03.get()); // 300
		// set()
		T01.set(88); // null -> 88
		System.out.println(Thread.currentThread().getName() + "---->" + T01.get());
		
		// for thread A
		new Thread(new MyRun01(), "A").start();
		
		// has 5 threads, each thread get the value 1 then --, each one gets the 0 finally 
		for (int i = 0; i < 5; i ++) {
			new Thread(new MyRun02()).start();
		}
		
		// context
		// constructor called by the main()
		new Thread(new MyRun03(), "B").start();
		// copy the data to let other threads to have it 
		T05.set(2); // share
		System.out.println(Thread.currentThread().getName() + "---->" + T05.get()); // 2
		new Thread(() -> {
			// get the value 2 from main()
			// Thread-5 ----> 2
			System.out.println(Thread.currentThread().getName() + "---->" + T05.get()); // 2
			T05.set(22); // can be changed
			// Thread-5 ----> 22
			System.out.println(Thread.currentThread().getName() + "---->" + T05.get()); // 22
		}).start();
	}
}

