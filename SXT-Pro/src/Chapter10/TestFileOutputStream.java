package Chapter10;
import java.io.*;

/**
 * Based on FileInputStream04
 * FileOutputStream
 * write to a file segment by segment
 * @author Woo
 *
 */
public class TestFileOutputStream {
	public static void main(String[] args) {
		// create the source
		File Src = new File("Text/CD.txt"); // if it doesn't exist, it be generated automatically
		OutputStream Os = null;
		try {
			// operate
			Os = new FileOutputStream(Src, true); // true means appending the String
			// Os = new FileOutputStream(Src, false); // false means erasing the String and rewriting it
			String Str = "try so hard and get so far\r\n"; //  carriage return and line feed
			// get the byte[]
			byte Data[] = Str.getBytes(); // String -> byte[] => encoding
			// writes out
			Os.write(Data, 0, Data.length);
			Os.flush(); // save the memory
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (Os != null) {
					Os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
