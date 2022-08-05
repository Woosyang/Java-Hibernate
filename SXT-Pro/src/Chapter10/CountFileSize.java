package Chapter10;
import java.io.*;

/**
 * encapsulation -> create a class to calculate the size of file
 * because the static variable can't be shared
 * @author Woo
 *
 */
public class CountFileSize {
	// do not use static!
	// when you create another class the variable inside the former object will remain
	private long FileSize = 0;
	private String Path;
	private int FileNum = 0;
	private int DirectoryNum = 0;
	File Src;
	
	// constructor
	public CountFileSize(String Path) {
		this.Path = Path; // this can be omitted
		this.Src = new File(this.Path);
		if (!Src.exists()) {
			throw new RuntimeException("File Not Found");
		}
		// or you can change the CountSize method into static method
		// when you create an object, the CountSize method will be called automatically 
		// CountSize(Src);
	}
	
	// print out the file name
	public void PrintName(File F, int Level) {
		for (int i = 0; i < Level; i ++) {
			System.out.print("-");
		}
		System.out.println(F.getName());
		if (F == null || !F.exists()) {
			return;
		} else if (F.isDirectory()) {
			Level ++;
			for (File f : F.listFiles()) {
				PrintName(f, Level);
			}
		}
	}
	
	// get the file's size
	public void CountSize(File F) {
		if (F != null && F.exists()) {
			if (F.isDirectory()) {
				DirectoryNum ++;
				// find out the subfiles
				for (File f : F.listFiles()) {
					CountSize(f);
				}
			} else {
				// if it is a file
				FileNum ++;
				FileSize += F.length();
			}
		}
	}
	
	public int GetDirectoryNum() {
		return DirectoryNum;
	}
	
	public int GetFileNum() {
		return FileNum;
	}
	
	public long GetFileSize() {
		return FileSize;
	}
	
	public static void main(String[] args) {
		// call the constructor and will call the method inside
		CountFileSize A = new CountFileSize("D:/Eclipse/eclipseJava/workspace/SXT-Pro/src");
		A.CountSize(A.Src);
		System.out.println("The size of the file: " + A.GetFileSize());
		System.out.println("The name of the file:");
		A.PrintName(A.Src, 0);
		System.out.println("The number of the dirctories: " + A.GetDirectoryNum()); // include src itself, you can initialize the DirctoryNum as -1 
		// System.out.println("The number of the files: " + A.GetFileNum());
		CountFileSize B = new CountFileSize("D:/Eclipse/eclipseJava/workspace/SXT-Pro/src/Chapter10");
		B.CountSize(B.Src);
		B.PrintName(B.Src, 0);
		System.out.println("The number of the files is: " + B.GetFileNum());
	}
}
