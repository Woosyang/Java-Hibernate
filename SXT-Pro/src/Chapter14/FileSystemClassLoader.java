package Chapter14;
import java.io.*;

/**
 * Customize A Class Loader
 * Load The Class According To Specific File Path
 * @author Woo
 *
 */
public class FileSystemClassLoader extends ClassLoader {
	// given such a string: "Chapther14.User" _Convert To_> "Chapter14/User"
	private String RootDir; // the format has to be D:/folder name 
	
	// constructor
	public FileSystemClassLoader(String RootDir) {
		this.RootDir = RootDir;
	}
	
	/*
	protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		synchronized (getClassLoadingLock(name)) {
			// First, check if the class has already been loaded
			Class<?> c = findLoadedClass(name);
			if (c == null) {
				long t0 = System.nanoTime();
				try {
					if (parent != null) {
						c = parent.loadClass(name, false);
					} else {
						c = findBootstrapClassOrNull(name);
					}
				} catch (ClassNotFoundException e) {
					// ClassNotFoundException thrown if class not found
					// from the non-null parent class loader
				}	
				if (c == null) {
					// If still not found, then invoke findClass in order
					// to find the class.
					c = findClass(name); // override it by yourself
				}
			}
			if (resolve) {
				resolveClass(c);
			}
			return c;
		}
	}
	*/
	
	@Override
	protected Class<?> findClass(String FileName) throws ClassNotFoundException {
		/* delegation principle already used in the loadClass()
		 * loadClass() will call findClass()
		// Class<?> C = findLoadedClass(FileName);
		// search this class whether has been loaded with this name(go to Extension, Bootstrap...)
		// if it has been loaded, return the loaded class (In Application)
		// otherwise, load a new class with this name 
		// convert the java file to byte[] then create its own class file
		*/
		
		byte[] ClassData = GetClassData(FileName);
		if (ClassData == null) {
			throw new ClassNotFoundException();
		} else {
			// convert the byte[] to a java class file
			Class<?> C = defineClass(FileName, ClassData, 0, ClassData.length);
			return C;
		}
	}
	
	private byte[] GetClassData(String FileName) { // FileName: com.sxt.file
		// use I/O Stream, need to use file path of the file 
		String Path = RootDir + "/" + FileName.replace(".", "/") + ".class"; // join the file path
		// System.out.println(Path);
		// can use IOUtils to convert the file to byte[]
		ByteArrayOutputStream Baos = new ByteArrayOutputStream();
		InputStream Is = null;
		byte[] Flush = new byte[1024];
		int Len = 0;
		try {
			Is = new FileInputStream(Path);
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (Is != null) {
				try {
					Is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	// test the customized class loader
	// Hierarchy of the class loader: Customized ClassLoader -> Application -> Extension -> BootStrap 
	public static void main(String[] args) throws Exception {
		// file path
		FileSystemClassLoader Loader01 = new FileSystemClassLoader("D:/Eclipse/eclipseJavaPro/workspace/SXT-Pro/Text");
		// the java file must be compiled first -> class file
		// find the class file -> load the class
		Class<?> C01 = Loader01.loadClass("com.sxt.ThankYou"); // class file in the com/sxt
		Class<?> C02 = Loader01.loadClass("com.sxt.ThankYou"); // same to the C01 because they both use the same class loader
		// not use the application class loader
		System.out.println(C01);
		System.out.println(C01.hashCode());
		System.out.println(C01 == C02); // true
		
		// the same class loaded by different class loader is not considered as the same class by the JVM
		FileSystemClassLoader Loader02 = new FileSystemClassLoader("D:/Eclipse/eclipseJavaPro/workspace/SXT-Pro/Text");
		Class<?> C03 = Loader02.loadClass("com.sxt.ThankYou");
		System.out.println(C03 == C02); // false
		
		Class<?> C04 = Loader01.loadClass("java.lang.String");
		System.out.println(C04.hashCode());
		System.out.println(C04.getClassLoader()); // null, bootstrap(delegation principle)
		System.out.println(C03.getClassLoader()); // customized class loader
		
		FileSystemClassLoader Loader03 = new FileSystemClassLoader("D:/Eclipse/eclipseJavaPro/workspace/SXT-Pro/src");
		// application class loader, because this class is located at the same directory of the FileSystemClassLoader
		Class<?> C05 = Loader03.loadClass("Chapter14.Good"); 
		System.out.println(C05.getClassLoader());
 	}
}
