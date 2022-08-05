package Chapter11;

/**
 * Join: let the thread cut in line to let another thread go in to blocked state
 * time wait
 * @author Woo
 *
 */
public class TestJoin {
	public static void main(String[] args) throws InterruptedException {
		Thread T =  new Thread(() -> {
			for (int i = 0; i < 90; i ++) {
				System.out.println("Rap" + i);
				}
			});
		// because it use join(), don't use anonymous class to start the thread
		T.start();
		for (int i = 0; i < 100; i ++) {
			if (i == 20) {
				T.join(); // main will be blocked
				// output the Rap until it will be finished
			}
			System.out.println("R&B" + i);
		}
		/////////////////////////////////////////////
		Dad Dad = new Dad();
		new Thread(Dad).start();
	}
}

class Dad extends Thread {
	@Override
	public void run() {
		System.out.println("Dad wanna smoke");
		System.out.println("He lets his son to purchase the cigar");
		Thread T = new Thread(new Son());
		T.start();
		// wait for the stoppage of the Son thread
		try {
			T.join(); // Dad thread will be blocked
		} catch (InterruptedException e) {
			System.out.println("The son is missing.....");
			e.printStackTrace();
		}
		System.out.println("Dad takes the cigar....");
	}
}

class Son extends Thread {
	@Override
	public void run() {
		System.out.println("The son receives the money from his dad");
		System.out.println("He goes out");
		for (int i = 0; i < 10; i ++) {
			System.out.println(i + "s later......");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("He finds a store to buy one");
		System.out.println("He goes back with a cigar");
	}
}