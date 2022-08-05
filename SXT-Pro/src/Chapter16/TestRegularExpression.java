package Chapter16;
import java.util.Arrays;
import java.util.regex.*;

/**
 * Test The Usage Of The Regular Expression
 * @author Woo
 *
 */
public class TestRegularExpression {
	public static void main(String[] args) {
		// test whether the "\w+" applies to string "asfa134"
		// in java "\w+" -> "\\w++"
		
		// expression object
		Pattern P = Pattern.compile("\\w+");
		
		// matcher object
		Matcher M = P.matcher("asfa134");
		// M = P.matcher("12415"); // false
		// make the whole string sequence to match with this pattern
		boolean TrueOrFalse = M.matches(); 
		System.out.println(TrueOrFalse); // true
		
		System.out.println("Use find(): ");
		M = P.matcher("asfgag&&241525");
		// this method scan the input string and search whether the substring of it can
		// match the pattern
		
		/*
		System.out.println(M.find()); // true, asfgag
		System.out.println(M.group()); // return the matched string
		System.out.println(M.find()); // true, 241525
		System.out.println(M.group()); // return the mathced string
		System.out.println(M.find()); // false, null
		System.out.println(M.find()); // false, null
		*/
		
		// use while loop to replace the code above
		while (M.find()) {
			System.out.println(M.group());
			System.out.println(M.group(0)); // group(0) is same as group()
			// group(0), group() match the substring of with the whole regular expression
		}
		
		System.out.println("Use group(): "); // capturing group
		P = Pattern.compile("([a-z]+)(\\d+)");
		M = P.matcher("afafag14125**safq3414&&dafaf35235");
		while (M.find()) {
			System.out.println(M.group()); // substring
			System.out.println(M.group(1)); // characters, first "("
			System.out.println(M.group(2)); // numbers, second "("
		}
		
		// use replace() to replace the designated pattern
		System.out.println("Use replaceAll(): ");
		P = Pattern.compile("\\d+");
		M = P.matcher("sfafagg1234***fagag124%%%faofo1345");
		String newStr = M.replaceAll("!"); // replace the "!" with numbers
		System.out.println(newStr);
		
		// use split()
		System.out.println("Use split(): ");
		String[] A = newStr.split("[a-z]+");
		System.out.println(Arrays.toString(A));
	}
}
