package Chapter11;

/**
 * Happen-Before: reordering the instructions -> increase performance
 * if there is data independence, the result will be changed
 * if there isn't, the result will be good
 * @author Woo
 *
 */
public class HappenBefore {
	// variable 1
	private static int a = 0;
	private static boolean Flag = false;
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 20; i ++) {
			a = 0;
			Flag = false;
			// T01 changes the data
			Thread T01 = new Thread (() -> {
				a = 1;
				Flag = true;
			});
			// T02 
			Thread T02 = new Thread (() -> {
				if (Flag) {
					a *= 1; 
				}
				if (a == 0) {
					System.out.println("Happen-Before: a -> " + a);
				}
				// wrong outcome: Happen-Before: a -> 1 because the reordering instructions
				// a = 0 is not be written back immediately, a has been changed a = 1
				// this two statement has no data independence
			});
			T01.start();
			T02.start();
			// let T01 finished first
			T01.join();
			T02.join();
		}
	}
}
