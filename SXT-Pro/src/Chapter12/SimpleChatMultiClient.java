package Chapter12;
import java.io.*;
import java.net.*;

/**
 * Chat Room: Client(One Or Multiple Clients Can Send And Receive Multiple Messages)
 * Needs To Be Improved(Sending And Receiving Information Are Not Mutually Independent)
 * -> SimpleChatMultiClientWithThread.java
 * This Chat Room (Only Chat with Themselves) -> Use MultiThread
 * Goal: Fulfill The Function That One Client Can Send And Receive The Information
 * @author Woo
 *
 */
public class SimpleChatMultiClient {
	public static void main(String[] args) throws Exception {
		// 1. create a connection: use Socket to create a client + the address and port of the Server 
		System.out.println("---Client---");
		Socket Client = new Socket("localhost", 8888);
		BufferedReader Console = new BufferedReader(new InputStreamReader(System.in));
		DataOutputStream Dos = new DataOutputStream(Client.getOutputStream());
		DataInputStream Dis = new DataInputStream(Client.getInputStream());
		boolean isRunning = true;
		// keep receiving and sending the message
		while (isRunning) {
			String Msg = Console.readLine();
			Dos.writeUTF(Msg);
			Dos.flush();
			// get the message
			Msg = Dis.readUTF();
			System.out.println(Msg);
		}
	}
}
