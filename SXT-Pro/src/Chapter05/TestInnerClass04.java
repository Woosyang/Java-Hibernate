package Chapter05;

/**
 * Test the local inner class
 * @author Woo
 *
 */

public class TestInnerClass04 {
	 public void Show() {
	        // only can be used in the method
	        class Inner {
	            public void Fun() {
	                System.out.println("helloworld");
	            }
	        }
	        new Inner().Fun();
	    }
	    public static void main(String[] args) {
	        new TestInnerClass04().Show();
	    }
}
