package Chapter10;
import java.io.*;

/**
 * 1. encapsulate the CopyFile method with InputStream and OutputStream
 * 2. encapsulate the ReleaseSource method with InputStream and OutputStream
 * 3. encapsulate the CopyFile method with BufferedInputStream and BufferedOutputStream
 * 3 is only for text file
 * based on CopyFileAndDirectory -> parameters changed: StringPath -> Stream
 * based on ConnectStream
 * @author Woo
 *
 */
public class FileUtils {
	
	// the parameters of the Copy01 method is Stream
	// encapsulation with InputStream and OutputStream
	// no need for the file source
	public static void Copy01(InputStream Is, OutputStream Os) {
		int Len = 0;
		byte Flush[] = new byte[1024];
		try {
			while (Len != -1) {
				Len = Is.read(Flush);
				if (Len == -1) {
					break;
				}
				Os.write(Flush, 0, Len);
			}
			Os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			/*
			try {
				if (Os != null) {
					Os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (Is != null) {
					Is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			*/
			// Close01(Is, Os);
			Close02(Is, Os);
		}
	}
	
	// encapsulate the close method with InputStream and OutputStream
	// disadvantage: the amount of the parameters of IOStream is limited
	// if the amount of IOStream is too many, it will not be good
	// release the source
	public static void Close01(InputStream Is, OutputStream Os) {
		try {
			if (Os != null) {
				Os.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if (Is != null) {
				Is.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// use the Closeable Interface and variable ...(stands for many variables)
	// release the source
	public static void Close02(Closeable... Ios) {
		for (Closeable Io : Ios) {
			try {
				if (Io != null) {
					Io.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	// the parameter of this Copy method is file path
	// if we use try.. with .. resources, there is no need for Close method
	// in the Copy method
	// take a look at the CopyFileAndDirectory
	public static void Copy02(String SrcPath, String DesPath) {
		/* other way:
		 * File Src = new File(SrcPath);
		 * File Des = new File(DesPath); 
		 * try (InputStream Is = new FileInputStream(Src);
			    OutputStream Os = new FileOutputStream(Des)) {.......}
			    catch (FileNotFoundException e) {....}
		 */
		//  inside try() is the declaration
		int Len = 0; // receiving length
		byte Flush[] = new byte[1024];
		try (InputStream Is = new FileInputStream(SrcPath);
			 OutputStream Os = new FileOutputStream(DesPath)) {
			while (Len != -1) {
				Len = Is.read(Flush);
				if (Len == -1) {
					break;
				}
				Os.write(Flush, 0, Len);
			}
			Os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// use BufferedReader and BufferedWriter to copy a file
	// use this method when we want to copy a text file
	public static void Copy03(String SrcPath, String DesPath) {
		File Src = new File(SrcPath);
		File Des = new File(DesPath);
		String Line = " ";
		try (BufferedReader Br = new BufferedReader(new FileReader(Src));
			 BufferedWriter Bw = new BufferedWriter(new FileWriter(Des))) {
			while (Line != null) {
				Line = Br.readLine(); // read the file line by line
				if (Line == null) {
					break;
				}
				Bw.write(Line);
				Bw.newLine(); // /r/n
			}
			Bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// test I: simply copy the file
		try {
			InputStream Is = new FileInputStream("Text/AB.txt"); 
			// no need to create a source file, create for you automatically
			OutputStream Os = new FileOutputStream("Text/AB-Copy.txt");
			Copy01(Is, Os);
		} catch (Exception e) { // contains IOException
			e.printStackTrace();
		}
		// test II: file -> byte[]
		byte Data[] = null;
		try {
			InputStream Is = new FileInputStream("Text/Cat.jfif");
			// although ByteArrayOutputStream is the child class of OutputStream
			// it still need ByteArrayOutputStream as the declaration to instantiate an object
			ByteArrayOutputStream Os = new ByteArrayOutputStream(); // if it is a picture, can't be printed out
			Copy01(Is, Os);
			// byte Data[] = Os.toByteArray();
			Data = Os.toByteArray(); // still has the data, because the scope of this variable
			System.out.println("File Size:" + Data.length);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// test III: byte[] -> file
		try {
			ByteArrayInputStream Is = new ByteArrayInputStream(Data); // byte[] is not empty
			// built-in method generate the file automatically
			OutputStream Os = new FileOutputStream("Text/Kitty.jfif");
			Copy01(Is, Os);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Copy02("Text/CD.txt", "Text/EF.txt"); // Closeable
		Copy03("Text/AB.txt", "Text/CD.txt");
	}
}
