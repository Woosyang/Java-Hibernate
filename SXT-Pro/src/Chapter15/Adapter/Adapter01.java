package Chapter15.Adapter;

/**
 * Adapter Class, create the connection between Client And Adapted Class
 * Metaphor: converter for USB to type-c
 * convert the original interface into the targeted interface by wrapping
 * the object needs to be adapted
 * @author Woo
 *
 */
// by extending the Adaptee, wrap the adapted class, call the adapted class's
// method directly
public class Adapter01 extends Adaptee implements Target {

	@Override
	public void HandleRequest() {
		super.Request();
	}
	
}
