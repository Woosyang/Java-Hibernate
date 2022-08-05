package Chapter12;
import java.io.*;
import java.net.*;

/**
 * Chat Room: Client(Only One Client Can Only Send And Receive One Message)
 * Needs To Be Improved -> SimpleChatMultiClient.java 
 * Goal: Fulfill The Function That One Client Can Send And Receive The Information
 * @author Woo
 *
 */
public class SimpleChatClient {
	public static void main(String[] args) throws Exception {
		// 1. create a connection: use Socket to create a client + the address and port of the Server 
		System.out.println("---Client---");
		Socket Client = new Socket("localhost", 8888);
		// 2. client send The message
		BufferedReader Console = new BufferedReader(new InputStreamReader(System.in));
		String Msg = Console.readLine();
		DataOutputStream Dos = new DataOutputStream(Client.getOutputStream());
		Dos.writeUTF(Msg);
		Dos.flush();
		// 3. get the message
		DataInputStream Dis = new DataInputStream(Client.getInputStream());
		Msg = Dis.readUTF();
		System.out.println(Msg);
		// 4. release the resources
		Dos.close();
		Dis.close();
		Client.close();
	}
}
