package Chapter15.Command;

/**
 * Example Of Command Pattern
 * actual role for executing the command
 * @author Woo
 *
 */
// Receiver, do the operations related to the request and realize the function toward
// the request. Before the abstraction(building process of Command Pattern), 
// it is the real role to handle the actual request
// being handled
public class Receiver {
	public void Execute() {
		System.out.println("Follow the order");
	}
}
