package Chapter15.State;

/**
 * Example Of State Pattern
 * Context class maintains an instance of subclass from the State class, this instance
 * defines the one of the state from the State class 
 * @author Woo
 *
 */
// make a metaphor for this Context class, in the banking scenario, it is just like a
// banking account, it will change its state due to the balance
// Hotel
public class Context {
	private State State; // have the reference of the State
	public void setState(State S) {
		System.out.println("Change the state ~");
		State = S; // get the state
		State.Handle(); // call the action corresponds to the state
	}
}
