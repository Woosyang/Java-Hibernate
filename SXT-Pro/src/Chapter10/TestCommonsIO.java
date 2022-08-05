package Chapter10;
import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.*;


/**
 * Import the CommonsIO from the Apache
 * test the methods inside
 * @author Woo
 *
 */
public class TestCommonsIO {
	public static void main(String[] args) throws IOException {
		// I. calculate the size
		// the size of the file
		long Len = FileUtils.sizeOf(new File("src/Chapter10/SplitAndMergeFile.java"));
		System.out.println(Len);
		// the size of the directory
		// used the recursion to calculate the size
		Len = FileUtils.sizeOf(new File("src/Chapter10"));
		System.out.println(Len);
		
		// II. list the subfiles under the current directory or the subdirectories       
		//                                 filter the file is empty   filer the directory 
		// the type it returns is a collection
		Collection<File>  SubFiles= FileUtils.listFiles(new File("Text"), EmptyFileFilter.NOT_EMPTY, null);
		for (File File : SubFiles) {
			System.out.println(File.getAbsolutePath());
		}
		System.out.println("---------------------------");                //   list the subfiles of the subdirectories
		SubFiles = FileUtils.listFiles(new File("Text"), EmptyFileFilter.NOT_EMPTY, DirectoryFileFilter.INSTANCE);
		for (File File : SubFiles) {
			System.out.println(File.getAbsolutePath());
		}
		
		// III. filter the files with the corresponding suffix
		// eg: .java . txt..............
		System.out.println("---------------------------");
		// find the file whose type is only jfif
		SubFiles = FileUtils.listFiles(new File("Text"), new SuffixFileFilter("jfif"), DirectoryFileFilter.INSTANCE);
		for (File File : SubFiles) {
			System.out.println(File.getAbsolutePath());
		}
		System.out.println("---------------------------");
		// find the file whose type is txt or html     parameter changed here: FileFilterUtils.or()
		SubFiles = FileUtils.listFiles(new File("Text"), FileFilterUtils.or(new SuffixFileFilter("jfif"), new SuffixFileFilter("html")), DirectoryFileFilter.INSTANCE);
		for (File File : SubFiles) {
			System.out.println(File.getAbsolutePath());
		}
		System.out.println("---------------------------");
		// find the file whose type is txt or html or the file is empty    
		SubFiles = FileUtils.listFiles(new File("Text"), FileFilterUtils.or(new SuffixFileFilter("jfif"), new SuffixFileFilter("html"), EmptyFileFilter.EMPTY), DirectoryFileFilter.INSTANCE);
		for (File File : SubFiles) {
			System.out.println(File.getAbsolutePath());
		}
		// find the file whose type is txt and which is empty
		System.out.println("---------------------------");
		SubFiles = FileUtils.listFiles(new File("Text"), FileFilterUtils.and(new SuffixFileFilter("txt"), EmptyFileFilter.EMPTY), DirectoryFileFilter.INSTANCE);
		for (File File : SubFiles) {
			System.out.println(File.getAbsolutePath());
		}
		
		// IV. read the file, set the char-set is optional
		String Msg = FileUtils.readFileToString(new File("Text/Emp.txt"), "UTF-8");
		System.out.println(Msg);
		// convert the file into a byte[] and then get its size
		byte Data[] = FileUtils.readFileToByteArray(new File("Text/Emp.txt"));
		System.out.println(Data.length); // size of the file
		// read the file line by line 
		// store each line in to a collection
		System.out.println("---------------------------");
		List<String> Lines = FileUtils.readLines(new File("Text/Emp.txt"), "UTF-8");
		for (String Str : Lines) {
			System.out.println(Str);
		}
		System.out.println("---------------------------");
		// use the LineIterator to print put
		// take a look at Chapter09
		LineIterator It = FileUtils.lineIterator(new File("Text/Emp.txt"), "UTF-8");
		while (It.hasNext()) {
			System.out.println(It.nextLine());
		}
		
		// V write to a file
		FileUtils.write(new File("Study.txt"), "Don't worry\r\n", "UTF-8"); // append the string in the same line
		FileUtils.writeStringToFile(new File("Study.txt"), "Be happy", "UTF-8", true);
		FileUtils.writeByteArrayToFile(new File("Study.txt"), ", Be tough\r\n".getBytes("UTF-8"), true);
		// 3 ways above has the same function to write something into a file
		// write to a file using a list
		List<String> Words = new ArrayList<>();
		Words.add("Jones");
		Words.add("White");
		Words.add("Smith"); //                Collection, Connection
		FileUtils.writeLines(new File("Study.txt"), Words, "-", true);
		
		// VI copy the file and the directory
		// copy the file
		FileUtils.copyFile(new File("Study.txt"), new File("Study-Co.txt"));
		// copy the file into a directory
		FileUtils.copyFileToDirectory(new File("Study.txt"), new File("Lib"));
		// copy the directory
		FileUtils.copyDirectory(new File("Lib"), new File("C"));
		// copy the directory into another directory
		FileUtils.copyDirectoryToDirectory(new File("Lib"), new File("C")); // Lib is the subdirectory of C
		
		// VII URL's operation
		// copy the URL into a File
		String Url = "https://images.app.goo.gl/E8s6Ny5psyTHSdfv8";
		FileUtils.copyURLToFile(new URL(Url), new File("DeadPool.jpg"));
		// get the source code of the web-site
		String Web = IOUtils.toString(new URL("http://www.google.com"), "UTF-8");
		System.out.println(Web);
	}
}
