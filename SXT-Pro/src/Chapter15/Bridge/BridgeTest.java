package Chapter15.Bridge;

/**
 * Test the bridge pattern
 * Used with Computer02, Brand
 * @author Woo
 *
 */
/*
 * Bridge is used when we need to decouple an abstraction from its implementation so 
 * that the two can vary independently. This type of design pattern comes under 
 * structural pattern as this pattern decouples implementation class and abstract class 
 * by providing a bridge structure between them.
 *
 * This pattern involves an interface which acts as a bridge which makes the 
 * functionality of concrete classes independent from interface implementer classes. 
 * Both types of classes can be altered structurally without affecting each other.
 */
// handle the case with multiple inheritance layer and multiple dimensions. Make each
// dimension class become to an independent inheritance structure so that each one can
// extend its relationship through the abstract layer.
public class BridgeTest {
	public static void main(String[] args) {
		// old school way to create an object
		Computer01 Pc01 = new AppleDesktop(); // the code of the Computer01 is complex
		Pc01.declarePrice();
		
		// use the Bridge Pattern
		Computer02 Pc02 = new Desktop02(new Apple());
		Pc02.declarePrice();
	}
}
