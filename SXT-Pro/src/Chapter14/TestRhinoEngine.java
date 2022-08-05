package Chapter14;
import javax.script.*;
import java.net.*;
import java.util.*;
import java.io.*;

/**
 * Use Rhino Engine To Execute JavaScript
 * Java <-> Engine <-> JavaScript (Interaction)
 * @author Woo
 *
 */
public class TestRhinoEngine {
	public static void main(String[] args) throws Exception {
		// get the script engine(Rhino)
		ScriptEngineManager Sem = new ScriptEngineManager();
		ScriptEngine En = Sem.getEngineByName("javascript"); // js
		
		// define a variable, Msg will be stored in the context of the engine
		// can be called by Java or JavaScript
		En.put("Msg", "One Piece");
		String Str = "var User = { name: 'Luffy', age: 18, friends: ['Sanji', 'Nami'] };";
		Str += "print(User.name);";
		
		// execute the java script
		En.eval(Str); // execute the java script
		En.eval("Msg = 'One Piece Really Exists !';"); // modify, execute the java
		System.out.println(En.get("Msg"));
		System.out.println("################");
		
		// define the method
		En.eval("function add(a, b) { var sum = a + b; return sum; }"); // method of the java script
		// execute the js method, Invocable implements the ScriptEngine
		// get the interface for calling the method
		Invocable JsInvo = (Invocable)En; // cast, convert the engine to Invocable interface
		//                            2 parameters: 8, 2
		// Object Re = JsInvo.invokeFunction("add", new Object[] {8 ,2});
		Object Re = JsInvo.invokeFunction("add", 8, 2); // execute the method
		System.out.println(Re); // 10
		
		// import other package from java, use the class from the Java
		// use java code in JS, then use the variable of the JS in Java
		String JsCode = "var list = java.util.Arrays.asList([\"Sam\", \"Sim\", \"Sum\"]);";
		En.eval(JsCode);
		List<String> L = (List<String>) En.get("list"); // var = list, casst
		for (String a : L) {
			System.out.println(a);
		}
		
		// execute the java script file, put this java script file beneath the Src folder
		// usually use try.. catch.. finally..
		URL Url = TestRhinoEngine.class.getClassLoader().getResource("A.js"); // use class loader
		// need a file reader
		Reader Fr = new FileReader(Url.getPath());
		En.eval(Fr);
		Fr.close();
	}
}
