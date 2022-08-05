package Chapter15.Memento;

/**
 * Example Of Memento Pattern
 * Memento stores the internal state of the Originator object and prevent other objects
 * except Originator to access the Memento
 * @author Woo
 *
 */
// Memento class
public class EmployeeMemento {
	private String Ename;
	private int Eage;
	private double Esalary;
	
	// store all the information of the Originator Employee
	public EmployeeMemento(Employee E) {
		Ename = E.getName();
		Eage = E.getAge();
		Esalary = E.getSalary();
	}

	public String getEname() {
		return Ename;
	}

	public void setEname(String ename) {
		Ename = ename;
	}

	public int getEage() {
		return Eage;
	}

	public void setEage(int eage) {
		Eage = eage;
	}

	public double getEsalary() {
		return Esalary;
	}

	public void setEsalary(double esalary) {
		Esalary = esalary;
	}
}
