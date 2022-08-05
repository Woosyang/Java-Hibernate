package Chapter15.Decorator;

/**
 * Test the Decorator Pattern
 * It can decouple for the system and delete or add the function of the concrete 
 * component dynamically. It can also make concrete component and concrete decorator
 * varies independently in order to make more new concrete components or concrete
 * directors.
 * Advantage: 1. it is very flexible to extend the obeject's function and will not increase
 * 			  the number of the classes.
 * 			  2. it can decorate an object with multiple times to create a combinantion
 * 				 with different behaviors to get a powerful object.
 * Weakness: will create a lot of subclasses, cost the memory. 
 * @author Woo
 *
 */
/*
 * Decorator pattern allows a user to add new functionality to an existing object 
 * without altering its structure. This type of design pattern comes under structural 
 * pattern as this pattern acts as a wrapper to existing class.
 * This pattern creates a decorator class which wraps the original class and provides 
 * additional functionality keeping class methods signature intact.
 */
public class DecoratorTest {
	public static void main(String[] args) {
		IdealCar Car = new Car(); // concrete component
		System.out.println("Normal Car: ");
		Car.Run(); // original function
		
		System.out.println("Let the Car can fly..");
		AirCar AirCar = new AirCar(Car);
		AirCar.Fly();
		
		System.out.println("Let the Car can float on the water..");
		AmphibiousCar WaterCar = new AmphibiousCar(Car);
		WaterCar.Float();
		
		System.out.println("Let the Car can fly and can battle..");
		// let original car become to the air car first
		// the let the air car become to the battle car
		// which means let the original car have 2 more functions
		BattleCar Bcar = new BattleCar(AirCar);
		Bcar.Run(); // 2 more function
	}
}

