package Chapter12;
import java.net.*;
import java.io.*;

/**Basic Process
 * Client _Data_=> Server 
 * Receive: Unwrapping -> Use IO stream to receive the data
 * primitive data type -> use DataInputStream with ByteArrayInputStream
 * convert the byte[] back to primitive data type
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
public class UDPPrimitiveDataTypeServer {
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
		DataInputStream Dis = new DataInputStream(Bis);
		// the order of reading out should be matched as writing in
		int a = Dis.readInt();
		String b = Dis.readUTF();
		boolean c = Dis.readBoolean();
		char d = Dis.readChar();
		System.out.println("Use DataInputStream With ByteArrayInputStream: ");
		System.out.println(a + " -> "+ b +" -> " + c + " -> " + d);
		Dis.close();
		// 5.release the resource
		Server.close();
	}
}
