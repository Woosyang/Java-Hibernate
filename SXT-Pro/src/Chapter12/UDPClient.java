package Chapter12;
import java.net.*;

/**Basic Process
 * Client _Data_=> Server
 * Send: Wrapping
 * 1. use DatagramSocket to designate an port and let this port to become 
 * 	  to the sending port.
 * 2. prepare the data and convert it into byte[],
 * 	  then encapsulate the byte[] into a DatagramPacket Packet(need a designated destination)
 * 	  (corresponds to the step IV of the Server)
 * 3. send the packet(DatagramPacket p)
 * 4. release the resource
 * @author Woo
 *
 */
public class UDPClient {
	public static void main(String[] args) throws Exception {
		System.out.println("The Sending Port Is Starting Now.....");
		// 1. use DatagramSocket to designate an port and let this port to become to the sending port.
		DatagramSocket Client = new DatagramSocket(8888);
		// 2. prepare the data and convert it into byte[]
		String Data = "All The Leaves Are Brown";
		byte Datas[] = Data.getBytes();
		// 3. encapsulate the byte[] into a DatagramPacket Packet(need a designated destination)
		// convert it into the packet                                                    destination   
		DatagramPacket Packet = new DatagramPacket(Datas, 0, Datas.length, new InetSocketAddress("localhost", 6666));
		// 4. send the packet(DatagramPacket p)
		Client.send(Packet);
		// 5. release the resource
		Client.close(); // like IO
	}
}
