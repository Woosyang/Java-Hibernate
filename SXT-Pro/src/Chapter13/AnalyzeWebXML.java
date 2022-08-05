package Chapter13;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.util.*;

/**
 * Use Sax to analyze the Web.xml, similar with AnalyzePersonXML.java
 * @author Woo
 *
 */
public class AnalyzeWebXML {
	public static void main(String[] args) throws Exception {
		// analyze with SAX
		// 1. get the analyzing factory
		SAXParserFactory Factory = SAXParserFactory.newInstance();
		// 2. get the analyzer from the factory
		SAXParser Parse = Factory.newSAXParser();
		// 4. load the document and register the handler
		WebHandler01 Wh = new WebHandler01();
		// 5. analyze
		Parse.parse(Thread.currentThread().getContextClassLoader()
						  .getResourceAsStream("Chapter13/Web.xml"), Wh);
		/*
		List<ServletEntity> Ents = Wh.getEntities();
		List<ServletMapping> SMaps = Wh.getMappings();
		
		System.out.println(Ents.size()); // check if there is any data, size is 2 according to Web.xml
		System.out.println(SMaps.size()); // check if there is any data, size is 2 according to Web.xml
		*/
		
		// use WebContext to replace the commented code above
		WebContext Wbc = new WebContext(Wh.getEntities(), Wh.getMappings());
		// assume input the url: /login
		// String ClassName = Wbc.getClaz("/g"); // LoginServlet
		// String ClassName = Wbc.getClaz("/login"); // LoginServlet
		String ClassName = Wbc.getClaz("/register"); // RegisterServlet
		// reflection
		Class Claz = Class.forName(ClassName); // get the class
		Servlet S = (Servlet)Claz.getConstructor().newInstance(); // cast
		S.Service(); // to see which Servlet class it is  
	}
}

//3. compile the handler
class WebHandler01 extends DefaultHandler {
	private List<ServletEntity> Entities;
	private List<ServletMapping> Mappings;
	private ServletEntity SerEn;
	private ServletMapping SerMap;
	private String Tag; // traverse each label and store it
	private boolean IsMapping; // signal to notify the operations to serlvet or serlvet-mapping
	
	// no needs to use start/endDocument() -> reduce the code
	public WebHandler01() {
		Entities = new ArrayList<>();
		Mappings = new ArrayList<>();
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		Tag = qName;
		if (Tag.equals("servlet")) {
			SerEn = new ServletEntity();
			IsMapping = false; // do the actions to servlet
		} else if (Tag.equals("servlet-mapping")) {
			SerMap = new ServletMapping();
			IsMapping = true; // do the actions to servlet-mapping
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String Context = new String(ch, start, length).trim();
		if (Context.length() > 0) { // get rid of empty content
			if (IsMapping) { // servlet-mapping
				if (Tag.equals("servlet-name")) {
					SerMap.setName(Context);
				} else if (Tag.equals("url-pattern")) {
					SerMap.addURLPatterns(Context);
				}
			} else { // servlet 
				if (Tag.equals("servlet-name")) {
					SerEn.setName(Context);
				} else if (Tag.equals("servlet-class")) {
					SerEn.setClaz(Context);
				}
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("servlet")) {
			Entities.add(SerEn);
		} else if (qName.equals("servlet-mapping")) {
			Mappings.add(SerMap);
		}
		Tag = null;
	}

	public List<ServletEntity> getEntities() {
		return Entities;
	}

	public List<ServletMapping> getMappings() {
		return Mappings;
	}
}