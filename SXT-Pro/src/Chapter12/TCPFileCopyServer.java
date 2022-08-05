package Chapter12;
import java.io.*;
import java.net.*;

/**
 * Receiving Port: Client _Data_=> Server
 * get familiar with the basic process
 * create a server
 * 1. designate a port with ServerSocket to create a server
 * 2. wait for the connection of the accept with blocked state
 * 3. action: the operation of IO stream
 * 4. release the resource
 * @author Woo
 *
 */
public class TCPFileCopyServer {
	public static void main(String[] args) throws IOException {
		// 1. designate a port by using ServerSocket to create a server
		System.out.println("---Server---");
		ServerSocket Server = new ServerSocket(8888);
		// 2. waiting for the connection of accept
		Socket Client = Server.accept();
		System.out.println("One Client has been connected.....");
		// 3. action: IO Stream operations
		//                          wrapping        byte stream
		InputStream Is = new BufferedInputStream(Client.getInputStream()); // connect to the output stream of the client
		// create a new file, for Client to send, create a bridge for client and server
		OutputStream Os = new BufferedOutputStream(new FileOutputStream("Kit.jfif"));
		int Len = 0; // receiving length
		byte[] Flush = new byte[1024];
		while (Len != -1) {
			Len = Is.read(Flush);
			if (Len == -1) {
				break;
			}
			Os.write(Flush, 0, Len);
		}
		Os.close();
		Is.close();
		// 4. release the resource
		Server.close();
	}
}
