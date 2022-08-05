package Chapter15.Command;

/**
 * 
 * @author Woo
 *
 */
public interface Command {
	// for this example, this is just a void method
	// we can customize the method according to the requirement of the project
	// eg public int Order()
	public void Order();
}

// concrete command, real command class
class ConcreteCommand implements Command {
	// has the reference of the object who is executing the command
	private Receiver Receiver;
	public ConcreteCommand(Receiver receiver) {
		super();
		this.Receiver = receiver;
	}
	
	@Override
	public void Order() {
		// add some code to do the relative operation to the command 
		// before the command is executed
		// code line, operation relates to the command, such as log...
		
		// call the receiver to execute the command
		Receiver.Execute();
		
		// add some code to do the relative operation to the command 
		// after the command is executed
		// code line, operation relates to the command, such as log..
	}
}