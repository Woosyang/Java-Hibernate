package Com.Sxt.Core;
import java.util.*;

/**
 * Used with AnalyzeWebXML.java as a helper class
 * according to url-pattern, find the servlet name, then find the servlet class 
 * @author Woo
 *
 */
public class WebContext {
	private List<ServletEntity> Entities;
	private List<ServletMapping> Mappings;
	// convert the List Entities to the Map
	// Key -> servlet-name, Value -> servlet-class
	private Map <String, String> EntityMap = new HashMap<>(); // you can intialize the Map in constructor
	// convert the List Mappings to the Map
	// Key -> url-pattern, Value -> servlet-name
	private Map <String, String> MappingMap = new HashMap<>(); // you can intialize the Map in constructor
	// constructor
	public WebContext(List<ServletEntity> Ets, List<ServletMapping> Mps) {
		Entities = Ets;
		Mappings = Mps;
		// convert the List Entities to coresponding Map EntityMap
		for (ServletEntity E : Entities) {
			EntityMap.put(E.getName(), E.getClaz());
		}
		// convert the List Mappings to coresponding Map MappingMap
		// One to Many -> One to One
		// one url pattern maps to only class but one class can have many url patterns
		for (ServletMapping Sp : Mappings) {
			// each ServletMapping class get multiple urls
			for (String Url : Sp.getURLPatterns()) {
				MappingMap.put(Url, Sp.getName());
			}
		}
	}
	
	// find the class name according to the url-pattern, then do the dynamic compiling
	public String getClaz(String UrlPattern) {
		String Name = MappingMap.get(UrlPattern); // get the servlet name
		String Claz = EntityMap.get(Name);
		return Claz;
	}
}
