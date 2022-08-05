package Chapter08;
import java.text.*;
import java.util.*;


/**
 * Create a CalendarPro
 * @author Woo
 *
 */

public class TestCalendar03 {
	public static void main(String[] args) throws ParseException {
		System.out.println("Please input your date: (Format: 0000-00-00)");
		@SuppressWarnings("resource")
		Scanner Scanner = new Scanner(System.in);
		String Str = Scanner.nextLine();
		DateFormat Df = new SimpleDateFormat("yyyy-MM-dd");
		Date Date = Df.parse(Str); // needs to throw an exception
		Calendar C = new GregorianCalendar();
		C.setTime(Date);
		System.out.println("Sun\tMon\tTue\tWen\tThu\tFri\tSat");
		int Day = C.get(Calendar.DAY_OF_MONTH); // should be ahead of C.set(Calendar.DAY_OF_MONTH, 1)
		C.set(Calendar.DAY_OF_MONTH, 1); // print out from the first day
		for (int i = 1; i < C.get(Calendar.DAY_OF_WEEK); i ++) {
			System.out.print("\t"); // because the first day of the month may not be on Sunday
		}
		int Days = C.getActualMaximum(Calendar.DATE);
		// don't use C.getActualMaximum(Calendar.Date), it will create a bug
		for (int i = 1; i <= /*C.getActualMaximum(Calendar.DAY_OF_MONTH)*, it will change*/ Days; i ++) { // execute it 31 times
		// System.out.println(i + "\t");
		// use the calendar to express the statement above
			if (i == Day) {
				System.out.print(i + "*\t");
			} else {
			System.out.print(C.get(Calendar.DAY_OF_MONTH) + "\t");
			}
			if (C.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				System.out.println(); // meet every Saturday, a new line
			}
			C.add(Calendar.DAY_OF_MONTH, 1); // careful here
		}
	}

}