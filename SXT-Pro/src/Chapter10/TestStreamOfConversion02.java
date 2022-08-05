package Chapter10;
import java.net.*;
import java.io.*;

/**
 * based on TestStreamOfConversion01
 * use the IO Stream to get the source code of google
 * write the code in to a html file
 * choose a designated char-set in InputStreamReader/OutputStreamWriter
 * @author Woo
 *
 */
public class TestStreamOfConversion02 {
	public static void main(String[] args) {
		/* Way I
		int Temp = 0; // read the data one by one
		try (InputStreamReader Isr = 
			 new InputStreamReader(new URL("https://www.google.com").openStream(), "UTF-8");){
			while (Temp != -1) {
				Temp = Isr.read();
				if (Temp == -1) {
					break;
				}
				// the amount of the byte is not enough
				System.out.print((char)Temp);  // it will generate garbled code
			}
		*/
		// Way II increase the efficiency using BufferedReader
		String Line = " ";
		try (BufferedReader Br = 
			new BufferedReader(
			new InputStreamReader(
			new URL("https://www.google.com").openStream(), "UTF-8"));
			BufferedWriter Bw = 
			new BufferedWriter(
			new OutputStreamWriter(
			new FileOutputStream("Google.html"), "UTF-8"))) {
			while (Line != null) {
				Line = Br.readLine();
				if (Line == null) {
					break;
				}
				// System.out.println(Line);
				Bw.write(Line);
				Bw.newLine();
			}
			Bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
