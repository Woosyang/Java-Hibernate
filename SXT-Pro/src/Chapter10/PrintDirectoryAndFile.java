package Chapter10;
import java.io.*;

/**
 * use the recursion to print out the subdirectories and subfiles
 * count the size of the directory(recursion)
 * @author Woo
 *
 */
public class PrintDirectoryAndFile {
	// print out the name out subdirectories or subfiles
	// status of file: 1. doesn't exist 2. directory 3. file
	public static void PrintFiles(File F, int Level) {
		// display the level of the directory by '-'
		for (int i = 0; i < Level; i ++) {
			System.out.print("-");
		}
		// print out the name
		System.out.println(F.getName());
		if (F == null || !F.exists()) {
			return;
		} else if (F.isDirectory()) {
			// list out the subdirectories and subfiles
			for (File f : F.listFiles()) {
				// no need for a static variable to store the layer
				PrintFiles(f, Level + 1); // keep digging layer by layer
			} // omit the status of IsFile()
		}
	}
	
	// calculate the size of the directory
	private static long Size = 0;
	public static void CountSize01(File F) {
		if (F == null || !F.exists()) {
			return;
		} else if (F.isDirectory()) {
			for (File f : F.listFiles()) {
				CountSize01(f);
			}
		} else if (F.isFile()) {
			Size = Size + F.length();
		}
	}
	
	public static long Len = 0;
	public static void CountSize02(File F) {
		if(F != null && F.exists()) {
			if (F.isDirectory()) {
				for (File f : F.listFiles()) {
					CountSize02(f);
				}
			} else {
				Len += F.length();
			}
		}	
	}
	
	public static void main(String[] args) {
		File Src = new File("D:/Eclipse/eclipseJava/workspace/SXT-Pro/src");
		PrintFiles(Src, 0);
		CountSize01(Src);
		CountSize02(Src);
		System.out.println("The size of this file: " + Len);
		System.out.println("The size of this file: " + Size);
	}
}
