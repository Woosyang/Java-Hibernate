package Chapter15.SimpleFactory;

/**
 * Used With Car.java
 * Used With CarSimpleFactory01.java
 * Used With CarAudiFactory.java
 * @author Woo
 *
 */
public class CarAudi implements Car {
	@Override
	public void Run() {
		System.out.println("Audi Is Running...");
	}
}
