package Com.Sxt.Core;

/**
 * Used with Web.xml, corresponds to 
 * <servlet>
		<servlet-name></servlet-name>
		<servlet-class></servlet-class>
   </servlet>
 * @author Woo
 *
 */
public class ServletEntity {
	private String Name;
	private String Claz; // reflection -> dynamic compiling
	public ServletEntity() {}
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}
	
	public String getClaz() {
		return Claz;
	}
	
	public void setClaz(String claz) {
		Claz = claz;
	}
}
