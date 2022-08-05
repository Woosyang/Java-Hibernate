package Chapter11;
import java.text.*;
import java.util.*;

/**
 * use the sleep() to simulate the timer
 * test the state of the thread: sleep
 * sleep with the resource
 * @author Woo
 *
 */
public class ThreadTimer {
	public static void main(String[] args) throws InterruptedException {
		// count down 10 times, each time for 1s
		// practice:
		/*
		int Num = 10;
		while (true) {
			Thread.sleep(1000);
			System.out.println(Num --);
			if (Num == 0) {
				break;
			}
		}
		*/
		// using Date, the time started to count down
		Date EndTime = new Date(System.currentTimeMillis() + 1000 * 10); // long
		Date CurrTime = new Date(System.currentTimeMillis());
		// long End = EndTime.getTime();
		while (true) {
			// the end time is greater than the current time in each execution
			System.out.println(new SimpleDateFormat("mm : ss").format(EndTime));
			Thread.sleep(1000); // 1s
			EndTime = new Date(EndTime.getTime() - 1000);
			// when the amount of the milliseconds it returns is smaller than the current time
			// the loop stops
			// if (End - 10000 > EndTime.getTime())
			if ((EndTime).before(CurrTime)) {
				break;
			}
		}
	}
}
