package Chapter15.Template;

/**
 * Example Of Template Pattern
 * The template method pattern is often used in programming. It defines an algorithm 
 * skeleton in operation, delaying certain methods to be implemented in subclasses. 
 * In this way, the new subclass can redefine certain specific content of the method 
 * without changing the whole structure of the algorithm(logic).
 * @author Woo
 *
 */
// callback is a type of hook function, the child class can't call the father class
// and the callback method is realized by using father class to call child class, the
// process of the callback method is already defined in the father class and the father
// class control the whole progress of the callback
public abstract class BankTemplate {
	// concrete method
	public void waitForNumber() {
		System.out.println("Waiting in the line");
	}
	
	// withdraw, deposit money...
	public abstract void Transact(); // hook function, specific business logic
	 
	public void Evaluate() {
		System.out.println("Send the feedback");
	}
	
	// template method, use "final" to describe, combine all of the basic operations
	// (common methods all the child classes will have), this method normally will not
	// be overrode by any child class
	public final void Process() {
		this.waitForNumber();
		// the function is like a hook, it will call the method of the child class when
		// it is redefined in that class
		this.Transact(); 
		this.Evaluate();
	}
}
