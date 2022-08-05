package Com.Sxt.Core;
import java.util.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

/**
 * Used With WebApp.java, ServletEntity.java, ServletMapping.java
 * @author Woo
 *
 */
public class WebHandler extends DefaultHandler {
	private List<ServletEntity> Entities;
	private List<ServletMapping> Mappings;
	private ServletEntity SerEn;
	private ServletMapping SerMap;
	private String Tag; // traverse each label and store it
	private boolean IsMapping; // signal to notify the operations to serlvet or serlvet-mapping
	
	// no needs to use start/endDocument() -> reduce the code
	public WebHandler() {
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
