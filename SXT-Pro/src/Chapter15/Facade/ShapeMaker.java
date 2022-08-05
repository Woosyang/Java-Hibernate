package Chapter15.Facade;

/**
 * Example Of Facade Pattern
 * Used With Shape
 * @author Woo
 *
 */
// encapsulate all the services that client wants
// provide an entrance for all of the sub systems
// encapsulate the complexity of all the sub systems
// make it is easy for client to call them
public class ShapeMaker {
	private Shape Circle; // sub system
	private Shape Rectangle; // sub system
	private Shape Square; // sub system
	
	// constructor
	public ShapeMaker() {
		Circle = new Circle();
		Rectangle = new Rectangle();
		Square = new Square();
	}
	
	public void drawCircle() {
		Circle.Draw(); // client directly use it
	}
	
	public void drawRectangle() {
		Rectangle.Draw(); // client directly use it
	}
	
	public void drawSquare() {
		Square.Draw(); // client directly use it
	}
}
