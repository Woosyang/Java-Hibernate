package Chapter12;
import java.io.*;
import java.net.*;

/**
 * Chat Room: Server(Only One Client Can Send And Receive Multiple Messages)
 * Needs To Be Improved -> SimpleChatMultiServerWithThread.java -> Multiple Clients Can Use 
 * This Chat Room (Only Chat with Themselves) -> Use MultiThread
 * Goal: Fulfill The Function That One Client Can Send And Receive The Information
 * 		 Needs To Be Improved: Multiple Clients Can Send And Receive The Information Without Conflict 
 * @author Woo
 *
 */
public class SimpleChatMultiServer {
	public static void main(String[] args) throws Exception {
		// 1. designate a port by using ServerSocket to create a server
		System.out.println("---Server---");
		ServerSocket Server = new ServerSocket(8888);
		// 2. waiting for the connection of accept
		// while(true) can let multiple clients connect to the server
		// Problem: but they are using the same resource -> other clients have to wait for 
		// the server until the current client disconnect to the server (it is not paralleling chat)
		// Need to use multithread to solve this problem (Multiple Clients Use The Same I/O Stream Resource)
		while (true) {
			Socket Client = Server.accept(); // one client connect to the server
			System.out.println("One Client has been connected.....");
			DataInputStream Dis = new DataInputStream(Client.getInputStream());
			DataOutputStream Dos = new DataOutputStream(Client.getOutputStream());
			boolean IsRunning = true;
			while (IsRunning) {
				// receive
				String Msg = Dis.readUTF();
				// return 
				Dos.writeUTF(Msg);
				Dos.flush();
			}
			Dis.close();
			Dos.close();
			Client.close();
		}
	}
}
