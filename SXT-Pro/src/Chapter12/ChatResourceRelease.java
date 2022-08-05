package Chapter12;
import java.io.*;

/**
 * class for closing the streams (I/O, Client) -> build for SimpleChatMultiServerWithThreadPro
 * @author Woo
 *
 */
public class ChatResourceRelease {
	// all the i/o stream implement the interface Closeable
	public static void Close(Closeable... T) {
		for (Closeable t : T) {
			try {
				if (t != null) {
					t.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
