import Chapter05.Human;

/**
 * Test the encapsulation
 * @author Woo
 *
 */

public class TestEncapsulation02 {
	public static void main(String[] args) {
		Human a = new Human();
		// a.age = 8; error, private
		// a.name = "Bao"; error, it is default and can't be accessed by the class of different packets
		// a.height = 0; error
		a.SayAge(); // even it is public, it still need to be imported
	}

	class Male extends Human {
		void SayHi() {
			System.out.println(height); // Subclass can use the fields of the superclass;
		}
	}
}
