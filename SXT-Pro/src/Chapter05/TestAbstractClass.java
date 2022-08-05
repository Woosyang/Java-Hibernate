package Chapter05;

/**
 * Test the Abstract
 * build the template for the child class
 * and the child class must fulfill the corresponding abstract method 
 * @author Woo
 *
 */

//1. can't be fulfilled 
//2. must be fulfilled by the child class
abstract class Man {
	abstract public void Shout();
	public void Run() {
		System.out.println("Run~");
	}
}
class DogMan extends Man {
	public void Run() {
		super.Run(); // Run~
		System.out.println("Walk");
	}
	@Override
	public void Shout() {
		System.out.println("Wong~");
	}
}

public class TestAbstractClass {
	public static void main(String[] args) {
		DogMan a = new DogMan();
		a.Shout();
		a.Run();
	}
}
