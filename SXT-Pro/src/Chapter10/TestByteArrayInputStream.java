package Chapter10;
import java.io.*;

/**
 * ByteArrayInputStream
 * 1. Source -> byte[]
 * 2. Stream is no need to be closed
 * 3. everything can converted into byte[]
 * 4. the capacity should not be too large
 * use the ByteArrayInputStream to read the file
 * @author Woo
 *
 */
public class TestByteArrayInputStream {
	public static void main(String[] args) {
		// 1. create the source
		byte Src[] = "the sun goes down".getBytes();
		InputStream Bis = null;
		int Len = 0; // receiving length
		byte Flush[] = new byte[10];
		try {
			Bis = new ByteArrayInputStream(Src);
			while (Len != -1) {
				Len = Bis.read(Flush);
				if (Len == -1) {
					break;
				}
				// byte[] -> String => decoding
				String Str = new String(Flush, 0, Len);
				System.out.println(Str);
			}
		} catch(IOException e) { // ByteArrayInputStream doesn't have the FileNotFoundException
			e.printStackTrace();
		} finally {
			/* no need to close the stream
			 * try {
			 * 	if (Bis != null) {
			 * 		Bis.close();
			 * 		}
			 * } catch (IOException e) {
			 * 		e.printStackTrace();}
			 */
		}
	}
}
