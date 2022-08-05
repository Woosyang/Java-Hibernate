package Chapter14;
import java.io.*;

/**
 * Helper Class For Encryption Used With Decoding Class Loader
 * @author Woo
 *
 */
public class EncryptUtil {
	// encode the source file 
	public static void Encrypt(String Src, String Des) {
		InputStream Fis = null;
		FileOutputStream Fos = null;
		int Len = 0; // receiving length
		try {
			Fis = new FileInputStream(Src);
			Fos = new FileOutputStream(Des);
			// do not use byte[] as the container
			while ((Len = Fis.read()) != -1) {
				Fos.write(Len ^ 0xff); // NOT(Bitwise) -> Len XOR 1 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (Fis != null) {
				try {
					Fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (Fos != null) {
				try {
					Fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
	}
	
	public static void main(String[] args) throws Exception {
		Encrypt("D:/Eclipse/eclipseJavaPro/workspace/SXT-Pro/Text/Hi.class", "D:/Eclipse/eclipseJavaPro/workspace/SXT-Pro/Text/A/Hi.class");
		// test whether encryption works used with FileSystemClassLoader
		FileSystemClassLoader Loader = new FileSystemClassLoader("D:/Eclipse/eclipseJavaPro/workspace/SXT-Pro/Text/A");
		Class C = Loader.loadClass("Hi");
		System.out.println(C); // class format error
	}
}
