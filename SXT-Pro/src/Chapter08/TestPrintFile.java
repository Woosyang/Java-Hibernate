package Chapter08;
import java.io.*;

/**
 * Use the recursion to print the file tree
 * @author Woo
 *
 */
public class TestPrintFile {
	public static void main(String[] args) {
		File F = new File("D:/Ñ§Ï°×ÊÁÏ");
		Print(F, 0);
	}

// recursion 
public static void Print(File F, int Level) {
	// use the "-" to display the depth of the directory
	for (int i = 0; i < Level; i ++) { // open the file one by one
		System.out.print("-");
	}
	System.out.println(F.getName());
	if (F.isDirectory()) { // if the parameter is not a directory, then it stop
		Level = Level + 1;
		File Fs[] = F.listFiles(); // put all files of the directory into an array
		for (File Temp : Fs) {
			Print(Temp, Level);
			}
		}
	}
}