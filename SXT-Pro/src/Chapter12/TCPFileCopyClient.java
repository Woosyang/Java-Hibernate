package Chapter12;
import java.io.*;
import java.net.*;

/**
 * Sending Port: Client _Data_=> Server
 * get familiar with the basic process
 * create a client
 * 1. create a connection: use Socket to create a client + the address and port of the Server(Destination)
 * 2. action: the operation of IO stream
 * 3. release the resource
 * @author Woo
 *
 */
public class TCPFileCopyClient {
	public static void main(String[] args) throws Exception {
		// 1. create a connection: use Socket to create a client + the address and port of the Server 
		System.out.println("---Client---");
		Socket Client = new Socket("localhost", 8888);
		// 2. action: the operation of IO stream
		// Copy the file to the Server
		InputStream Is = new BufferedInputStream(new FileInputStream("Cat.jfif")); // read the file first
		// for Server to receive -> the bridge between Client and Server
		OutputStream Os = new BufferedOutputStream(Client.getOutputStream()); // write the data into byte[]
		int Len = 0; // receiving Length
		byte[] Flush = new byte[1024]; // unit size
		while (Len != -1) {
			Len = Is.read(Flush);
			if (Len == -1) {
				break;
			}
			Os.write(Flush, 0, Len);
		}
		Is.close();
		Os.close();
		// 3. release the resource
		Client.close();
	}	 
}
