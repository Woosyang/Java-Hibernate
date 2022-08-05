package Chapter15.Memento;

/**
 * Example Of The Memento Pattern
 * Employee is an Originator which creates a memento object to store the internal state
 * of the current time and it can use memento to restore its internal state
 * @author Woo
 *
 */
// Originator class
public class Employee {
	private String Name;
	private int Age;
	private double Salary;
	
	public Employee(String name, int age, int salary) {
		this.Name = name;
		this.Age = age;
		this.Salary = salary;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public double getSalary() {
		return Salary;
	}

	public void setSalary(double salary) {
		Salary = salary;
	}
	
	// do the operations of the memento and return the memento object
	public EmployeeMemento Memento() {
		return new EmployeeMemento(this);
	}
	
	// restore the data, back to the designated Memento object
	public void Recovery(EmployeeMemento Emt) {
		this.Name = Emt.getEname();
		this.Age = Emt.getEage();
		this.Salary = Emt.getEsalary();
	}
}
