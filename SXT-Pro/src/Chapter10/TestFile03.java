package Chapter10;
import java.io.*;

/**
 * Other information
 * createNewFile(): create a new file when the file doesn't exist
 * delete(): delete the file has existed
 * @author Woo
 *
 */
public class TestFile03 {
	public static void main(String[] args) throws IOException {
		File Src = new File("D:/Eclipse/eclipseJava/workspace/SXT-Pro/H.txt");
		boolean Flag01 = Src.createNewFile();
		System.out.println(Flag01); // return false when the file already has been created
		// boolean Flag02 = Src.delete(); // boolean
		// System.out.println(Flag02); // true -> deleted 
		System.out.println("---------------------------");
		// it is not a directory
		Src = new File("D:/Eclipse/eclipseJava/workspace/SXT");
		Flag01 = Src.createNewFile();
		System.out.println(Flag01);
		boolean Flag02 = Src.delete(); // boolean
		System.out.println(Flag02); // true -> deleted 
		// supplement: con, com3 are the built-in name inside the System
		// can't be created
		Src = new File("D:/Eclipse/eclipseJava/workspace/SXT-Pro/con");
		Src.createNewFile();
	}
}
