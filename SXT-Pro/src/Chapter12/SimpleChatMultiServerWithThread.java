package Chapter12;
import java.io.*;
import java.net.*;

/**
 * Chat Room: Server(One Or Multiple Clients Can Send And Receive Multiple Messages) 
 * Only Chat with Themselves -> Use MultiThread
 * Need To Be Improved: 1. Too much code in the lambda expression -> not easy to fix them
 * 						2. Send And Receive Messages are not mutually independent
 * 						   From Client's Port(Read after Write)
 * @author Woo
 *
 */
public class SimpleChatMultiServerWithThread {
	public static void main(String[] args) throws Exception {
		// 1. designate a port by using ServerSocket to create a server
		System.out.println("---Server---");
		ServerSocket Server = new ServerSocket(8888);
		// 2. waiting for the connection of accept
		while (true) {
			Socket Client = Server.accept(); // one client connect to the server
			System.out.println("One Client has been connected.....");
			// using lambda expression
			new Thread(() -> {
					// create a constant
					DataInputStream Dis = null; 
					DataOutputStream Dos = null;
					try {
						Dis = new DataInputStream(Client.getInputStream());
						Dos = new DataOutputStream(Client.getOutputStream());
					} catch (IOException e) {
						e.printStackTrace();
					}
					boolean IsRunning = true;
					while (IsRunning) {
						try {
							String Msg = Dis.readUTF();
							Dos.writeUTF(Msg);
							Dos.flush();
						} catch (IOException e) {
							IsRunning = false; // if there is no message to read and receive
							// e.printStackTrace(); // no need to print out the exception message
						}
						
					}
					// close the resource
					try {
						if (Dos != null) {
							Dos.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						if (Dis != null) {
							Dis.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						if (Client != null) {
							Client.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}).start();
		}
	}
}
