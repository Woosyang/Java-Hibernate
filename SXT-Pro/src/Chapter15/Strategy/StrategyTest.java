package Chapter15.Strategy;

/**
 * Test Strategy Pattern
 * The strategy pattern matches to a set of algorithms that solve a certain problem, 
 * allowing users to choose an algorithm from the set to solve a certain problem, 
 * and at the same time, it is convenient to change the algorithm or add a new 
 * algorithm. And the client can decide which algorithm to call.
 * @author Woo
 *
 */
/*
 * In Strategy pattern, a class behavior or its algorithm can be changed at run time. 
 * This type of design pattern comes under behavior pattern.
 * In Strategy pattern, we create objects which represent various strategies and a 
 * context object whose behavior varies as per its strategy object. The strategy object 
 * changes the executing algorithm of the context object.
 * 
 */
public class StrategyTest {
	public static void main(String[] args) {
		Strategy S01 = new NewCustomerWithAFewOrders(); // strategy 1
		Strategy S02 = new NewCustomerWithManyOrders(); // strategy 2
		Strategy S03 = new RepeatCustomerWithAFewOrders(); // strategy 3
		Strategy S04 = new RepeatCustomerWithManyOrders(); // strategy 4
		
		// new customer with a few orders
		Context C = new Context(S01);
		C.printPrice(1000);
		
		// new customer with many orders
		C = new Context(S02);
		C.printPrice(1000);
		
		// repeat customer with a few orders
		C = new Context(S03);
		C.printPrice(1000);
		
		// repeat customer with many orders
		C = new Context(S04);
		C.printPrice(1000);
	}
}
