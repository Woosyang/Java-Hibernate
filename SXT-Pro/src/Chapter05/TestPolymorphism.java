package Chapter05;

/**
 * Testing the Polymorphism
 * @author Woo
 *
 */

public class TestPolymorphism {
	public static void main(String[] args) {
		Animal a = new Animal();
		AnimalCry(a);
		Dog b = new Dog();
		b.watch();
		Animal c = new Dog(); // legal, automatic upward casting
		// c.watch(), error, if you want call the method, you have to do the casting
		Dog d = (Dog)c;
		d.watch(); // legal, forced downward casting
		Cat x = new Cat();
		// Dog y = (Dog)x; error
		AnimalCry(c);
		AnimalCry(b); // polymorphism happens
		AnimalCry(new Cat()); // polymorphism happens
		String str; // has the final type
	}
	
	static void AnimalCry(Animal a) { // a is just an argument
		a.shout();
	}
	/*
	static void AnimalCry(Dog a) {
		a.shout();
	}
	If there is no polymorphism, you have to override the method again and again
	static void AnimalCry(Cat a) {
		a.shout();                                                                                                                                                                                                                                                                       
	}
	*/
}
// final class 
class Animal {
	public void shout() {  /* final public void */
		System.out.println("make a voice..");
	}
}

class Dog extends Animal {
	public void shout() {
		System.out.println("bark~");
	}
	public void watch() {
		System.out.println("focus!");
	}
}

class Cat extends Animal {
	public void shout() {
		System.out.println("meow-");
	}
}
