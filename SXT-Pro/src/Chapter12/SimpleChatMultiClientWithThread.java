package Chapter12;
import java.io.*;
import java.net.*;

/**
 * Chat Room: Client(One Or Multiple Clients Can Send And Receive Multiple Messages)
 * Based On The SimpleChatMultiClient.java, Encapsulate Sending Message And Receiving Message
 * With MultiThread -> Read And Write Data Can Be Mutually Independent
 * Used With SimpleChatMultiServerWithThreadPro.java
 * This Chat Room (Only Chat with Themselves) -> Use MultiThread
 * Goal: Fulfill The Function That One Client Can Send And Receive The Information
 * @author Woo
 *
 */
public class SimpleChatMultiClientWithThread {
	public static void main(String[] args) throws Exception {
		// 1. create a connection: use Socket to create a client + the address and port of the Server 
		System.out.println("---Client---");
		Socket Client = new Socket("localhost", 8888);
		new Thread(new SendingChannel(Client)).start();
		new Thread(new ReceivingChannel(Client)).start();
		// Client.close();
	}
	
	// Sending Port
	/*
	 * 1. Get Message From Console
	 * 2. Send Message
	 * 3. Release Message
	 * 4. Override run() 
	 */
	static class SendingChannel implements Runnable {
		private BufferedReader Console;
		private DataOutputStream Dos;
		private Socket Client;
		private boolean IsRunning;
		// constructor
		public SendingChannel(Socket Client) {
			this.Client = Client;
			Console = new BufferedReader(new InputStreamReader(System.in));
			try {
				IsRunning = true;
				Dos = new DataOutputStream(Client.getOutputStream());
			} catch (IOException e) {
				System.out.println("Exception For Sending Channel");
				Release();
			}
		}
		
		@Override
		public void run() {
			while (IsRunning) {
				String Msg = GetStringFromConsole();
				// send
				if (!Msg.equals("")) {
					Send(Msg);
				}
			}
		}		
		
		// send message
		public void Send(String Msg) {
			try {
				Dos.writeUTF(Msg);
				Dos.flush();
			} catch (IOException e) {
				System.out.println("Exception For Sending Message From Sending Channel");
				Release();
			}
		}
		
		// get message
		public String GetStringFromConsole() {
			String Msg = "";
			try {
				Msg = Console.readLine();
			} catch (IOException e) {
				System.out.println("Exception For Reading From Console");
				// e.printStackTrace();
				Release();
			}
			return Msg;
		}
		
		// close the resource
		public void Release() {
			ChatResourceRelease.Close(Dos, Client, Console);
		}
	}
	
	// Receiving Port -> Read Data From The Server
	/*
	 * 1. Receive message 
	 * 2. Release resource
	 * 3. Override run()
	 */
	static class ReceivingChannel implements Runnable {
		private DataInputStream Dis;
		private Socket Client;
		private boolean IsRunning;
		// constructor
		public ReceivingChannel(Socket Client) {
			this.Client = Client;
			try {
				Dis = new DataInputStream(Client.getInputStream());
				IsRunning = true;
			} catch (IOException e) {
				System.out.println("Exception For Receiving Channel");
				Release();
			}
		}
		
		@Override
		public void run() {
			while (IsRunning) {
				String Msg = Receive();
				if (!Msg.equals("")) {
					System.out.println(Msg);
				}
			}
		}
		
		// receive the message from Server
		public String Receive() {
			String Msg = "";
			try {
				Msg = Dis.readUTF();
			} catch (IOException e) {
				// System.out.println("Exception For Receiving Message From ReceivingChannel");
				// no message to read
				Release();
			}
			return Msg;
		}
		
		// close the resource
		public void Release() {
			ChatResourceRelease.Close(Dis, Client);
		}
	}
}
