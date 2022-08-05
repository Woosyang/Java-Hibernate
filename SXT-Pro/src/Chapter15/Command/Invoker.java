package Chapter15.Command;

/**
 * Example Of Command Pattern
 * The class invokes the command
 * @author Woo
 *
 */
/*
 * 
 * The command sender, executes the command through the command object. A caller does 
 * not need to determine its receiver at design time and it is only associated with 
 * the abstract command class. When the program is running, the execute() of the 
 * command object will be called to indirectly call the receiver's related methods.
 * 
 */
public class Invoker {
	private Command Command; // has the reference of the command
	// u can use the collection to store the command to do the batch processing
	// similar with the format of the transaction management in database
	// each command match to each receiver
	// private List<Command> commandList; // like SQL, many to many
	public Invoker(Command command) {
		super();
		Command = command; 
	}
	
	// the method to call the command's method
	public void Initiate() {
		Command.Order();
		// batch processing
	}
}
