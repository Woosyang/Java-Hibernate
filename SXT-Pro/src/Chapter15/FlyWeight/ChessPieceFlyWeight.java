package Chapter15.FlyWeight;

/**
 * Example Of Fly Weight(WeiQi Game, Black & White) 
 * @author Woo
 *
 */
// abstract fly weight class
// It is an an interface or abstract class that declares public methods. These methods 
// can provide the internal state of the object to outer for setting the external 
// state.
public interface ChessPieceFlyWeight {
	public void setColor(String color);
	public String getColor();
	public void Display(Position position);
}

// concrete fly weight, internal state
// use the member variables to store the data
class ConcreteChessPiece implements ChessPieceFlyWeight {
	private String Color; // member variable
	
	@Override
	public void setColor(String color) {
		this.Color = color;	
	}

	@Override
	public String getColor() {
		return this.Color;
	}

	@Override
	public void Display(Position position) {
		System.out.println("The color of the chess piece: " + this.Color);
		System.out.println("The coordinate of the chess piece: (" + position.getX() + ", " + position.getY() + ")" );
	}
}
