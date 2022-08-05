package Chapter05;

/**
 * Test the Interface
 * @author Woo
 *
 */

public class TestInterface {
	public static void main(String[] args) {
		MyClass A = new MyClass();
		A.print();
		System.out.println("~~~~~~~~~~~~");
		Angel a = new Angel(); // if it is Volant a = new Angel(), it only can call the method in Volant
		a.Fly();
		a.HelpOther();
		Volant b = new BirdMan();
		b.Fly();
		Honest c = new GoodMan();
		c.HelpOther();
	}
}

interface MyInterface { // public interface must be defined in its own file
	int a = 365; // public static final int
	void print(); // public abstract void
}

class MyClass implements MyInterface {
	public void print() {
		System.out.println(a);
	}
}

interface Volant {
	int Fly_Height = 100;
	void Fly();
}

interface Honest {
	void HelpOther();
}

class Angel implements Volant, Honest {
	public void Fly() {
		System.out.println("Say you");
	}
	public void HelpOther() {
		System.out.println("Say me");
	}
}

class GoodMan implements Honest {
	public void HelpOther() {
		System.out.println("Naturally");
	}
}

class BirdMan implements Volant {
	public void Fly() {
		System.out.println("Say it together");
	}
}

// Multiple Inheritance in Interface
interface A {
	void a();
}
interface B {
	void b();
}
interface C extends A,B { // if the methods from the father class are not overrode
	void c();			  // then they can be called by the child class 
}
class D implements A,B,C { // D can only extends one object
	@Override
	public void c() {
		// TODO Auto-generated method stub
	}
	@Override
	public void b() {
		// TODO Auto-generated method stub	
	}
	@Override
	public void a() {
		// TODO Auto-generated method stub	
	} 
}