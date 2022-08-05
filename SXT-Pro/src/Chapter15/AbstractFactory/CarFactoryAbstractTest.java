package Chapter15.AbstractFactory;

/**
 * Used With CarFactoryAbstract
 * @author Woo
 *
 */
public class CarFactoryAbstractTest {
	public static void main(String[] args) {
		// can not create a new car, only create the componnet
		CarFactoryAbstract GoodFactory = new GoodCarFactory();
		Engine GoodEngine = GoodFactory.createEngine();
		GoodEngine.Run();
		
		CarFactoryAbstract MediumFactory = new MediumCarFactory();
		Seat MediumSeat = MediumFactory.createSeat();
		MediumSeat.SitOn();
		
		CarFactoryAbstract BadFactory = new BadCarFactory();
		Tyre BadTyre = BadFactory.createTyre();
		BadTyre.Revolve();
	}
}
