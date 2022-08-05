package Chapter11;

/**
 * derive the lambda expression
 * avoid to instantiate too many anonymous classes 
 * simplify the usage of the Thread(used once)
 * @author Woo
 *
 */
public class TestLambdaExpression {
	// static inner class
	static class Test01 implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 10; i ++) {
				System.out.println(i);
			}
		}
	}
	
	public static void main(String[] args) {
		// class TestLambdaExpression is not used, it will not be compiled
		new Thread(new Test01()).start(); 
		
		// improving: move the static inner class Test01 in to main()
		// use Test02 to represent
		// local inner class, in the method
		class Test02 implements Runnable {
			@Override
			public void run() {
				for (int i = 0; i < 10; i ++) {
					System.out.println(i);
				}
			}
		}
		new Thread(new Test02()).start();
		
		// improving: use anonymous inner class
		// must use interface or the its father class
		new Thread(new Runnable() {
			public void run() { // override the method
				for (int i = 0; i < 10; i ++) {
					System.out.println(i);
				}
			}
		}).start();
		
		// JDK8-> simplify: lambda derivation
		// omit the name of the anonymous inner class
		// omit the name of the method needs to be overridden 
		// (if the anonymous class only has this method)
		new Thread(()-> { // override the method
			for (int i = 0; i < 10; i ++) {
				System.out.println(i);
			}
		}).start();
		
		// Practice: void Runnable() { }          
		new Thread( () -> { System.out.println("James"); } ).start();
		// only has one line inside the method, can omit the {} and ;
		new Thread( () -> System.out.println("Keegan") ).start();
	}
}
