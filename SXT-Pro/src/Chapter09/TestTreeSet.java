package Chapter09;
import java.util.*;

/**
 * need to know how the Interface Comparable work
 * Test the usage of TreeSet
 * @author Woo
 * 
 */

// TreeSet is ordered, HashSet is not
public class TestTreeSet {
	public static void main(String[] args) {
		// Set is an interface
		// Set only has one type in the generic
		Set<Integer> Hs01 = new HashSet<>();
		Hs01.add(1);
		Hs01.add(4);
		Hs01.add(2);
		Set<Integer> Ts01 = new TreeSet<>();
		Ts01.add(100);
		Ts01.add(40);
		Ts01.add(200);
		Ts01.add(220);
		Ts01.add(200); // ignore
		Ts01.add(610);
		// order by ascending sort
		for (Integer m : Ts01) {
			System.out.println(m);
		}
		System.out.println("=============");
		// output by sorted is not guaranteed
		// this is just a coincidence
		for (Integer m : Hs01) {
			System.out.println(m);
		}
		System.out.println(Hs01);
		// System.out.println(Ts);
		System.out.println("=============");
		Member A = new Member(100, "Kang", 5000);
		Member B = new Member(101, "Ted", 6000);
		Member E = new Member(108, "Volsiki", 3500);
		Member C = new Member(102, "Hariki", 4500);
		Member D = new Member(103, "Khalid", 5500);
		Set<Member> Ts02 = new TreeSet<>();
		Set<Member> Hs02 = new HashSet<>();
		Ts02.add(A);
		Ts02.add(B);
		Ts02.add(C);
		Ts02.add(D);
		Ts02.add(E);
		Hs02.add(A);
		Hs02.add(B);
		Hs02.add(C);
		Hs02.add(D);
		Hs02.add(E);
		System.out.println("outcome of TreeSet: ");
		for (Member m : Ts02) {
			System.out.println(m);
		}
		System.out.println("outcome of HashSet£» ");
		for (Member m : Hs02) {
			System.out.println(m);
		}
 	}
}

//interface Comparable needs the generic
class Member implements Comparable<Member> {
	int ID;
	String Name;
	double Salary;
	// constructor
	public Member(int ID, String Name, double Salary) {
		this.ID = ID;
		this.Name = Name;
		this.Salary = Salary;
	}
	@Override
	// Ascending
	public int compareTo(Member O) {
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