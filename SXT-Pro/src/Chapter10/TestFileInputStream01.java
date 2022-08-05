package Chapter10;
import java.io.*;

/**
 * Steps:
 * 1. create a source file
 * 2. choose the stream
 * 3. operating
 * 4. release the source(memory)
 * @author Woo
 *
 */
public class TestFileInputStream01 {
	public static void main(String[] args) {
		// 1. create a source file
		// if want to type "AB.txt", the level of AB.txt should be same as the src's
		File Src = new File("Text/AB.txt"); // relative path
		System.out.println(Src.getAbsolutePath());
		// 2. choose the stream(which type)
		try {
		// 3. operate
			InputStream Is = new FileInputStream(Src); // needs the exception
			int Data01 = Is.read(); // first data
			int Data02 = Is.read(); // second data
			int Data03 = Is.read(); // third data
			int Data04 = Is.read();
			int Data05 = Is.read();
			// doesn't find the data
			int Data06 = Is.read(); // it is not the data, it is the end of the data, returns -1
			System.out.println((char)Data01);
			System.out.println((char)Data02);
			System.out.println((char)Data03);
			System.out.println(Data04); // byte format needs to be cast
			System.out.println((char)Data05);
			System.out.println(Data06); // -1
		// 4. release the recourse
			Is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
