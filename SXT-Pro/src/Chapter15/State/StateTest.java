package Chapter15.State;

/**
 * Test the State Pattern
 * State Pattern is used to solve the problem that a complicated object which has  
 * multiple states to switch to in the system. It also encapsulates the relative method
 * for each different state.
 * @author Woo
 *
 */
/*
 * In State pattern, a class behavior changes based on its state. This type of design 
 * pattern comes under behavior pattern.
 * In State pattern, we create objects which represent various states and a context 
 * object whose behavior varies as its state object changes.
 * 
 */
public class StateTest {
	public static void main(String[] args) {
		Context Ctx = new Context();
		Ctx.setState(new AvailableState());
		Ctx.setState(new BookedState());
		Ctx.setState(new OccupiedState());
	}
}
