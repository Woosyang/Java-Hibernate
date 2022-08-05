package Chapter11;
import java.util.*;

/**
 * use the thread to simulate the ticket rusher for the theater
 * use the synchronized block
 * @author Woo
 *
 */
public class HappyTheater {
	public static void main(String[] args) {
		//              total tickets
		Cinema C = new Cinema(20, "AMC");
		//                tickets will be purchased
		new Thread(new Customer(C, 10), "James").start();
		new Thread(new Customer(C, 15), "Dustin").start();
		List<Integer> CM = new ArrayList<>(); // total seats for the cinema
		for (int i = 1; i < 10; i ++) {
			CM.add(i);
		}
		CinemaPro M = new CinemaPro(CM, "WanDa");
		// the seats that customer wants to purchase
		List<Integer> C1 = new ArrayList<>();
		C1.add(1);
		C1.add(2);
		C1.add(3);
		List<Integer> C2 = new ArrayList<>();
		C2.add(6);
		C2.add(8);
		new Thread(new CustomerPro(C1, M), "Wong").start();
		new Thread(new CustomerPro(C2, M), "Lam").start();
	}
}

// decoration mode
class Customer implements Runnable {
	Cinema Cinema;
	int Seats02;
	public Customer (Cinema Cinema, int Seats) {
		this.Cinema = Cinema;
		this.Seats02 = Seats;
	}
	
	@Override
	public void run() {
		synchronized (Cinema) {
			boolean Flag = Cinema.BookTickets(Seats02);
			if (Flag) {
				// System.out.println("Purchase Failed");
				System.out.println(Thread.currentThread().getName() + "-> the number of the seats you have booked is " + Seats02);
			} else {
				// System.out.println("Purchase Succeed");
				System.out.println(Thread.currentThread().getName() + "-> seats are not enough");
			}
		}
	}
}

// improving: can chose the seats you want, simulate it by using ArrayList
// used for CinemaPro
class CustomerPro implements Runnable {
	List<Integer> Book; // seats that customer wants to book 
	CinemaPro CinemaPro;
	boolean Flag;
	
	public CustomerPro(List<Integer> Seats, CinemaPro CinemaPro) {
		this.Book = Seats;
		this.CinemaPro = CinemaPro;
	}
	
	@Override
	public void run() {
		synchronized (this.CinemaPro) {
			Flag = this.CinemaPro.BookTicket(Book);
			if (Flag) {
				System.out.println("Purchase Succeed ~ -> " + Thread.currentThread().getName() + " the seats you have booked are " + Book);
			} else {
				System.out.println("Purchase Failed .. -> " + Thread.currentThread().getName() + " seats are not enough");
			}
		}
	}
}

class Cinema {
	int Seats01; // available amounts of the seats 
	String Name; // the cinema's name
	
	public Cinema(int Seats, String Name) {
		this.Seats01 = Seats;
		this.Name = Name;
	}
	
	public boolean BookTickets(int Seats) {
		System.out.println("Welcome to " + this.Name);
		System.out.println("the available amount of the seats is: " + this.Seats01);
		if (Seats > this.Seats01) {
			// also can be typed in the run()
			System.out.println("Purchase Failed");
			return false;
		}
		this.Seats01 = this.Seats01 - Seats;
		// also can be typed in the run()
		System.out.println("Purchase Succeed");
		return true;
	}
}

// improving: using the ArrayList
class CinemaPro {
	List<Integer> Seats;
	String Name;
	
	public CinemaPro (List<Integer> Seats, String Name) {
		this.Seats = Seats;
		this.Name = Name;
	}
	
	public boolean BookTicket(List<Integer> Seats) {
		System.out.println("Welcome to " + this.Name);
		System.out.println("the available seats are: " + this.Seats);
		List<Integer> Copy = new ArrayList<>();
		Copy.addAll(this.Seats);
		// get the intersection
		Copy.removeAll(Seats);
		// some of the seats are taken
		// make the decision
		if (this.Seats.size() - Copy.size() != Seats.size()) {
			return false;
		}
		// purchase succeed
		this.Seats = Copy;
		return true;
	}
}