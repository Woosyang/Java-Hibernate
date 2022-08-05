package Chapter14;
import javax.tools.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.*;

/**
 * Scenario For Using Dynamic Compiling: 
 * Design A System That User Can Use The Browser To Write Java Code And Compile It Online
 * 
 * @author Woo
 *
 */
public class TestJavaCompiler {
	public static void main(String[] args) throws Exception {
		JavaCompiler Jcp = ToolProvider.getSystemJavaCompiler();
		// run(in, out, err, arguments)
		// in: InputStream(null -> System.in)
		// out: OutputStream(null -> System.out)
		// err: error info
		// arguments: the path of the java file(use text editor to write a java code and save it as .java)
		int Re = Jcp.run(null, null, null, "D:/Eclipse/eclipseJavaPro/workspace/SXT-Pro/Text/Hello.java");
		// will generate a Hello.class file if it is compiled successfully
		System.out.println("for the Hello.class: ");
		System.out.println(Re == 0 ? "Compiled Successfully" : "Compiled Failed");
		
		// compile the String to a class file
		String Str = "public class Hi { public static void main(String[] args) { System.out.println(\"Hi...\"); } }";
		// use IO Stream, convert the String to a java file then use Java Compiler
		OutputStream Os = new FileOutputStream("Text/Hi.java");
		byte[] Data = Str.getBytes();
		Os.write(Data, 0, Data.length);
		Os.flush();
		System.out.println("for the String Compiling: ");
		Re = Jcp.run(null, null, null, "Text/Hi.java");
		System.out.println(Re == 0 ? "Compiled Successfully" : "Compiled Failed"); // will generate a Hi.class file
		
		// execute the class file by using Runtime
		Runtime Run = Runtime.getRuntime();
		Process Pro = Run.exec("java -cp Text Hi");
		// needs to use IO Stream to get the result(show on the console)
		InputStream Is = Pro.getInputStream();
		BufferedReader Br = new BufferedReader(new InputStreamReader(Is));
		// get the output of the Hi.java, only works for txt file
		System.out.println("use RunTime to get the output of the Hi.class: ");
		String Info = " "; 
		while (Info != null) {
			Info = Br.readLine();
			if (Info == null) {
				break;
			}
			System.out.println(Info);
		}
		
		System.out.println("Use Reflection to get the output of the Hello.class: ");
		// use class loader to execute the class file(Reflection)
		URL[] Urls = new URL[] {new URL("file:/" + "D:/Eclipse/eclipseJavaPro/workspace/SXT-Pro/Text/")}; // completed path for the java file
		URLClassLoader Loader = new URLClassLoader(Urls);
		Class C = Loader.loadClass("Hello");
		// call the main method of the class loader
		Method M = C.getMethod("main", String[].class);
		// invoke (obj, arg)
		// main(String[] arg) -> the arg type is String[]
		// the second parameter of this M.invoke(a, b) is String[] but has to be initialized -> new String[]{}
		// the first parameter of this M.invoke(a, b) is null because it is main()
		M.invoke(null, (Object) new String[] {}); // must use (Object) to cast the String[]{}
		// if the main method got multiple parameters inside, eg main(String[] a, String[] b)
		// then the format of the invoke() should be invoke(null, (Object) new String[] {}, (Object) new String[] {}) 
		//                                                            String[] a                  String[] b
		// (Object) cast for String[]{} is indispensable, for example if there is no (Object) for
		// the invoke method, like invoke(null, new String[] {"a", "b"}); compiler will compile it
		// as invoke(null, "a", "b") -> number of the parameters can't be matched with the main() 
		// which only got one parameter "String[] arg"
	}
}
