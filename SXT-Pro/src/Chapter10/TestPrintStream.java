package Chapter10;
import java.io.*;

/**
 * Test PrintStream
 * @author Woo
 *
 */
public class TestPrintStream {
	public static void main(String[] args) throws FileNotFoundException {
		// System.out
		PrintStream Ps = System.out;
		Ps.println("Hahahah");
		Ps.println(true);
		// increase the efficiency
		Ps = new PrintStream(new BufferedOutputStream(new FileOutputStream("Print.txt")), true);
		Ps.println("study harder");
		Ps.println(true);
		// Ps.flush(); if PrintStream(OutputStream Os, true) appears
		// redirect the output (print out in the file)
		System.out.println("WoW"); // shows in the console
		System.setOut(Ps); // parameter inside is a print stream
		System.out.println("Yeah"); // shows in the file rather than console
		// reset: redirect the output to the console
		// true inside PrintStream
		System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(FileDescriptor.out)), true));
		System.out.println("Back");
		Ps.close();
	}
}
