package Chapter15.Adapter;

/**
 * Adapter Class, create the connection between Client And Adapted Class
 * Metaphor: converter for USB to type-c
 * convert the original interface into the targeted interface by wrapping
 * the object needs to be adapted
 * @author Woo
 *
 */
// make the adapted class as one of the field of the Adapter02 class
// need the help of the constructor and mark the class as the parameter
public class Adapter02 implements Target {
	private Adaptee Adaptee; // parameter
	
	public Adapter02(Adaptee Adaptee) {
		this.Adaptee = Adaptee;
	}
	
	@Override
	public void HandleRequest() {
		Adaptee.Request();
	}
	
}
