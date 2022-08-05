package Chapter15.Strategy;

/**
 * Manage all the concrete strategy classes
 * Interact with the concrete strategy classes
 * Separate the business logic(Strategy) with the client so that the business can vary
 * independently without the client
 * @author Woo
 *
 */
public class Context {
	private Strategy Strategy; // use this strategy as the current strategy
	
	// use constructor to inject the strategy
	public Context(Strategy strategy) {
		super();
		Strategy = strategy;
	}
	
	/*
	 * Also, we can use the dependency of Spring to inject the Strategy by using 
	 * configuration file. Can switch the business logic dynamically with dynamic 
	 * injection of Strategy classes.
	 */
	
	// use setter to inject the strategy
	public void setStrategy(Strategy strategy) {
		Strategy = strategy;
	}
	
	// can customize what method u want
	public void printPrice(double price) {
		System.out.println("The price we can offer: " + Strategy.getPrice(price) );
	}
}
