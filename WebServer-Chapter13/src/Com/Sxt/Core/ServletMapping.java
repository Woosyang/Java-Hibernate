package Com.Sxt.Core;
import java.util.*;

/**
 * Used with Web.xml, corresponds to 
 * <servlet-mapping>
		<servlet-name></servlet-name>
		<url-pattern></url-pattern>
		....
		<url-pattern></url-pattern>
	</servlet-mapping>
 * @author Woo
 *
 */
public class ServletMapping {
	private String Name;
	private Set<String> URLPatterns; // remove the duplicate url-patterns by using sets
	
	// constructor 
	public ServletMapping() {
		URLPatterns = new HashSet<>(); // initialize the Collection when create a new instance
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Set<String> getURLPatterns() {
		return URLPatterns;
	}

	// one servlet-mapping class may have many urls
	public void addURLPatterns(String URLPattern) {
		URLPatterns.add(URLPattern);
	}
}
