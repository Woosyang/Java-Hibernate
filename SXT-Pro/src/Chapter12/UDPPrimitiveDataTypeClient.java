package Chapter12;
import java.io.*;
import java.net.*;

/**Basic Process
 * Client _Data_=> Server
 * Send: Wrapping -> Use IO stream to send the data
 * primitive data type -> use DataOutputStream with ByteArrayOutputStream
 * convert any data from primitive data type to byte[](no need to cast those data to char)
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
public class UDPPrimitiveDataTypeClient {
	public static void main(String[] args) throws Exception {
		System.out.println("The Sending Port Is Starting Now.....");
		// 1. use DatagramSocket to designate an port and let this port to become to the sending port.
		DatagramSocket Client = new DatagramSocket(8090);
		// 2. prepare the data and convert it into byte[]
		// String Data = "All The Leaves Are Brown";
		// use DataOutputStream
		ByteArrayOutputStream Bos = new ByteArrayOutputStream();
		DataOutputStream Dos = new DataOutputStream(Bos);
		Dos.writeInt(26);
		Dos.writeUTF("Wu");
		Dos.writeBoolean(true);
		Dos.writeChar('A');
		Dos.flush();
		byte Datas[] = Bos.toByteArray(); // for Data: convert any primitive data type to byte[]
		// 3. encapsulate the byte[] into a DatagramPacket Packet(need a designated destination)
		// convert it into the packet                                                    destination   
		// for primitive data type
		DatagramPacket Packet = new DatagramPacket(Datas, 0, Datas.length, new InetSocketAddress("localhost", 5188));
		// 4. send the packet(DatagramPacket p)
		Client.send(Packet);
		// 5. release the resource
		Client.close(); // like IO
	}
}
