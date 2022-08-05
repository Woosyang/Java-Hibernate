package Chapter10;
import java.io.*;

/**
 * application for node stream
 * similar with copying a file
 * 1. picture read in to byte[]
 * 2. byte[] wrote out to file
 * @author Woo
 *
 */
public class ConnectStream {
	/*
	 * picture read in to byte[]
	 * 1. picture read in to program: InputStream
	 * 2. program -> byte[]: ByteArrayOutputStream(no parameters)
	 */
	public static byte[] FiletoByteArray(String SrcPath) {
		// read the file
		File Src = new File(SrcPath); // for the InputStream
		byte Des[] = null; // store the file
		InputStream Is = null;
		ByteArrayOutputStream Bos = null;
		int Len = 0; // receiving length for Is
		if (!Src.exists()) {
			throw new RuntimeException("File Not Found");
		}
		try {
			byte Flush[] = new byte[1024 * 10]; // 10K 
			Is = new FileInputStream(Src);
			Bos = new ByteArrayOutputStream();
			while (Len != -1) {
				Len = Is.read(Flush);
				if (Len == -1) {
					break;
				}
				Bos.write(Flush, 0, Len);
			}
			Des = Bos.toByteArray();
			Bos.flush();
			return Des;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (Is != null) {
					Is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/*
	 * byte[] converted in to picture
	 * 1.byte[] -> program: ByteArrayOutputStream
	 * 2.program -> file: OutputStream
	 */
	public static void ByteArraytoFile(byte[] Src, String Despath) {
		File Des = new File(Despath); // for FileOutputStream
		OutputStream Os = null;
		ByteArrayInputStream Bis = null;
		int Len = 0; // receiving length for Bis
		try {
			Bis = new ByteArrayInputStream(Src); // Src -> File
			Os = new FileOutputStream(Des); // create a new file
			byte Flush[] = new byte[1024 * 10]; // 10K
			while (Len != -1) {
				Len = Bis.read(Flush);
				if (Len == -1) {
					break;
				}
				Os.write(Flush, 0, Len);
			}
			Os.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (Os != null) {
					Os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		byte Data[] = FiletoByteArray("Cat.jfif"); // it is a byte array
		System.out.println(Data.length); // size of the Cat.jfif
		ByteArraytoFile(Data, "Kitty.jfif");
	}
}
