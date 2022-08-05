package Chapter15.State;

/**
 * Example Of State Pattern
 * Similar with Strategy Pattern
 * Use the state pattern if you want to change the output(logic) of the code frequently 
 * State class is an abstract class
 * @author Woo
 *
 */
public interface State {
	// the action corresponds to the state
	public void Handle();
}

// concrete state, available
class AvailableState implements State {
	// the action corresponds to the state
	@Override
	public void Handle() {
		System.out.println("The room is empty...");
	}	
}

// concrete state, booked
class BookedState implements State {
	// the action corresponds to the state
	@Override
	public void Handle() {
		System.out.println("The room is booked...");
	}	
}

// concrete state, occupied
class OccupiedState implements State {
	// the action corresponds to the state
	@Override
	public void Handle() {
		System.out.println("The room is checked in...");
	}
}
