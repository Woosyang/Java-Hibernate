package Chapter15.Command;

/**
 * Test The CommandTest Pattern
 * Needs to create Invoker, ConcreteCommand.
 * Needs to designate a receiver when creating an ConcreteCommand
 * Invoker has no direct relationship with Receiver, Invoker indirectly call the Receiver
 * through the Command class
 * @author Woo
 *
 */
/*
 * 
 * Command pattern is a data driven design pattern and falls under behavioral pattern 
 * category. A request is wrapped under an object as command and passed to invoker 
 * object. Invoker object looks for the appropriate object which can handle this 
 * command and passes the command to the corresponding object which executes the 
 * command.
 *  
 */
public class CommandTest {
	public static void main(String[] args) {
		Command C = new ConcreteCommand(new Receiver());
		Invoker I = new Invoker(C);
		I.Initiate(); // send the order, receiver will obey
		// new Receiver().Execute();
	}
}
