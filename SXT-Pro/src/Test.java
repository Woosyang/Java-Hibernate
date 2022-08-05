import Chapter04.User05; // importing the class of User05
/* Using import Chapter04.*; will slow down the compiling speed */

import java.util.Date;
//import java.sql.*; overlap cuz sql also has the "Date"

import static java.lang.Math.*;

/**
 * Testing the import
 * @author Woo
 *
 */

public class Test {
	public static void main(String[] args) {
		//Chapter04.User05 user = new Chapter04.User05();
		User05 user = new User05();
		String str;
		Date date01 = new Date(); // util
		java.sql.Date date02 = new java.sql.Date(1); // sql
		System.out.println(Math.PI);
		System.out.println(PI); // the same as the one above
	}
}
