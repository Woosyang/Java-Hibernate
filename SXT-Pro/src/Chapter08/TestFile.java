package Chapter08;
import java.io.*;
import java.util.*;

/**
 * Test the File
 * @author Woo
 *
 */

public class TestFile {
	public static void main(String[] args) throws Exception {
		// create a new file in the Disk D
		File F = new File("D:/a.txt"); // don't forget the quote
		F.createNewFile(); // needs an exception
		File G = new File("D:\\b.txt");
		G.delete(); // delete the file
		G.createNewFile(); // needs an exception
		System.out.println(F); // print the path
		G.renameTo(new File("D:/c.txt")); // change the b.txt into c.txt
		System.out.println(System.getProperty("user.dir")); // get the current path of this code
		File A = new File("Ha.txt");
		A.createNewFile();
		System.out.println("whether the file exists: " + A.exists());
        System.out.println("whether the file is the directory: " + A.isDirectory());
        System.out.println("whether the file is a file: " + A.isFile());
        System.out.println("the last time when the file got modified: " + new Date(A.lastModified()));
        System.out.println("the size of the file: " + A.length());
        System.out.println("the name of the file: " + A.getName());
        System.out.println("the path of the file: " + A.getPath());
        File C = new File("D:/A/B");
        boolean Flag01 = C.mkdir(); // if one of the directory doesn't exist, then the whole file tree will be failed to create
        System.out.println(Flag01); // fail to create
        boolean Flag02 = C.mkdirs(); // if none of the directory exists, it will still create the whole file tree
        System.out.println(Flag02); // created successfully, if the directory has existed, it will be false
        // combination test
        /*---------------------------------------------------------------------*/
        // declare a file
        File P = new File("D:/L/C/H.txt"); 
        // see whether the file exists
        boolean Flag03 = P.exists();
        // if the file exists, delete it otherwise create it
        if (Flag03) {
        	// delete it
        	boolean Flag04 = P.delete();
        	if (Flag04) {
        		System.out.println("deleted successfully");
        	} else {
        		System.out.println("failed to delete");
        	}
        } else {
        	// create
        	boolean Flag05 = false;
        	// if the directory doesn't exist, then create one
			File Dir = P.getParentFile(); // get the directory's name of the file
			Dir.mkdirs();
			Flag05 = P.createNewFile(); // if there is no .createNewFile() above, the file never exist
			if (Flag05) {
				System.out.println("created successfully");
			} else {
				System.out.println("failed to create");
			}
        /*
         can use try - catch instead
          try {
                // if the directory doesn't exist, then create one
                File Dir = J.getParentFile();
                Dir.mkdirs();
                // create the file
                Flag = J.createNewFile();
                System.out.println("created successfully");
            } catch (IOException e) {
                System.out.println("failed to create");
                e.printStackTrace();
            }          
         */
        }
	}
}
