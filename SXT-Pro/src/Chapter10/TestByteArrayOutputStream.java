package Chapter10;
import java.io.*;

/**
 * based on TestIO08
 * File -> byte[]
 * 1. create the source: no need to be concerned, built-in type
 * 2. choose the stream: not relative to the source(no parameters)
 * ByteArrayOutputStream: -> no need to create the capacity for the stream
 * @author Woo
 *
 */
public class TestByteArrayOutputStream {
	public static void main(String[] args) {
		byte Des[] = null;
		ByteArrayOutputStream Bos = null;
		try {
			Bos = new ByteArrayOutputStream(); // no parameters inside
			String Msg = "the stars come out";
			byte Data[] = Msg.getBytes();
			Bos.write(Data, 0, Data.length);
			Bos.flush();
			// require the data
			Des = Bos.toByteArray(); // File -> byte[]
			System.out.println("The size of the String: " + Des.length);
			String Str = new String(Des, 0, Bos.size()); // Des.length()
			System.out.println(Str); // restore the string
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
