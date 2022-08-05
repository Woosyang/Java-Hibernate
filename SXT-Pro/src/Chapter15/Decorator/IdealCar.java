package Chapter15.Decorator;

/**
 * Example Of The Decorator Pattern
 * 1. Component: Concrete Component And Decorator Implements The Same Interface(Component) 
 * 				 So that Client Can Interact With Decorator By Using The Same 
 * 				 Concrete Component 
 * 
 * 2. Concrete Component: Actual Role, eg: FileInputStream, FileOutputStream
 * 
 * 3. Decorator: Has The Reference Of The Concrete Component And Receive The Request
 * 				 From The Client. Then Forward Those Requests To The Concrete Component
 * 				 So That It Can Have More Functions After/Before Calling The Concrete 
 * 				 Component
 * 
 * 4. Concrete Decorator: Make The Concrete Component Have More Functions
 * @author Woo
 *
 */
/*
 * I. Component interface: an abstraction describing the behaviors of the components 
 * that you will eventually use in your program. Any objects that will use these 
 * components will do so through the interface, meaning that they are principally 
 * concerned with the abstraction (not the actual object). This is what allows both 
 * objects and wrapped objects to be considered to be the same type.
 * 
 */
public interface IdealCar {
	public void Run();
}

// Concrete Component: a component that implements the component interface. In doing so 
// it agree to the contract set by the interface that it will implement certain behaviors
class Car implements IdealCar {
	@Override
	public void Run() {
		System.out.println("Running On The Road...");
	}
}

// Decorator: implements the component interface and contains a pointer to some 
// instance of the same interface. Inside the decorator, each of the component 
// interface behaviors will be delegated to whichever concrete component the pointer 
// indicates.
class AdvancedCar implements IdealCar {
	IdealCar Car; // concrete component the pointer indicates.
	
	public AdvancedCar (IdealCar Car) {
		this.Car = Car;
	}
	
	@Override
	public void Run() {
		Car.Run();
	}
}

// Concrete Decorator: a subclass of the decorator abstraction. This is where the 
// action of the decorator pattern takes place. A concrete decorator ¡°wraps¡± over the 
// provided concrete component and may then either override existing behaviors or add 
// new behavior
class BattleCar extends AdvancedCar {
	public BattleCar(IdealCar Car) {
		super(Car);
	}
	
	public void Fire() {
		System.out.println("Launching...");
	}
	
	@Override
	public void Run() {
		super.Run();
		Fire();
	}
}

class AirCar extends AdvancedCar {

	public AirCar(IdealCar Car) {
		super(Car);
	}
	
	public void Fly() {
		System.out.println("In the Sky..");
	}
	
	@Override
	public void Run() {
		super.Run();
		Fly();
	}
}

class AmphibiousCar extends AdvancedCar {

	public AmphibiousCar(IdealCar Car) {
		super(Car);
	}
	
	public void Float() {
		System.out.println("Float on the water surface ");
	}
	
	@Override
	public void Run() {
		super.Run();
		Float();
	}
}