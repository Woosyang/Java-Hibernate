package Chapter10;
import java.io.*;

/**
 * wrapping stream for node stream
 * test BufferedInputStream
 * based on TestFileInputStream02
 * @author Woo
 *
 */
public class TestBufferedInputStream {
	public static void main(String[] args) {
		File Src = new File("H.txt");
		InputStream Is = null; // polymorphism
		// way1: BufferedInputStream Bis = null; // for InputStream Is
		int Len = 0; // receiving length
		byte Flush[] = new byte[1024];
		try {
			// by writing down this, there is no need to close Bis
			Is = new BufferedInputStream(new FileInputStream(Src));
			// way1: Bis = new BufferedInputStream(Is);
			while (Len != -1) {
				// way1: Len = Bis.read(Flush);
				Len = Is.read(Flush);
				if (Len == -1) {
					break;
				}
				String Str = new String(Flush, 0, Len);
				System.out.println(Str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// the one which is outside should be closed first
			/* way1:
			try {
				if (Bis != null) {
					Bis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			*/
			try {
				if (Is != null) {
					Is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
