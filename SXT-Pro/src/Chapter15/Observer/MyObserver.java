package Chapter15.Observer;
import java.util.*;

/**
 * Example Of The Observer Pattern
 * @author Woo
 *
 */
public interface MyObserver {
	public void Update(Subject Sub);
}

class ConcreteObserver01 implements MyObserver {
	private int ObState; // the state should be the same as the subject's State
	
	@Override
	public void Update(Subject Sub) {
		ObState = ((ConcreteSubject01) Sub).getState(); // needs to be cast
	}

	public int getObState() {
		return ObState;
	}

	public void setObState(int obState) {
		ObState = obState;
	}
}

// use java.util.Observer to create the Observer
class ConcreteObserver02 implements Observer {
	private int ObState;

	@Override
	public void update(Observable o, Object arg) {
		ObState = ((ConcreteSubject02) o).getState();
	}

	public int getObState() {
		return ObState;
	}

	public void setObState(int obState) {
		ObState = obState;
	}
}