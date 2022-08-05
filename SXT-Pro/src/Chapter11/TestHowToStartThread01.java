package Chapter11;

/**
 * create a thread:
 * extend the thread + override the void run()
 * @author Woo
 *
 */
public class TestHowToStartThread01 extends Thread {
	// the entrance of the thread
	@Override
	public void run() {
		for (int i = 0; i < 100; i ++) {
			System.out.println("Say You");
		}
	}
	public static void main(String[] args) {
		// start, create a child object
		TestHowToStartThread01 St = new TestHowToStartThread01();
		// if the for loop is over the St.start(), the for loop will be executed first until it is finished
		for (int i = 0; i < 10; i ++) {
			System.out.println("Say me");
		}
		// the timing of starting the thread
		// doesn't guarantee it will be executed first 
		St.start(); // create a new path 
		// St.run(); // it will be still in the same path
		// void run() will be executed first and then execute the code below until it is finished 
		for (int i = 0; i < 100; i ++) {
			System.out.println("Say me");
		}
	}
}
