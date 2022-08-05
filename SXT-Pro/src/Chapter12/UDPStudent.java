package Chapter12;

/**
 * Use multiple threads to fulfill communication mutually -> simulate 2 people talk to each other
 * Based on UDPSendingPortOfCommunication
 * @author Woo
 *
 */
public class UDPStudent {
	public String Name;
	public UDPStudent(String Name) {
		this.Name = Name;
	}
	public static void main(String[] args) {
		UDPTeacher T = new UDPTeacher("Thomas");
		//                                                         match UDPTeacher
		new Thread(new UDPSendingPortOfCommunication(8888, "localhost", 6666)).start(); // send
		//                                           match UDP Teacher
		new Thread(new UDPReceivingPortOfCommunication(7777, T.Name)).start();
	}
}
