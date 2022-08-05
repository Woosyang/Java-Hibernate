package Chapter15.FactoryMethod;

/**
 * Used With CarBenz
 * Used with CarFactoryMethod
 * @author Woo
 *
 */
public class CarBenzFactoryMethod implements CarFactoryMethod {
	@Override
	public Car createCar() {
		return new CarBenz();
	}
}
