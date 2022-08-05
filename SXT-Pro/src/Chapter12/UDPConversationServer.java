package Chapter12;
import java.net.*;

/**Basic Process
 * Client _Data_=> Server (fulfill multiple communications)
 * Receive: Unwrapping
 * 1. use DatagramSocket to designate an port and let this port to become 
 * 	  to the receiving port.
 * 2. prepare a container and encapsulate it into a DatagramPacket Packet
 * 3. receive the packet(DatagramPacket p) with a blocked state
 * 4. analyze the data using byte[]: getData(), getLength() <-> step II of the UDPClient
 * 5. release the resource
 * @author Woo
 *
 */
public class UDPConversationServer {
	public static void main(String[] args) throws Exception {
		// use DatagramSocket to designate an port and let this port to become to the receiving port.
		System.out.println("The Receiving Port Is Starting Now.....");
		// 1. use DatagramSocket to designate an port and let this port to become to the sending port.
		// the port number can't be used by other programs which use the same protocol
		// port collision -> Exception: Address already in use....
		DatagramSocket Server = new DatagramSocket(6666);
		// 2. prepare a container and encapsulate it into a DatagramPacket Packet
		while (true) { // has to keep receiving
			byte[] Container = new byte[1024 * 80]; // 8k
			DatagramPacket Packet = new DatagramPacket(Container, 0, Container.length);
			// 3. receive the packet(DatagramPacket p) with a blocked state
			Server.receive(Packet);
			// 4. analyze the data using byte[]: getData(), getLength()
			byte[] Data = Packet.getData(); // return an array
			int Len = Packet.getLength(); // Data.length()
			// byte[] -> String
			System.out.println(new String(Data, 0, Len));
			if (new String(Data, 0, Len).equals("Bye")) {
				break;
			}
		}
		// 5.release the resource
		Server.close();
	}
}
