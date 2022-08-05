package Chapter15.FactoryMethod;

/**
 * Used With Audi
 * Used With CarFactoryMethod
 * @author Woo
 *
 */
public class CarAudiFactoryMethod implements CarFactoryMethod {
	@Override
	public Car createCar() {
		return new CarAudi();
	}
}
