package Chapter14;
import java.io.*;
import java.net.*;

/**
 * Similar With FileSystemClassLoader.java
 * @author Woo
 *
 */
public class NetWorkClassLoader extends ClassLoader {
	private String RootUrl; // http://localhost:8090/Test.class
	
	// constructor
	public NetWorkClassLoader(String RootUrl) {
		this.RootUrl = RootUrl;
	}
	
	@Override
	protected Class<?> findClass(String FileName) throws ClassNotFoundException {
		byte[] Data = GetClassData(FileName);
		if (Data == null) {
			throw new ClassNotFoundException();
		} else {
			Class<?> C = defineClass(FileName, Data, 0, Data.length);
			return C;
		}
	}
	
	private byte[] GetClassData(String FileName) {
		String Path = RootUrl + "/" + FileName.replace(".", "/");
		InputStream Is = null;
		ByteArrayOutputStream Baos = new ByteArrayOutputStream();
		byte[] Flush = new byte[1024];
		int Len = 0;
		try {
			URL Url = new URL(Path);
			Is = Url.openStream();
			while (Len != -1) {
				Len = Is.read(Flush);
				if (Len == -1) {
					break;
				}
				Baos.write(Flush, 0, Len);
			}
			return Baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (Baos != null) {
				try {
					Baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (Is != null) {
				try {
					Is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static void main(String[] args) {}
}
