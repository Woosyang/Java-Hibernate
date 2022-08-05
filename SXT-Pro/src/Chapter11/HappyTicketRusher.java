package Chapter11;

/**
 * use the thread to simulate the ticket rusher
 * use the synchronized method
 * @author Woo
 *
 */
public class HappyTicketRusher {
	public static void main(String[] args) {
		Station S = new Station(10, "Port");
		// like new Thread(S).start();
		//                       Runnable
		new Passenger(5, "Wallace", S).start();
		new Passenger(4, "Bush", S).start();
	}
}

// proxy
class Passenger extends Thread {
	// Station Station;
	int Perk;
	// String Name;
	public Passenger(int Perk, String Name, Runnable Target) {
		super(Target, Name); // this.Name = Name; this.Station = Station;
		this.Perk = Perk;
	}
}

class Station implements Runnable {
	int Ticket;
	String Name;
	
	public Station(int Ticket, String Name) {
		this.Ticket = Ticket;
		this.Name = Name;
	}
	
	// use the synchronized method
	// lock this object
	public synchronized boolean BookTicket(int Tickets) {
		// System.out.println("the available amount of the tickets is: " + Ticket);
		if (Tickets > Ticket) {
			// System.out.println("Purchase Failed..."); wrong, can't be here
			System.out.println("the available amount of the tickets is: " + Ticket);
			return false;
		} else {
			// System.out.println("Purchase Done..."); wrong, can't be here
			System.out.println("the available amount of the tickets is: " + Ticket);
			this.Ticket = this.Ticket - Tickets;
			return true;
		}
	}

	@Override
	public void run() {
		// Passenger is the child class of the Thread
		Passenger P = (Passenger) Thread.currentThread(); // cast
		boolean Flag = BookTicket(P.Perk);
		if (Flag) {
			// System.out.println("Purchase Done..."); concurrent issue
			System.out.println("Purchase Done...: " + P.getName() + "-----> " + "the number of the ticket you have booked is: " + P.Perk);
		} else {
			// System.out.println("Purchase Failed..."); concurrent issue
			System.out.println("Purchase Failed...: " + P.getName() + "-----> " + "the number of the tickets are not enough");
		}
	}
}