package Chapter04;

/**
 * Testing the Constructor
 * @author Woo
 *
 */

class Point {
	double x, y;
	
	// the name of constructor must be the same as the name of class
	public Point(double _x, double _y) {
		x = _x;
		y = _y;
	}
	
	//Point(){} // overload
	
	public double getDistance(Point o) {
		return Math.sqrt((x - o.x) * (x - o.x) + (y - o.y) * (y - o.y));
	}	
}

public class TestConstructor {
	public static void main(String[] args) {
		Point p  = new Point(3.0 , 4.0);
		Point origin = new Point(0.0, 0.0);
		//Point a = new Point(); // overload
		System.out.println(p.getDistance(origin)); // p.x --> x of getDistance()
	}
}
