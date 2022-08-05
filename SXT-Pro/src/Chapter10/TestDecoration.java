package Chapter10;

/**
 * get familiar with designing pattern
 * eg: use amplifier to amplify the voice
 * eg: coffee with sugar or milk
 * make the new class has more powerful functions
 * the factors of decorating:
 * 1. Component Interface: 
 * The interface or abstract class defining the methods that will be implemented.
 * 2. Component Implementation:
 * The basic implementation of the component interface.
 * 3. Decorator: 
 * Decorator class implements the component interface and 
 * it has a HAS-A relationship with the component interface
 * 4. Concrete Decorators:
 * Extending the base decorator functionality and 
 * modifying the component behavior accordingly.
 * @author Woo
 *
 */
public class TestDecoration {
	public static void main(String[] args) {
		Person P = new Person(); // initial variable in the Person is 10 
		P.Say();
		// decorating
		Amplifier Am = new Amplifier(P);
		Am.Say();
		//++++++++++//
		Drink coffee = new Coffee();
		// original
		System.out.println("The Name: " + coffee.Info() + ", " + "Price: " + coffee.Cost());
		// decorating
		Drink milk = new Milk(coffee);
		System.out.println("The Name: " + milk.Info() + ", " + "Price: " + milk.Cost());
		Drink sugar = new Sugar(coffee);
		System.out.println("The Name: " + sugar.Info() + ", " + "Price: " + sugar.Cost());
		// combination
		milk = new Milk(sugar);
		System.out.println("The Name: " + milk.Info() + ", " + "Price: " + milk.Cost());
	}
}

interface Voice {
	public void Say();
}

class Person implements Voice {
	private int Sound = 10;
	@Override
	public void Say() {
		System.out.println("The Decibel Of The Sound: " + this.GetSound());
	}
	public void SetSound(int Sound) {
		this.Sound = Sound;
	}
	public int GetSound() {
		return this.Sound;
	}
}

class Amplifier implements Voice {
	private Person P; // null at first
	// constructor
	public Amplifier(Person P) {
		this.P = P; // P has fields
	}
	@Override
	public void Say() {
		// decorating
		System.out.println("The Decibel Of The Sound: " + P.GetSound() * 100);
		System.out.println("Noise");
	}
}

//+++++++++++++++++++++++++++++++++++++++++++++//
// Component Interface
interface Drink {
	// the methods that will be implemented
	public double Cost(); // price
	public String Info(); // drink's name
}

// Component Implementation
// object needs to be decorated
class Coffee implements Drink {
	private double Cost = 5;
	private String Info = "Instant Coffee";
	@Override
	public double Cost() {
		return this.Cost;
	}
	@Override
	public String Info() {
		return this.Info;
	}
}

// abstract decorating object: let the new class has more powerful functions
abstract class Processing implements Drink {
	Drink D;
	public Processing(Drink D) {
		this.D = D;
	}
	public double Cost() {
		return this.D.Cost();
	}
	public String Info() {
		return this.D.Info();
	}
}

// 	object that is used to decorate
class Milk extends Processing {
	public Milk(Drink D) {
		super(D);
	}
	@Override
	public double Cost() {
		return this.D.Cost() * 2;
	}
	@Override
	public String Info() {
		return this.D.Info() + " with milk";
	}
}

// on the base of class D
class Sugar extends Processing {
	public Sugar(Drink D) {
		super(D);
	}
	@Override
	public double Cost() {
		return this.D.Cost() + 5;
	}
	@Override
	public String Info() {
		return this.D.Info() + " with sugar";
	}
}