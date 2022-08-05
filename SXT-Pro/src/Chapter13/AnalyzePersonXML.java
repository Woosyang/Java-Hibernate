package Chapter13;
import java.util.*;
import javax.xml.parsers.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.*;

/**
 * get familiar with the process of the Sax
 * @author Woo
 *
 */
public class AnalyzePersonXML {
	public static void main(String[] args) throws Exception {
		// analyze with SAX
		// 1. get the analyzing factory
		SAXParserFactory Factory = SAXParserFactory.newInstance();
		// 2. get the analyzer from the factory
		SAXParser Parse = Factory.newSAXParser();
		// 4. load the document and register the handler
		PersonHandler01 Ph01 = new PersonHandler01();
		
		PersonHandler02 Ph02 = new PersonHandler02();
		// 5. analyze
		Parse.parse(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("Chapter13/Person.xml"), Ph01);
		
		Parse.parse(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("Chapter13/Person.xml"), Ph02);
		
		System.out.println("Output With PersonHandler02: ");
		// get the data
		List<Person> Ps = Ph02.getPs();
		for (Person P : Ps) {
			System.out.println(P.getName() + " ---> " + P.getAge());
		}
	}
}

//3. compile the handler
class PersonHandler01 extends DefaultHandler {
	@Override
	public void startDocument() throws SAXException {
		System.out.println("Start The Analyzation Of The Document");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println(qName + " -->  Start The Analyzation" ); // qName -> label name, output order is same as the order of XML
	}
	
	// content of the XML 
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String Context = new String(ch, start, length).trim(); // trim filter the first and last space
		if (Context.length() > 0) { // make sure the string is not ""
			System.out.println("Content is: " + Context); 
		} else {
			System.out.println("Content is: " + "Empty"); // content inside or outside the label is ""
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println(qName + " -->  End The Analyzation" );  // output order is same as the order of XML
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println("End The Analyzation Of The Document");
	}
}

// 3. compile the handler 
class PersonHandler02 extends DefaultHandler {
	public List<Person> Ps;
	private Person P;
	private String Tag; // store the current label of XML file like a cursor
	
	@Override
	public void startDocument() throws SAXException {
		Ps = new ArrayList<>(); // Initialize when loading the file
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		Tag = qName; // traverse and get each head label in XML file, for example: <name> </name> -> get <name>
		if (qName.equals("person")) { // <person> in XML file
			P = new Person();
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String Context = new String(ch, start, length).trim(); // trim filter the first and last space
		// be careful: Tag is still stored after getting the data between <name> </name>
		// should drop the Tag because if there is still some content between </name> and <age>
		// and it will cover the former content between <name> and </name> which is not empty
		if (Context.length() > 0) {
			if (Tag.equals("name")) { 
				P.setName(Context);
			} else if (Tag.equals("age")) {
				P.setAge(Integer.valueOf(Context));
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		Tag = null; // drop the former label name according to comment line: 96 - 98
		if (qName.equals("person")) { // </person> in XML file
			Ps.add(P);
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
	}
	
	// store the list
	public List<Person> getPs() {
		return Ps;
	}
}