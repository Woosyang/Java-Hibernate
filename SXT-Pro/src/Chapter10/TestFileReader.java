package Chapter10;
import java.io.*;

/**
 * based on FileInputStream01
 * FileReader: character stream
 * @author Woo
 *
 */
public class TestFileReader {
	public static void main(String[] args) {
		// create the source
		File Src = new File("a.txt");
		// choose the stream
		Reader R = null;
		int Len = 0; // receiving length
		// operating: segment by segment
		try {
			char Flush[] = new char[1024];
			R = new FileReader(Src);
			while (Len != -1) {
				Len = R.read(Flush);
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
			try {
				if (R != null) {
					R.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
