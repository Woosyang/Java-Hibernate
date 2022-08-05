package Chapter06;

import java.io.*;

/**
 * Testing the throws
 * @author Woo
 *
 */
public class TestException03 {
	// throws to the JRE
	public static void main(String[] args) throws IOException {
		ReadMyFile();
	}
	// throws to the void main
	public static void ReadMyFile() throws IOException /*contains FileNotFound*/ {
		FileReader reader = null;
		System.out.println("a");
		reader = new FileReader("b.txt"); // corrected one: a.txt
		System.out.println("step1");
		char a = (char) reader.read();
		System.out.println(a);
		if (reader != null) {
			reader.close();
		}
	}
}
