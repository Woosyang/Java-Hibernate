package com.Utils;

/**
 * Encapsulate the common methods of the operations to String
 * @author Woo
 *
 */
public class StringUtils {
	/**
	 * convert the first character of the targeted String into upper case 
	 * @param Str, targeted String
	 * @return a string with first character in upper case
	 */
	public static String firstChar2UpperCase(String Str) {
		// eg: abcd -> Abcd
		// first abcd -> ABCD, second get first character "A" by using substring(0, 1)
		// third joint the "A" with the rest of the string "bcd"
		//               "A"                           "bcd"
		return Str.toUpperCase().substring(0, 1) + Str.substring(1);
	}
}
