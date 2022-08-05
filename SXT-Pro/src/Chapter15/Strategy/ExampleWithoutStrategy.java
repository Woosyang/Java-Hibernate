package Chapter15.Strategy;

/**
 * Brute solution, it works but if we have too many classes to handle and the algorithm
 * of the code is complicated, it will cause the code have too many if-else statements.
 * Also, it will be very difficult for you to fix the code and will make you adjust the 
 * code frequently when a new class are created.
 * Does not follow the OCP principle
 * @author Woo
 *
 */
// compare it with strategy pattern
public class ExampleWithoutStrategy {
	public double getPrice(String Type, double Price) {
		if (Type.equals("New Customer With A Few Orders ")) {
			System.out.println("No Discount");
			return Price;
		} else if (Type.equals("New Customer With Many Orders ")) {
			System.out.println("10% Off");
			return Price * 0.9;
		} else if (Type.equals("Repeat Customer With A Few Orders ")) {
			System.out.println("15% Off");
			return Price * 0.85;
		} else if (Type.equals("Repeat Customer With Many Orders ")){
			System.out.println("20% Off");
			return Price * 0.8;
		}
		return Price;
	}
}
