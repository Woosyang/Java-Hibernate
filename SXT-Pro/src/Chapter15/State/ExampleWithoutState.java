package Chapter15.State;

/**
 * Test the example without state pattern
 * Does not follow the OCP rules
 * Scenario: there are 3 states of the room in hotel: available, booked, occupied and 
 * 			 each state will vary depends on the action from the customer, if we change
 * 			 the state by using if-else statement, it will violate the OCP
 * @author Woo
 *
 */
public class ExampleWithoutState {
	public static void main(String[] args) {
		// just a simulation
		String State = "";
		String Action = "";
		if (State == "Available") {
			if (Action == "Book") {
				// code line for booking the hotel
				State = "Booked";
			} else if (Action == "Check In") {
				// code line for checking in
				State = "Occupied";
			}
		} else if (State == "Booked") {
			if (Action == "Check In") {
				// code line for checking in
				State = "Occupied";
			} else if (Action == "Check Out") {
				// code line for checking out
				State = "Available";
			}
			// what if Action is Cancel ? It will modify the code here and it violates
			// the OCP
		}
	}
}
