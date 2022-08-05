package Chapter12;
import java.net.*;
import java.io.*;

/**Basic Process
 * Client _Data_=> Server 
 * Receive: Unwrapping -> Use IO stream to receive the data
 * reference data type -> use ObjectInputStream with ByteArrayInputStream
 * convert the byte[] back to reference data type
 * 1. use DatagramSocket to designate an port and let this port to become 
 * 	  to the receiving port.
 * 2. prepare a container and encapsulate it into a DatagramPacket Packet
 * 3. receive the packet(DatagramPacket p) with a blocked state
 * 4. analyze the data using byte[]: getData(), getLength() <-> step II of the UDPClient
 * 5. release the resource
 * @author Woo
 *
 */
public class UDPReferenceDataTypeServer {
	public static void main(String[] args) throws Exception {
		// use DatagramSocket to designate an port and let this port to become to the receiving port.
		System.out.println("The Receiving Port Is Starting Now.....");
		// 1. use DatagramSocket to designate an port and let this port to become to the sending port.
		// the port number can't be used by other programs which use the same protocol
		// port collision -> Exception: Address already in use....
		DatagramSocket Server = new DatagramSocket(5188);
		// 2. prepare a container and encapsulate it into a DatagramPacket Packet
		byte[] Container = new byte[1024 * 80]; // 8k
		DatagramPacket Packet = new DatagramPacket(Container, 0, Container.length);
		// 3. receive the packet(DatagramPacket p) with a blocked state
		Server.receive(Packet);
		// 4. analyze the data using byte[]: getData(), getLength()
		byte[] Data = Packet.getData(); // return an array
		// int Len = Packet.getLength(); // Data.length()
		// byte[] -> primitive data type
		// read -> required byte[] as the parameter in ByteArrayInputStream
		ByteArrayInputStream Bis = new ByteArrayInputStream(Data);
		ObjectInputStream Ois = new ObjectInputStream(Bis);
		// the order of reading out should be matched as writing in
		int a = Ois.readInt();
		boolean b = Ois.readBoolean();
		char c = Ois.readChar();
		Object d = Ois.readObject();
		System.out.println("Use ObjectOutputStream With ByteArrayAOutputStream: ");
		System.out.println(a + "\n"+ b +"\n" + c);
		if (d instanceof String) {
			System.out.println((String) d);
		}
		Ois.close();
		// 5.release the resource
		Server.close();
	}
}
