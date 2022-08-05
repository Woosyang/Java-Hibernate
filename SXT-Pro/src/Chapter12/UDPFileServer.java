package Chapter12;
import java.io.*;
import java.net.*;

/**Basic Process
 * Client _Data_=> Server 
 * Receive: Unwrapping -> Use IO stream to receive the data
 * write a method to convert the byte[] to file, Notice: the size of the file can't be too big because UDP can't handle it 
 * 1. use DatagramSocket to designate an port and let this port to become 
 * 	  to the receiving port.
 * 2. prepare a container and encapsulate it into a DatagramPacket Packet
 * 3. receive the packet(DatagramPacket p) with a blocked state
 * 4. analyze the data using byte[]: getData(), getLength() <-> step II of the UDPClient
 * 5. release the resource
 * @author Woo
 *
 */
public class UDPFileServer {
	// Byte[] -> File
	public static void ByteArrayToFile(byte[] Src, String Despath) {
		File F = new File(Despath);
		int Len = 0;
		OutputStream Os = null;
		ByteArrayInputStream Bis = null;
		try {
			Os = new FileOutputStream(F);
			Bis = new ByteArrayInputStream(Src);
			byte[] Flush = new byte[1024 * 10]; // 10K
			while (Len != -1) {
				Len = Bis.read();
				if (Len == -1) {
					break;
				}
				Os.write(Flush, 0, Len);
			}
			Os.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (Os != null) {
					Os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		// use DatagramSocket to designate an port and let this port to become to the receiving port.
		System.out.println("The Receiving Port Is Starting Now.....");
		// 1. use DatagramSocket to designate an port and let this port to become to the sending port.
		// the port number can't be used by other programs which use the same protocol
		// port collision -> Exception: Address already in use....
		DatagramSocket Server = new DatagramSocket(6677);
		// 2. prepare a container and encapsulate it into a DatagramPacket Packet
		byte[] Container = new byte[1024 * 80]; // 8k
		DatagramPacket Packet = new DatagramPacket(Container, 0, Container.length);
		// 3. receive the packet(DatagramPacket p) with a blocked state
		Server.receive(Packet);
		// 4. analyze the data using byte[]: getData(), getLength()
		byte[] Data = Packet.getData();
		// byte[] -> file
		ByteArrayToFile(Data, "copy.png");
		// 5. release the resource
		Server.close(); // like IO
	}
}
