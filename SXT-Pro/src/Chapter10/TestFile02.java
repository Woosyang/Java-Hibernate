package Chapter10;
import java.io.*;

/**
 * Name & Path
 * getName() 
 * getPath(): depends on the path, can return both relative path or absolute path
 * getAbsolutePath()
 * getParent(): if it exists -> all of upper layers of the file, if it doesn't -> null
 * 
 * Status of the File
 * 1. Doesn't exist:
 * 2. exist: File -> IsFile(), Directory -> isDirectory()
 * 
 * Size of the File
 * length(): how many bytes the File has
 * @author Woo
 *
 */
public class TestFile02 {
	
	public static void main(String[] args) {
		File Src01 = new File("D:/Eclipse/eclipseJava/workspace/SXT-Pro/Cat.jfif");
		File Src02 = new File("Cat.jfif");
		// basic methods
		System.out.println("Name: " + Src01.getName());
		System.out.println("Path: " + Src02.getPath()); // Cat.jfif
		System.out.println("AbsolutePath: " + Src01.getAbsolutePath());
		System.out.println("Parent's Path: " + Src01.getParent()); // D:\Eclipse\eclipseJava\workspace\SXT-Pro
		System.out.println("Parent's Path: " + Src02.getParent()); // null
		// return the name of upper layer from this file
		System.out.println("Parent's Path: " + Src01.getParentFile().getName());
		// status of the file
		// doesn't exist
		File Src03 = new File("SXT-Pro/Cat.jfif"); // doesn't offer the absolute path
		// it will only let the System find the file under the current directory (src)
		System.out.println(Src03.getAbsolutePath());
		System.out.println("Whether the file exist: " + Src03.exists());
		System.out.println("Whether it is a file: " + Src03.isFile());
		System.out.println("Whether it is a directory: " + Src03.isDirectory());
		System.out.println("------------------------------------");
		// use the relative path
		Src03 = new File("Cat.jfif"); // relative path
		System.out.println("Whether the file exist: " + Src03.exists());
		System.out.println("Whether it is a file: " + Src03.isFile());
		System.out.println("Whether it is a directory: " + Src03.isDirectory());
		// becomes to a directory
		System.out.println("------------------------------------");
		Src03 = new File("D:/Eclipse/eclipseJava/workspace/SXT-Pro");
		System.out.println("Whether the file exist: " + Src03.exists());
		System.out.println("Whether it is a file: " + Src03.isFile());
		System.out.println("Whether it is a directory: " + Src03.isDirectory());
		// use the absolute path
		System.out.println("------------------------------------");
		Src03 = new File("D:/Eclipse/eclipseJava/workspace/SXT-Pro/Cat.jfif");
		System.out.println("Whether the file exist: " + Src03.exists());
		System.out.println("Whether it is a file: " + Src03.isFile());
		System.out.println("Whether it is a directory: " + Src03.isDirectory());
		System.out.println("------------------------------------");
		Src03 = new File("SXT-Pro"); // has to offer the absolute path
		System.out.println("Whether the file exist: " + Src03.exists());
		System.out.println("Whether it is a file: " + Src03.isFile());
		System.out.println("Whether it is a directory: " + Src03.isDirectory());
		// the file will exist only if it is the last layer or absolute path
		// file's status
		File Src04 = new File("A/a.java");
		//                    doesn't exist
		if (Src04 == null || !Src04.exists()) {
			System.out.println("File doesn't exist");
		} else {
			if (Src04.isFile()) {
				System.out.println("File");
			} else {
				System.out.println("Directory");
			}
		}
		// use the length()
		Src03 = new File("Cat.jfif");
		System.out.println(Src03.length()); // long, if the file doesn't exist return 0
		Src03 = new File("D:/Eclipse/eclipseJava/workspace");
		System.out.println(Src03.isDirectory());
		System.out.println(Src03.length()); // 0, because it is a directory
	}
}
