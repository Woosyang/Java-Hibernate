package Chapter15.FactoryMethod;

/**
 * Used With Car.java
 * Used With CarAudiFactoryMethod.java
 * @author Woo
 *
 */
public class CarAudi implements Car {
	@Override
	public void Run() {
		System.out.println("Audi Is Running...");
	}
}
