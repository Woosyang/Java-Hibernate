package Chapter14;
import java.io.*;

/**
 * Used With EncryptUtil, Decrypt The File From EncrypytUtil 
 * @author Woo
 *
 */
public class DecryptFileSystemClassLoader extends ClassLoader {
	private String RootDir;
	
	public DecryptFileSystemClassLoader(String RootDir) {
		this.RootDir = RootDir;
	}
	
	@Override
	protected Class<?> findClass(String FileName) throws ClassNotFoundException {
		byte[] ClassData = GetClassData(FileName);
		if (ClassData == null) {
			throw new ClassNotFoundException();
		} else {
			Class<?> C = defineClass(FileName, ClassData, 0, ClassData.length);
			return C;
		}
	}
	
	public byte[] GetClassData(String FileName) {
		String Path = RootDir + "/" + FileName.replace(".", "/") + ".class";
		InputStream Is = null;
		ByteArrayOutputStream Baos = new ByteArrayOutputStream();
		int Len = 0;
		try {
			Is = new FileInputStream(Path);
			// don not use byte[] as the container
			while ((Len = Is.read()) != -1) {
				Baos.write(Len ^ 0xff); // corresponding to NOT of the EncryptUtil
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
	
	public static void main(String[] args) throws Exception {
		EncryptUtil.Encrypt("D:/Eclipse/eclipseJavaPro/workspace/SXT-Pro/Text/Hi.class", "D:/Eclipse/eclipseJavaPro/workspace/SXT-Pro/Text/A/Hi.class");
		DecryptFileSystemClassLoader Loader = new DecryptFileSystemClassLoader("D:/Eclipse/eclipseJavaPro/workspace/SXT-Pro/Text/A");
		Class<?> C = Loader.loadClass("Hi");
		System.out.println(C);
	}
}
