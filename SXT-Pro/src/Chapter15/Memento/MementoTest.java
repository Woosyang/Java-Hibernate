package Chapter15.Memento;

/**
 * Test The Memento Pattern
 * Save the previous internal state in a certain object(Copy)
 * Restore the object back to the state
 * @author Woo
 *
 */
/*
 * 
 * Memento pattern is used to restore state of an object to a previous state. Memento 
 * pattern falls under behavioral pattern category.
 * Memento pattern uses three actor classes. Memento contains state of an object to be 
 * restored. Originator creates and stores states in Memento objects and Caretaker 
 * object is responsible to restore object state from Memento.
 * 
 */
public class MementoTest {
	public static void main(String[] args) {
		CareTaker Taker = new CareTaker();
		Employee Emp = new Employee("Leech", 32, 50000);
		System.out.println("first time to print out the object: ");
		System.out.println("Name: " + Emp.getName() + ", Age: " + Emp.getAge() + ", Salary: " + Emp.getSalary());
		
		Taker.setMemento(Emp.Memento()); // first copy
		
		// revise the Emp
		Emp.setAge(18);
		Emp.setName("Song");
		Emp.setSalary(30000);
		System.out.println("second time to print out the object: ");
		System.out.println("Name: " + Emp.getName() + ", Age: " + Emp.getAge() + ", Salary: " + Emp.getSalary());
		
		Emp.Recovery(Taker.getMemento()); // restore the Employee back to the state stored by Memento object
		
		System.out.println("third time to print out the object: ");
		System.out.println("Name: " + Emp.getName() + ", Age: " + Emp.getAge() + ", Salary: " + Emp.getSalary());
	}
}
