package Chapter15.AbstractFactory;

/**
 * Used With Engine, Seat, Tyre
 * the related factory for the related product
 * Abstract Factory is a creational design pattern that lets you produce families of 
 * related objects without specifying their concrete classes.
 * @author Woo
 *
 */
// can not create a new product, only create a new set of components of the product
public interface CarFactoryAbstract {
	public Engine createEngine();
	public Seat createSeat();
	public Tyre createTyre();
}

// can combine each items from the components of the product
// to customize your own factory but the factory has to have
// every components from the product 
class GoodCarFactory implements CarFactoryAbstract {
	@Override
	public Engine createEngine() {
		return new GoodEngine();
	}

	@Override
	public Seat createSeat() {
		return new GoodSeat();
	}

	@Override
	public Tyre createTyre() {
		return new GoodTyre();
	}
}

class MediumCarFactory implements CarFactoryAbstract {
	@Override
	public Engine createEngine() {
		return new MediumEngine();
	}

	@Override
	public Seat createSeat() {
		return new MediumSeat();
	}

	@Override
	public Tyre createTyre() {
		return new MediumTyre();
	}
}

class BadCarFactory implements CarFactoryAbstract {
	@Override
	public Engine createEngine() {
		return new BadEngine();
	}

	@Override
	public Seat createSeat() {
		return new BadSeat();
	}

	@Override
	public Tyre createTyre() {
		return new BadTyre();
	}
}

