package Chapter09;
import java.util.*;

/**
 * Test the usage of TreeMap -> Sorting
 * @author Woo
 *
 */
public class TestTreeMap {
	public static void main(String[] args) {
		Map<Integer, String> Tree01 = new TreeMap<>();
		Tree01.put(20, "James");
		Tree01.put(10, "Leo");
		Tree01.put(30, "Wave");
		Tree01.put(25, "Ted");
		// the output will be sorted by the value of the key
		// ascending sort with the value of the key
		for (Integer Key : Tree01.keySet()) {
			System.out.println(Key + " ---> " + Tree01.get(Key));
		}
		Map<Staff, String> Tree02 = new TreeMap<>();
		Staff A = new Staff(100, "James", 80000);
		Staff B = new Staff(101, "Keegan", 85000);
		Staff C = new Staff(102, "Siyang", 70000);
		Staff D = new Staff(103, "Jason", 70000);
		Tree02.put(A, "Excellent");
		Tree02.put(B, "Perfect");
		Tree02.put(C, "Good");
		Tree02.put(D, "Not Bad");
		for (Staff Key : Tree02.keySet()) {
			System.out.println(Key + "--->" + Tree02.get(Key));
		}
		System.out.println(Tree02);
	}
}

// interface Comparable needs the generic
class Staff implements Comparable<Staff> {
	int ID;
	String Name;
	double Salary;
	// constructor
	public Staff(int ID, String Name, double Salary) {
		this.ID = ID;
		this.Name = Name;
		this.Salary = Salary;
	}
	@Override
	// Ascending
	public int compareTo(Staff O) {
		// TODO Auto-generated method stub
		if (this.Salary > O.Salary) {
			return 1;
		} else if (this.Salary < O.Salary) {
			return -1;
			// have the same Salary
		} else {
			if (this.ID > O.ID) {
				return 1;
			} else if (this.ID < O.ID) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	@Override
	public String toString() {
		return "ID: " + this.ID + "Name: " + this.Name + "Salary: " + this.Salary;
	}
}