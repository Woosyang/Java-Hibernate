package Chapter08;
import java.util.*;
import java.text.*; // control + shift + o

/**
 * Test the conversion between the String and Date
 * and usage of DateFormat and SimpleDateFormat(can be fulfilled)
 * @author Woo
 *
 */

public class TestDateFormat {
	public static void main(String[] args) throws ParseException {
		DateFormat Df1; // abstract class
		// convert the Date into corresponding String according to formatted String
		Df1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); // child class, use formatted string
		String Str1 = Df1.format(new Date(8000000)); // milliseconds
		System.out.println(Str1);
		// convert the String into corresponding Date according to formatted String
		DateFormat Df2 = new SimpleDateFormat("yyyy年MM月dd日 hh时mm分ss秒");
		Date Date = Df2.parse("1994年02月22日 12时06分08秒"); // Exception will happen
		System.out.println(Date);
		// test the string of other format
		// W, w, D, d stand for different meanings
		DateFormat Df3 = new SimpleDateFormat("D"); // test how many days it has
		String Str2 = Df3.format(new Date());
		System.out.println(Str2); // Days
	}
}
