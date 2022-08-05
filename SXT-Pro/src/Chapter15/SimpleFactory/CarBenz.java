package Chapter15.SimpleFactory;

/**
 * Used With Car
 * Used With CarSimpleFactory
 * @author Woo
 *
 */
public class CarBenz implements Car {
	@Override
	public void Run() {
		System.out.println("Benz Is Crashing...");
	}
}
