package Chapter10;
import java.io.*;

/**
 * compare the copy method using node stream => InputStream & OutputStream
 * with the copy method using wrapping stream => BufferedInputStream & BufferedOutputStream
 * @author Woo
 *
 */
public class ComparePerformance {
	// using try.. with.. resource
	// node stream
	public static void Copy01(String SrcPath, String DesPath) {
		int Len = 0;
		byte Flush[] = new byte[1024];
		try (InputStream Is = new FileInputStream(SrcPath);
			 OutputStream Os = new FileOutputStream(DesPath)) {
				while (Len != -1) {
					Len = Is.read(Flush);
					if (Len == -1) {
						break;
					}
					Os.write(Flush, 0, Flush.length);
				}
				Os.flush();
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
	}
	
	// using wrapping stream
	public static void Copy02(String SrcPath, String DesPath) {
		int Len = 0;
		byte Flush[] = new byte[1024];
		try (InputStream Is = new BufferedInputStream(new FileInputStream(SrcPath));
			 OutputStream Os = new BufferedOutputStream(new FileOutputStream(DesPath))) {
				while (Len != -1) {
					Len = Is.read(Flush);
					if (Len == -1) {
						break;
					}
					Os.write(Flush, 0, Flush.length);
				}
				Os.flush();
			 } catch (IOException e) {
				 e.printStackTrace();
			 }
	}
	
	public static void main(String[] args) {
		long T1 = System.currentTimeMillis();
		Copy01("Cat.jfif", "Cat01.jfif");
		long T2 = System.currentTimeMillis();
		System.out.println("the time using node stream: " + (T2 - T1));
		T1 = System.currentTimeMillis();
		Copy02("Cat.jfif", "Cat02.jfif"); // much faster
		T2 = System.currentTimeMillis();
		System.out.println("the time using wrapping stream: " + (T2 - T1));
	}
}
