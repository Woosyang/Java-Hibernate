package Chapter10;
import java.io.*;

/**
 * learn how to create a path
 * relative path & absolute path
 * absolute path: has the sign of Disk
 * relative path: has no sign of Disk, relative to the current directory, user.dir
 * @author Woo
 *
 */
public class TestPath {
	public static void main(String[] args) {
		// in java '\' is illegal for the path of a file
		// use '\\' or '/'
		String Path = "D:\\Eclipse\\eclipseJava\\workspace\\SXT-Pro\\Cat.jfif";
		System.out.println(File.separator); // бо\'
		// 2 ways to create a path
		// 1. use '/'
		Path = "D:/Eclipse/eclipseJava/workspace/SXT-Pro/Cat.jfif";
		System.out.println(Path);
		// 2. use constants to splice 
		Path = "D:" + File.separator + "Eclipse" + File.separator + "eclipseJava" + File.separator + "workspace" + File.separator + "SXT-Pro" + File.separator + "Cat.jfif";
		System.out.println(Path);
		// absolute path:
		File Src = new File(Path);
		System.out.println(Src.getAbsolutePath());
		
		// relative path:
		System.out.println(System.getProperty("user.dir")); // D:\Eclipse\eclipseJava\workspace\SXT-Pro
		Src = new File("Cat.jfif");
		System.out.println(Src.getAbsolutePath());
		
		// create a file doesn't exist
		// it can be a file or a directory
		Src = new File("A/a.java");
		System.out.println(Src.getAbsolutePath());
	}
	
	
}
