package Chapter10;
import java.io.*;

/**
 * Based on FileInputStream02
 * FileInputStream
 * read from the text segment by segment
 * @author Woo
 *
 */
public class TestFileInputStream04 {
	public static void main(String[] args) {
		// create a source
		File Src = new File("Text/AB.txt");
		// choose the stream
		InputStream Is = null; // extend the scope of the variable
		// operate: segment
		// eg: byte Car[] = new byte[3]
		// can type the Flush[] and Len in the try-catch
		byte Flush[] = new byte[2]; // each time read 3 characters
		int Len = 0; // the number of data received, if it is -1, there is no data
		try {
			// byte Flush[] = new byte[1024];
			// int Len = -1;
 			Is = new FileInputStream(Src);
 			/*
 			while((Len = Is.read(Flush)) != -1) {
 				String Str = new String(Flush, 0, Len);
 				Sytem.out.println(Str);
 			}
 			*/
 			while (Len != -1) {
 				Len = Is.read(Flush);
				if (Len == -1) {
					break;
				}
				// String Str = new String(Car); 
				// it will generate some garbled code at the tail
				// byte[] (0101010) -> String => decoding
				// Flush is a byte[]
				String Str = new String(Flush, 0, Len);
				System.out.println(Str);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//  close the stream
			try {
				if (Is != null) {
					Is.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
