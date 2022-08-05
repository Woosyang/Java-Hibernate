package Chapter12;
import java.io.*;
import java.net.*;

/**
 * Chat Room: Client(One Or Multiple Clients Can Send And Receive Multiple Messages)
 * Based On The SimpleChatMultiClientWithThread.java, Encapsulate Sending Message 
 * And Receiving Message With MultiThread -> Read And Write Data Can Be Mutually Independent
 * Used With ChatServer.java
 * Used With ChatResourceRelease.java
 * This Chat Room (Can Receive Other's Message And Send Message To Specific Client) 
 * -> Use MultiThread
 * Goal: Fulfill The Group Chat Function By Using Collections
 * @author Woo
 *
 */
public class ChatClient {
	public static void main(String[] args) throws Exception {
		// 1. create a connection: use Socket to create a client + the address and port of the Server 
		System.out.println("---Client---");
		// get the User Name
		String UserName;
		BufferedReader Br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please Input Your User Name: "); // assume there is no naming conflict
		UserName = Br.readLine();
		Socket Client = new Socket("localhost", 8866);
		new Thread(new SendingChannel(Client, UserName)).start();
		new Thread(new ReceivingChannel(Client)).start();
	}
	
	// Sending Port
	static class SendingChannel implements Runnable {
		private DataOutputStream Dos;
		private boolean IsRunning;
		private Socket Client;
		private BufferedReader Console;
		private String UserName;
		// constructor 
		public SendingChannel(Socket Client, String Name) {
			this.Client = Client;
			this.UserName = Name;
			this.Console = new BufferedReader(new InputStreamReader(System.in));
			try {
				IsRunning = true;
				this.Dos = new DataOutputStream(Client.getOutputStream());
				SendMessage(Name); // send the user name of the client to the server when initializing
			} catch (IOException e) {
				System.out.println("Exception Happens In SendingChannel");
				Release();
			}
		}
		
		// get the message from console
		public String GetStringFromConsole() {
			String Msg = "";
			try {
				Msg = Console.readLine();
			} catch (IOException e) {
				System.out.println("Exception Happens In Get String From Console Of SendingChannel");
				Release();
			}
			return Msg;
		}
		
		// send the message to server
		public void SendMessage(String Msg) {
			try {
				Dos.writeUTF(Msg);
				Dos.flush();
			} catch (IOException e) {
				System.out.println("Exception Happens In Send Message From SendingChannel");
				Release();
			}
		}
		
		// release 
		public void Release() {
			// this.IsRunning = false;
			ChatResourceRelease.Close(Dos, Console, Client);
		}
		
		@Override
		public void run() {
			while (IsRunning) {
				String Msg = GetStringFromConsole();
				if (!Msg.equals("")) {
					SendMessage(Msg);
				}
			}
		}
	}
	
	// Receiving Port
	static class ReceivingChannel implements Runnable {
		private DataInputStream Dis;
		private boolean IsRunning;
		private Socket Client;
		
		// constructor
		public ReceivingChannel(Socket Client) {
			this.Client = Client;
			try {
				IsRunning = true; // only if there is no exception
				this.Dis = new DataInputStream(Client.getInputStream());
			} catch (IOException e) {
				System.out.println("Exception Happens In ReceivingChannel");
				Release();
			}
		}
		
		// Receive 
		public String ReceiveMessage() {
			String Msg = "";
			try {
				Msg = Dis.readUTF();
			} catch (IOException e) {
				System.out.println("Exception Happens In Receive Message From ReceivingChannel");
				Release();
			}
			return Msg;
		}
		
		// release resource 
		public void Release() {
			// IsRunning = false; // add this line will make the client send or receive only one message
			ChatResourceRelease.Close(Dis, Client);
		}
		
		@Override
		public void run() {
			while (IsRunning) {
				String Msg = ReceiveMessage();
				if (!Msg.equals("")) {
					System.out.println(Msg);
				}
			}	
		}
	}
}
