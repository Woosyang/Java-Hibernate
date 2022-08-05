package Chapter10;
import java.io.*;

/**
 * based on the FileInputStream01
 * make a standard style for IO
 * @author Woo
 *
 */
public class TestFileInputStream02 {
	public static void main(String[] args) {
		File Src = new File("Text/AB.txt");
		InputStream Is = null;
		int Temp = 0;
		try {
			Is = new FileInputStream(Src);
			// while ((Temp = Is.read()) != -1) {
			//	System.out.println((char)Temp); }
			while (Temp != -1) {
				Temp = Is.read(); // moves forward
				if (Temp == -1) {
					break;
				}
				System.out.println((char)Temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (Is != null) {
					Is.close();
				}
			} catch (IOException e) { // not FileNotFoundException !
				e.printStackTrace();
			}
		}
	}
}
