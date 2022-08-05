package Chapter10;
import java.io.*;

/**
 * copy the file using FileInputStream and OutputStream
 * based on this idea, make a method which can copy the directory
 * @author Woo
 *
 */
public class CopyFileAndDirectory {
	// copying the file
	public static void CopyFile(String SrcPath, String DesPath) {
		File Src = new File(SrcPath);
		File Des = new File(DesPath);
		OutputStream Os = null;
		InputStream Is = null;
		int Len = 0; // the receiving length
		try {
			Is = new FileInputStream(Src);
			Os = new FileOutputStream(Des);
			byte Flush[] = new byte[1024];
			while (Len != -1) {
				Len = Is.read(Flush);
				if (Len == -1) {
					break;
				}
				//                     byte[] int int 
				String Str = new String(Flush, 0, Len);
				Os.write(Str.getBytes());
			}
			Os.flush(); // important
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// the stream is created earlier, will be deleted later
			// each stream will be closed separately using two try-catch
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
	}
	
	// recursion
	// copy the directory using recursion
	public static void CopyDirectory(String SrcPath, String DesPath) {
		File Src01 = new File(SrcPath);
		File Fs01[] = Src01.listFiles();
		File Src02 = new File(DesPath);
		if (!Src02.exists()) {
			Src02.mkdirs();
		}
		for (File f : Fs01) {
			if (f.isFile()) {
				CopyFile(f.getPath(), DesPath + "/" + f.getName());
			} else { // directory
				CopyDirectory(f.getPath(), DesPath + "/" + f.getName());
			}
		}
	}
	
	public static void main(String[] args) {
		CopyFile("Text/AB.txt", "Text/CD.txt");
		CopyDirectory("Text", "Exam");
 	}
}
