package Chapter08;
import java.util.*;

/**
 * Test the usage of Calendar
 * @author Woo
 *
 */

public class TestCalendar01 {
	public static void main(String[] args) {
		Calendar Ca1 = new GregorianCalendar(2100, 11, 18, 23, 20, 40);
		// get the field of the Ca1
		int Year = Ca1.get(Calendar.YEAR);
		int Month = Ca1.get(Calendar.MONTH);
		int Weekday = Ca1.get(Calendar.DAY_OF_WEEK_IN_MONTH); // 1 -> Sunday....7 -> Monday
		int Date = Ca1.get(Calendar.DATE); // DATE_OF_MONTH
		System.out.println(Ca1);
		System.out.println(Year);
		System.out.println(Month);
		System.out.println(Weekday);
		System.out.println(Date);
		// set the value of the field in Ca2
		Calendar Ca2 = new GregorianCalendar();
		Ca2.set(Calendar.YEAR, 1995);
		System.out.println(Ca2);
		// calculate the date after days
		Calendar Ca3 = new GregorianCalendar();
		Ca3.add(Calendar.DAY_OF_MONTH, 100); // 100 days later
		System.out.println(Ca3);
		Ca3.add(Calendar.YEAR, -100); // 100 years ago
		System.out.println(Ca3);
		// conversion between Time and Date
		Date D = Ca3.getTime();
		Calendar C4 = new GregorianCalendar();
		C4.setTime(D);
		PrintCalendar(C4);
	}
	public static void PrintCalendar(Calendar C) {
		// print year - month - day - hour - minute - second
		int Year = C.get(Calendar.YEAR);
		int Month = C.get(Calendar.MONTH);
		int Date = C.get(Calendar.DAY_OF_MONTH);
		int Hour = C.get(Calendar.HOUR);
		int Minute = C.get(Calendar.MINUTE);
		int Second = C.get(Calendar.SECOND);
		int Dayweek = C.get(Calendar.DAY_OF_WEEK);
		System.out.println(Year + "年" + Month + "月" + Date + "日" + Hour + "时" + Minute + "分" + Second + "秒" + "周" + Dayweek);
	}
}
