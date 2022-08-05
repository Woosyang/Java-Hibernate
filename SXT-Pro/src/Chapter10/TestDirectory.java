package Chapter10;
import java.io.*;

/**
 * Create a new directory
 * 2 ways: 
 * 1. mkdir(): make sure the upper layer of the directory you are creating exists
 * 			   if it doesn't, the directory you are creating will be failed 
 * 2. mkdirs(): can create the directory and the upper layer of the directory(doesn't exist)
 * 				at the same time
 * 
 * List all the lower layers of the current directory
 * 1. list(): find the name of the lower directories
 * 2. listFiles(): find the files of inside lower directories
 * @author Woo
 *
 */
public class TestDirectory {
	
	public static void PrintTen(int n) {
		System.out.println(n);
		n ++;
		if (n > 10) {
			return;
		} else {
			PrintTen(n);
		}
	}
	
	public static void main(String[] args) {
		File Dir = new File("D:/Eclipse/eclipseJava/workspace/SXT-Pro/Text/A");
		// create a directory using mkdirs()
		boolean Flag = Dir.mkdirs();
		System.out.println(Flag);
		// create a directory using mkdir(), make sure the upper directory exist
		Dir = new File("D:/Eclipse/eclipseJava/workspace/SXT-Pro/Text/B");
		Flag = Dir.mkdir();
		System.out.println(Flag);
		Dir = new File("D:/Eclipse/eclipseJava/workspace/SXT-Pro/src");
		
		// list all the names of the subdirectories of the current directory
		String SubNames[] = Dir.list();
		for (String N : SubNames) {
			System.out.println(N);
		}
		
		// list all the subfiles of the current directory
		// absolute path
		Dir = new File("D:/Eclipse/eclipseJava/workspace/SXT-Pro/src/Chapter10");
		File SubFiles[] = Dir.listFiles();
		for (File F : SubFiles) {
			System.out.println(F.getAbsolutePath());
		}
		
		// list all the Disks
		// absolute path
		File Roots[] = File.listRoots();
		for (File R : Roots) {
			System.out.println(R.getAbsolutePath());
		}
		
		// a little practice
		PrintTen(1);
	}
}
