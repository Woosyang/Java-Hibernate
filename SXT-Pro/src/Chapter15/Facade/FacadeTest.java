package Chapter15.Facade;

/**
 * Test The Facade Pattern
 * @author Woo
 *
 */
/*
 * Facade pattern hides the complexities of the system and provides an interface to 
 * the client using which the client can access the system. This type of design pattern
 * comes under structural pattern as this pattern adds an interface to existing system 
 * to hide its complexities.
 * This pattern involves a single class which provides simplified methods required by 
 * client and delegates calls to methods of existing system classes.
 * 
 */
public class FacadeTest {
	public static void main(String[] args) {
		ShapeMaker Sm = new ShapeMaker();
		// client want a circle
		Sm.drawCircle(); // not call the circle class
		// client want a square
		Sm.drawSquare(); // not call the square class
		// client want a rectangle
		Sm.drawRectangle(); // not call the rectangle class
	}
}
