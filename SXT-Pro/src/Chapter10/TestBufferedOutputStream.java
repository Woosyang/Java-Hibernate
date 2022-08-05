package Chapter10;
import java.io.*;

/**
 * wrapping stream for node stream
 * test BufferedOutputStream
 * based on TestBufferedInputStream
 * @author Woo
 *
 */
public class TestBufferedOutputStream {
	public static void main(String[] args) {
		String Msg = "take my breath away";
		File Des = new File("H.txt");
		OutputStream Os = null;
		try {
			byte Flush[] = Msg.getBytes();
			Os = new BufferedOutputStream(new FileOutputStream(Des));
			Os.write(Flush, 0, Flush.length);
			Os.flush();
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
