package Chapter15.Adapter;

/**
 * Test the Adapter Pattern
 * Used With Target Interface
 * Client who only get type-c port
 * @author Woo
 *
 */
/*
 * Adapter pattern works as a bridge between two incompatible interfaces. This type 
 * of design pattern comes under structural pattern as this pattern combines the 
 * capability of two independent interfaces.
 * This pattern involves a single class which is responsible to join functionalities 
 * of independent or incompatible interfaces.
 * example for Adapter: java.io.InputStreamReader(InputStream)
						java.io.OutputStreamWriter(OutputStream
 */
public class AdapterTest {
	
	public void Use(Target T) {
		T.HandleRequest();
	}
	
	public static void main(String[] args) {
		// first way
		AdapterTest At = new AdapterTest(); // client
		Adaptee Ae = new Adaptee(); // the class needs to be adapted
		// let the interface becomes to the adapted class
		Target T = new Adapter01(); // target interface
		// Client -> Target -> Target becomes to Adapter01 -> Adapter01 calls the real function
		At.Use(T);
		
		// second way
		// make the adapted class become to one of the field of the Adapter class
		// pass this field to its constructo
		T = new Adapter02(Ae);
		At.Use(T);
	}
}
