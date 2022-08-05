package Chapter10;
import java.io.*;

/**
 * based on FileInputStream02
 * another standard style for IO using throws
 * @author Woo
 *
 */
public class TestFileInputStream03 {
	// throws Exception
	public static void main(String[] args) throws IOException, FileNotFoundException {
		File Src = new File("Text/AB.txt");
		InputStream Is = new FileInputStream(Src);
		int Temp = 0;
		while ((Temp = Is.read()) != -1) {
			System.out.println((char) Temp);
		}
		if (Is != null) {
			Is.close();
		}
	}
}
