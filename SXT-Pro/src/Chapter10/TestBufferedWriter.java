package Chapter10;
import java.io.*;

/**
 * based on TestFileWriter
 * Test BufferedWriter
 * @author Woo
 *
 */
public class TestBufferedWriter {
	public static void main(String[] args) {
		BufferedWriter Bw = null;
		File Des = new File("H.txt");
		try {
			Bw = new BufferedWriter(new FileWriter(Des));
			String Msg = "we are the beautiful one";
			Bw.append("don't be shy");
			Bw.newLine(); // -> /r/n
			Bw.write(Msg);
			Bw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (Bw != null) {
					Bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
