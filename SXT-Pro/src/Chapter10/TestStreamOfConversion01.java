package Chapter10;
import java.io.*;

/**
 * Conversion: Test InputStreamReader OutputStreamWriter
 * Only for Text File
 * 1. address the byte stream with the manner of character stream
 * 2. set a designated char-set  
 * @author Woo
 *
 */
public class TestStreamOfConversion01 {
	public static void main(String[] args) {
		// using System.out and System.in
		// character stream     <-----      convert    byte stream
		// InputStreamReader Isr = new InputStreamReader(System.in);
		// OutputStreamWriter Osw = new OutputStreamWriter(System.out);
		// increase the efficiency use the BufferedReader and BufferedWriter
		// the target has been converted into character stream
		// use try..with...resources
		String Line = " "; // receiving string
		try ( BufferedReader Br = new BufferedReader(new InputStreamReader(System.in));
			  BufferedWriter Bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			//  get the input from the keyboard (using a loop)
			// break the loop when the input is "exit"
			while (!Line.equals("exit")) {
				Line = Br.readLine(); // read in
				Bw.write(Line); // write out
				Bw.newLine(); // /r/n
				Bw.flush(); 
				// if the size of the input is too small, it can't be printed out
				// needs to  flush the memory manually
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
