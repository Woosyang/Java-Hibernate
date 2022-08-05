package Chapter15.Memento;
import java.util.*;

/**
 * CareTaker class, store the memento
 * @author Woo
 *
 */
public class CareTaker {
	private EmployeeMemento Memento;
	// if you want to store multiple memento state, u can use a collection to store them
	// private List<EmployeeMemento> List; 
	// use Stack is the best choice because of the order of the output
	// last one to store is recent one to recover
	public EmployeeMemento getMemento() {
		return Memento;
	}

	public void setMemento(EmployeeMemento memento) {
		Memento = memento;
	}
}
