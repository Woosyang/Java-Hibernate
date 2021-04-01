package Com.Sxt.Core;
import javax.xml.parsers.*;

/**
 * Used With SimpleServerPro, Analyzer For Web.xml
 * Need The Help Of WebContext.java
 * @author Woo
 *
 */
public class WebApp {
	// static block -> only execute one time, need to new an Object
	private static WebContext Wbc;
	static {
		try {
			// analyze with SAX
			// 1. get the analyzing factory
			SAXParserFactory Factory = SAXParserFactory.newInstance();
			// 2. get the analyzer from the factory
			SAXParser Parse = Factory.newSAXParser();
			// 4. load the document and register the handler
			WebHandler Wh = new WebHandler();
			// 5. analyze
			Parse.parse(Thread.currentThread().getContextClassLoader()
					      	  .getResourceAsStream("Web.xml"), Wh);
			
			// get the corresponding HashMap according to the Web.xml
			Wbc = new WebContext(Wh.getEntities(), Wh.getMappings());
		} catch (Exception e) {
			System.out.println("There Is Something Wrong With The Configuration Files");
		}
	}
	
	// get the servlet in the configuration file according to the corresponding Request URL
	// use static method -> can be directly called
	public static Servlet GetServletFromUrl(String UrlFromRequest) {
		// UrlFromRequest has no "/" inside, needs to add "/"
		// Because In Sax, the Url is analyzed with "/"
		String ClassName = Wbc.getClaz("/" + UrlFromRequest); 
		// reflection
		Class Claz;
		try {
			Claz = Class.forName(ClassName);
			if (Claz != null) {
				Servlet S = (Servlet)Claz.getConstructor().newInstance(); // cast
				return S;
			}
		} catch (Exception e) {
			return null; // if there is something wrong, then return nothing
			// avoid the exception being printed out
			// e.printStackTrace();
		}
		return null;
	}
}
