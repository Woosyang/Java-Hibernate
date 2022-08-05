package Chapter15.Observer;
import java.util.*;

/**
 * Example Of Observer
 * In this case, subject is the targeted object or observable, when something changed
 * in the subject, it needs to notify a set of other objects(Observers) and make them
 * respond to the changing. (Chat Room)
 * Subject is the message producer and the observers are the message subscriber
 * @author Woo
 *
 */
// there are two ways to notify the observer, One is use push which means subject will
// send the message to all the observer and make them receive the message passively, Two
// is pull which means observer only needs to know the message is sent and whether they
// (observers) will receive the message is ups to them
public class Subject {
	// when the case is 1 to n, it is the time to use Observer Pattern
	public List<MyObserver> List = new ArrayList<>();
	// add/delete other observers
	public void registerObserver(MyObserver Obs) {
		List.add(Obs);
	}
	public void removeObserver(MyObserver Obs) {
		List.remove(Obs);
	}
	
	// notify all the observer to update their states
	public void notifyAllObservers() {
		for (MyObserver Obs : List) {
			Obs.Update(this); // this is Subject
		}
	}
}

// concrete subject class
class ConcreteSubject01 extends Subject {
	private int State; // the own state of the real targeted class

	public int getState() {
		return State;
	}

	public void setState(int state) {
		State = state;
		// something has changed in the subject, please tell all the observers
		this.notifyAllObservers();
	}
}

// use java.util.Observable to create the subject class
@SuppressWarnings("all")
class ConcreteSubject02 extends Observable {
	private int State;
	
	public void Set(int S) {
		State = S; // the status of the target has been changed
		setChanged(); // confirm that the state has been changed
		notifyObservers(State); // notify all of the observers
	}

	public int getState() {
		return State;
	}

	public void setState(int state) {
		State = state;
	}
}

