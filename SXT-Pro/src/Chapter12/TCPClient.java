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
public class TCPClient {
	public static void main(String[] args) throws Exception {
		// 1. create a connection: use Socket to create a client + the address and port of the Server 
		System.out.println("---Client---");
		Socket Client = new Socket("localhost", 8888);
		// 2. action: the operation of IO stream
		// use DataOutputStream -> send
		//                         wrapping                     byte Stream
		DataOutputStream Dos = new DataOutputStream(Client.getOutputStream());
		String S = "Hello";
		Dos.writeUTF(S);
		Dos.flush();
		// 3. release the resource
		Dos.close();
		Client.close();
	}	 
}
