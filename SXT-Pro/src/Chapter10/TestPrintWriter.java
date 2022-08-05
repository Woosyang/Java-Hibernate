package Chapter10;
import java.io.*;

/**
 * test PrintWriter: write the data into the file, like redirecting of PrintStream
 * similar with PrintStream
 * @author Woo
 *
 */
public class TestPrintWriter {
	public static void main(String[] args) throws FileNotFoundException {
		PrintWriter Pw = new PrintWriter(new BufferedOutputStream(new FileOutputStream("Rmb.txt")), true);
		Pw.println("R&B All Night");
		Pw.println("------");
		// Pw.flush(); same as PrintWriter(OutputStream Os, true)
		Pw.close();
	}
}
