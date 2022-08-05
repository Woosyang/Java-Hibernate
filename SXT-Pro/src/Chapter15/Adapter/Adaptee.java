package Chapter15.Adapter;

/**
 * The Class Needs To Be Adapted
 * The class can't be used by client directly, but the client want to use it
 * Metaphor: mouse with USB port but the client only got type-c port
 * @author Woo
 *
 */
public class Adaptee {
	public void Request() {
		System.out.println("can cater the requirements of the client..");
	}
}
