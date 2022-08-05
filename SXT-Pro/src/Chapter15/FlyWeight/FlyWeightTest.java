package Chapter15.FlyWeight;

/**
 * Test The FlyWeight Pattern
 * @author Woo
 *
 */
/*
 * 
 * FlyWeight pattern is primarily used to reduce the number of objects created and 
 * to decrease memory footprint and increase performance. This type of design pattern 
 * comes under structural pattern as this pattern provides ways to decrease object 
 * count thus improving the object structure of application.
 * 
 * FlyWeight pattern tries to reuse already existing similar kind objects by storing 
 * them and creates new object when no matching object is found.
 * 
 */
public class FlyWeightTest {
	public static void main(String[] args) {
		// no black piece then create a new one in the fly weight pool
		ChessPieceFlyWeight C01 = ChessPieceFlyWeightFactory.getChessPiece("Black");
		// same object, "Black" is the internal state
		ChessPieceFlyWeight C02 = ChessPieceFlyWeightFactory.getChessPiece("Black");
		System.out.println(C01 == C02); // true
		
		System.out.println("add the operation of the external state: ");
		C01.Display(new Position(10, 20)); // position is the external state
		C02.Display(new Position(15, 25)); // position is the external state
	}
}
