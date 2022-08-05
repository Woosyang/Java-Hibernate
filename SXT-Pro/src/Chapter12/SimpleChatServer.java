package Chapter12;
import java.io.*;
import java.net.*;

/**
 * Chat Room: Server
 * Goal: Fulfill The Function That One Client Can Send And Receive The Information
 * @author Woo
 *
 */
public class SimpleChatServer {
	public static void main(String[] args) throws IOException {
		// 1. designate a port by using ServerSocket to create a server
		System.out.println("---Server---");
		ServerSocket Server = new ServerSocket(8888);
		// 2. waiting for the connection of accept
		Socket Client = Server.accept();
		System.out.println("One Client has been connected.....");
		// 3. receive the message
		DataInputStream Dis = new DataInputStream(Client.getInputStream());
		String Msg = Dis.readUTF();
		// 4. return the message to the client
		DataOutputStream Dos = new DataOutputStream(Client.getOutputStream());
		Dos.writeUTF(Msg);
		Dos.close();
		Dis.close();		
	}
}
