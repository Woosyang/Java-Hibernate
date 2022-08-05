package Chapter15.Strategy;

/**
 * Example Of Strategy Pattern
 * @author Woo
 *
 */
public interface Strategy {
	public double getPrice(double standardPrice);
}

// concrete class of Strategy
// compare with the example without strategy 
class NewCustomerWithAFewOrders implements Strategy {
	@Override
	public double getPrice(double standardPrice) {
		System.out.println("No Discount");
		return standardPrice;
	}
}

class NewCustomerWithManyOrders implements Strategy {
	@Override
	public double getPrice(double standardPrice) {
		System.out.println("10% Off");
		return standardPrice * 0.9;
	}
}

class RepeatCustomerWithAFewOrders implements Strategy {
	@Override
	public double getPrice(double standardPrice) {
		System.out.println("15% Off");
		return standardPrice * 0.85;
	}
}

class RepeatCustomerWithManyOrders implements Strategy {
	@Override
	public double getPrice(double standardPrice) {
		System.out.println("20% Off");
		return standardPrice * 0.8;
	}
}
