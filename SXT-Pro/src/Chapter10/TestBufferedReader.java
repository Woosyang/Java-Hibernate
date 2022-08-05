package Chapter10;
import java.io.*;

/**
 * based on TestFileReader
 * Test BufferedReader
 * @author Woo
 *
 */
public class TestBufferedReader {
	public static void main(String[] args) {
		File Src = new File("H.txt");
		// polymorphism is not allowed because new method will not be used
		BufferedReader Br = null;
		String Line = ""; // different from receiving length
		try {
			Br = new BufferedReader(new FileReader(Src));
			while (Line != null) {
				// read line by line
				Line = Br.readLine();
				if (Line == null) {
					break;
				}
				System.out.println(Line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (Br != null) {
					Br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
