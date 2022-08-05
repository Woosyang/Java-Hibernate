package Chapter12;
import java.io.*;
import java.net.*;

/**
 * Chat Room: Server(One Or Multiple Clients Can Send And Receive Multiple Messages) 
 * Only Chat with Themselves -> Use MultiThread With Encapsulation
 * @author Woo
 *
 */
public class SimpleChatMultiServerWithThreadPro {
	public static void main(String[] args) throws Exception {
		// 1. designate a port by using ServerSocket to create a server
		System.out.println("---Server---");
		ServerSocket Server = new ServerSocket(8888);
		// 2. waiting for the connection of accept
		while (true) {
			Socket Client = Server.accept();
			System.out.println("One Client Has Been Connected To The Server...");
			new Thread(new ServerChannel(Client)).start();
		}
	}
	
	// One Client Stands For One Channel
	static class ServerChannel implements Runnable {
		private DataInputStream Dis;
		private DataOutputStream Dos;
		private Socket Client;
		private boolean IsRunning;
		
		public ServerChannel(Socket Client) {
			this.Client = Client;
			try {
				Dis = new DataInputStream(Client.getInputStream());
				Dos = new DataOutputStream(Client.getOutputStream());
				IsRunning = true; // if there is no exception, assign true to it
			} catch (IOException e) {
				System.out.println("---Exception For The Server Channel---");
				Release();
			}
		}
		
		@Override
		public void run() {
			while (IsRunning) {
				String Msg = Receive();
				if (!Msg.equals("")) {
					Send(Msg);
				}
			}	
		}
		
		// sending the message 
		public void Send(String Msg) {
			try {
				Dos.writeUTF(Msg);
				Dos.flush();
			} catch (IOException e) {
				System.out.println("---Exception For Sending---");
				Release();
			}
		}
		
		// receiving the message
		public String Receive() {
			String Msg = "";
			try {
				Msg = Dis.readUTF();
			} catch (IOException e) {
				// System.out.println("---Exception For Receiving---");
				// System.out.println("---No Message Left To Read For Dis---");
				// e.printStackTrace();
				// no message to read
				Release();
			}
			return Msg;
		}
		
		// releasing the resource
		public void Release() {
			this.IsRunning = false;
			ChatResourceRelease.Close(Dis, Dos, Client);
		}
	}
}

