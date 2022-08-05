package Chapter08;
import java.text.*;
import java.util.*;


/**
 * Create a calendar
 * @author Woo
 *
 */

public class TestCalendar02 {
	public static void main(String[] args) throws ParseException {
		String Str = "2020-10-10";
		DateFormat Df = new SimpleDateFormat("yyyy-MM-dd");
		Date Date = Df.parse(Str); // needs to throw an exception
		Calendar C = new GregorianCalendar();
		C.setTime(Date);
		System.out.println("Sun\tMon\tTue\tWen\tThu\tFri\tSat");
		C.set(Calendar.DAY_OF_MONTH, 1); // print out from the first day
		for (int i = 1; i < C.get(Calendar.DAY_OF_WEEK); i ++) {
			System.out.print("\t"); // because the first day of the month may not be on Sunday
		}
		// don't use C.getActualMaximum(Calendar.Date), it will create a bug
		for (int i = 1; i <= C.getActualMaximum(Calendar.DAY_OF_MONTH); i ++) { // execute it 31 times
		// System.out.println(i + "\t");
		// use the calendar to express the statement above
			System.out.print(C.get(Calendar.DAY_OF_MONTH) + "\t");
			if (C.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
				System.out.println(); // meet every Saturday, a new line
			}
			C.add(Calendar.DAY_OF_MONTH, 1);
		}
	}

}
