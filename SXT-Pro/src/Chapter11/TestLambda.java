package Chapter11;

/**
 * derivation of the lambda
 * @author Woo
 *
 */
public class TestLambda {
	// static inner class
	static class LoveHer implements Love {
		@Override
		public void Lambda() {
			System.out.println("Lambda02");		
		}
	}
	
	public static void main(String[] args) {
		// outer class
		Love A01 = new LoveU();
		A01.Lambda(); // Lambda01
		
		// improving: static inner class
		Love A02 = new LoveHer();
		A02.Lambda(); // Lambda02
		// improving: local inner class(inside method)
		class LoveHim implements Love {
			@Override
			public void Lambda() {
				System.out.println("Lambda03");		
			}		
		}
		Love A03 = new LoveHim();
		A03.Lambda(); // Lambda03
		
		// improving: anonymous inner class
		Love A04 = new Love() {
			public void Lambda() {
				System.out.println("Lambda04");
			}
		};
		A04.Lambda(); // Lambda04
		
		// improving: using lambda, only if the interface has only one method inside
		Love A05 = () -> { System.out.println("Lambda05"); };
		A05.Lambda(); // Lambda05
		
		/* must have the type if you want to derive the lambda expression
		 * the code below is wrong
		 * () -> { System.out.println("Lambda05");}.Lambda();
		 */
		
		System.out.println("Adding the parameters");
		
		// anonymous inner class
		/*
		Ai B0 = new Ai() { public void Love (int a) {
			System.out.println("Rmb" + a);
		}};
		*/
		Ai B0 = (int a) -> { System.out.println("Love " + a); };
		B0.Love(520);
		
		// improving: omit the type 
		Ai B1 = (a) -> { System.out.println("Like " + a); };
		B1.Love(13);
		
		// improving: omit the parentheses ()
		// only if it has one parameter
		Ai B2 = a -> { System.out.println("Feels" + a); };
		B2.Love(14);
		
		// improving: omit the bracket {}
		// only if it has one line code inside the method
		Ai B3 = a -> System.out.println("Enjoy" + a);
		B3.Love(0);
		
		System.out.println("Return something in the method");
		// the method inside the interface has type
		Aimer C0 = new Aimer() {
			public int Ai(int a, int b) {
				System.out.println("Beless");
				return a + b;
			} };
		System.out.println(C0.Ai(3, 2));
		
		// improving: omit the name of the interface and omit the name of the method
		Aimer C1 = (int a, int b) -> {
			System.out.println("My");
			return a * b;
		};
		System.out.println(C1.Ai(11, 15));
		
		// improving: omit the type but can't omit the () because it has 2 parameters
		// can't omit the {} because it has two lines inside method
		C1 = (a, b) -> { System.out.println("Famliy"); return a - b; };
		System.out.println(C1.Ai(22, 2));
		
		// override: let the method has only one line
		// same as C1 = (a, b) -> { return a / b; };
		C1 = (a, b) ->  a / b;
		System.out.println(C1.Ai(10, 5)); // 2
		// eg: return 100
		C1 = (a, b) -> 100;
		System.out.println(C1.Ai(1, 0)); // 100
	}
}

interface Love {
	public void Lambda();
	// void LambdaPro(); -> if the interface has one methods inside
	// can't use lambda expression
}

interface Ai {
	public void Love(int a);
}

interface Aimer {
	public int Ai(int a, int b); 
}

// outer class
class LoveU implements Love {
	@Override
	public void Lambda() {
		System.out.println("Lambda01");
	}
}