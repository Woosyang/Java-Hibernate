package Chapter15.FlyWeight;

/**
 * Example Of The Fly Weight Pattern
 * Used With ChessPieceFlyWeight
 * @author Woo
 *
 */
// coordinate of the chess piece
// unshared concrete fly weight:
// the external state of the object
// the field of the object/subclass can't be shared with can be made as a unshared 
// concrete fly weight
public class Position {
	private int x;
	private int y;
	
	// constructor
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
}
