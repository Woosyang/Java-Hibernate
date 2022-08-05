package Chapter15.SimpleFactory;

/**
 * Used With Car(Interface), CarBenz, CarAudi To Test The Case 
 * Without Simple Factory Design Pattern
 * 
 * Used With Car(Interface), CarBenz, CarAudi To Test With CarSimpleFactory01/02
 * @author Woo
 *
 */
public class CarSimpleFactoryTest {
	public static void main(String[] args) {
		// if there is no simple factory design pattern
		// when you want to create an object, you have 
		// to keep using "new" to instantiate
		// eg
		Car Audi01 = new CarAudi(); // keep using "new" to create an object, not good
		Car Benz01 = new CarBenz(); // keep using "new" to create an object, not good
		Audi01.Run();  
		Benz01.Run();
		
		/*
		 * instantiate the object, use the factory pattern rather than "new"
		 * manage and control all of the created objects and instantiated classes
		 * decouple for the client who is calling them(objects and classes)
		 */
		// used to produce any product in the same hierarchical level. 
		// (for creating new products, you need to modify the code)
		System.out.println("Use Factory Pattern: ");
		CarSimpleFactory01.createCar("Audi").Run();
		CarSimpleFactory01.createCar("Benz").Run();
		System.out.println(".........................");
		CarSimpleFactory02.createCarAudi().Run();
		CarSimpleFactory02.createCarBenz().Run();
	}
}
