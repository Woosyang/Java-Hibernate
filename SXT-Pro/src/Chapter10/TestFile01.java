package Chapter10;
import java.io.*;

/**
 * create a File object
 * @author Woo
 *
 */
public class TestFile01 {
	public static void main(String[] args) {
		String Path = "D:/Eclipse/eclipseJava/workspace/SXT-Pro/Cat.jfif";
		// use different File constructors to instantiate an object
		// 1                String
		File Src = new File(Path);
		System.out.println(Src.length()); // size of the file
		// 2                      String parent                    String child
		Src = new File("D:/Eclipse/eclipseJava/workspace/SXT-Pro", "Cat.jfif");
		System.out.println(Src.length());
		// 3                         File parent                           String child
		Src = new File(new File("D:/Eclipse/eclipseJava/workspace/SXT-Pro"), "Cat.jfif");
		System.out.println(Src.length());
	}
}
