package Chapter15.FactoryMethod;

/**
 * Used With Car
 * Used With CarFactoryMethod
 * @author Woo
 *
 */
public class CarBenz implements Car {
	@Override
	public void Run() {
		System.out.println("Benz Is Crashing...");
	}
}
