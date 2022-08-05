package Chapter12;
import java.io.*;
import java.net.*;

/**Basic Process
 * Client _Data_=> Server
 * Send: Wrapping -> Use IO stream to send the data
 * write a method to convert the file to byte[], Notice: the size of the file can't be too big because UDP can't handle it 
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
public class UDPFileClient {
	// File to ByteArray
	public static byte[] FileToByteArray(String Src) throws Exception {
		File F = new File(Src);
		byte[] Res;
		byte[] Flush = new byte[10 * 1024]; // 10K
		int Len = 0;
		if (!F.exists()) {
			throw new RuntimeException("File Not Found");
		}
		InputStream Is = new FileInputStream(F);
		ByteArrayOutputStream Bos = new ByteArrayOutputStream();
		while (Len != -1) {
			Len = Is.read(Flush);
			if (Len == -1) {
				break;
			}
			Bos.write(Flush, 0 , Len);
		}
		Res = Bos.toByteArray();
		Bos.flush();
		Is.close();
		return Res;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("The Sending Port Is Starting Now.....");
		// 1. use DatagramSocket to designate an port and let this port to become to the sending port.
		DatagramSocket Client = new DatagramSocket(8888);
		// 2. prepare the data and convert it into byte[]
		byte[] Data = FileToByteArray("icon.png");
		// 3. encapsulate the byte[] into a DatagramPacket Packet(need a designated destination)
		// convert it into the packet                                                    destination   
		DatagramPacket Packet = new DatagramPacket(Data, 0, Data.length, new InetSocketAddress("localhost", 6677));
		// 4. send the packet(DatagramPacket p)
		Client.send(Packet);
		// 5. release the resource
		Client.close(); // like IO
	}
}
