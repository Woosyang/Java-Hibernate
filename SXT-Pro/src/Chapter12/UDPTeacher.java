package Chapter12;

/**
 * Use multiple threads to fulfill communication mutually -> simulate 2 people talk to each other
 * Based on UDPReceivingPortOfCommunication
 * @author Woo
 *
 */
public class UDPTeacher {
	public String Name; 
	public UDPTeacher(String Name) {
		this.Name = Name;
	}
	public static void main(String[] args) {
		UDPStudent S = new UDPStudent("Sam");
		//                             			 match UDP student
		new Thread(new UDPReceivingPortOfCommunication(6666, S.Name)).start(); // receive
		// 															match UDP student
		new Thread(new UDPSendingPortOfCommunication(5555, "localhost", 7777)).start();
	}
}
