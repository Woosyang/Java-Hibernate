package Chapter15.FactoryMethod;

/**
 * FactoryMethod Is The Improved Version Of The Simple Factory
 * Difference between the factory method pattern and the simple factory pattern 
 * is that there is only one simple factory pattern factory class but the factory 
 * method pattern has a set of factory classes that implement the same interface.
 * @author Woo
 *
 */
/*
 * SimpleFactory Vs FactoryMethod
 * 1. complexity of the structure of the simple factory is better
 * 2. complexity of the code for developer of the factory method is better
 *    (if you want to add more function for the product, you need to modify
 *     the factory interface for simple factory)
 * 3. factory method follows the OCP rule but simple factory does not need to new
 * 4. factory method has a lot of classes, it is very hard to manange    
 *
 */
public class FactoryMethodTest {
	public static void main(String[] args) {
		// if you want to add more products(new other objects),
		// there is no need to modify the code inside the Factory class
		// only do the extension by implementing this Factory Interface
		// with a new factory class for a new product which is implementing
		// the Product Interface
		// follow the OCP principle
		Car C01 = new CarAudiFactoryMethod().createCar(); // have to instantiate an object
		Car C02 = new CarBenzFactoryMethod().createCar(); // have to instantiate an object
		C01.Run();
		C02.Run();
	}
}
