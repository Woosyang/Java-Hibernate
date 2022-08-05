package Chapter15.Template;

/**
 * Test The Template(Method) Pattern
 * The code for a certain process(skeleton of the code) is already available, but the 
 * code of some specific parts is temporarily uncertain. Therefore, we adopt the 
 * factory method pattern to render the code implementation of those parts to the 
 * subclass to complete. That means the general process of the program is already 
 * defined in the father class, the concrete implementation of the abstract part
 * (abstract method) will be left to the its children classes.
 * @author Woo
 *
 */
/*
 * 
 * In Template pattern, an abstract class exposes defined way(s)/template(s) to execute
 * its methods. Its subclasses can override the method implementation as per need but 
 * the invocation is to be in the same way as defined by an abstract class. This 
 * pattern comes under behavior pattern category.
 * 
 * When construct a program, and the steps for fulfilling it is confirmed but only a
 * part of the steps will be uncertain, then we do the abstraction for the uncertain part
 * and realized it with sub classes. (Time to use template pattern)
 */
public class TemplateTest {
	public static void main(String[] args) {
		BankTemplate Bt01 = new WithDraw();
		Bt01.Process(); // Transact() will be "Withdraw the money.."
		
		// use anonymous class to realize the Transact()
		BankTemplate Bt02 = new BankTemplate() {
			// Transact() redefined again
			@Override
			public void Transact() {
				System.out.println("Scan the paycheck..");
			}
		};
		System.out.println("----------------------");
		Bt02.Process();
		System.out.println("----------------------");
		BankTemplate Bt03 = new Deposit();
		Bt03.Process(); 
	}
	
}

// child class for implementing the abstract method in father class(BankTemplate)
class WithDraw extends BankTemplate {
	@Override
	public void Transact() {
		System.out.println("Withdraw the money..");
	}
}

class Deposit extends BankTemplate {
	@Override
	public void Transact() {
		System.out.println("Deposit 100K$....");
	}
}