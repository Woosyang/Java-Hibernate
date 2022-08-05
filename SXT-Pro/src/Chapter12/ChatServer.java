package Chapter12;
import java.io.*;
import java.net.*;
// import java.util.*;
import java.util.concurrent.*;

/**
* Chat Room: Server(One Or Multiple Clients Can Send And Receive Multiple Messages)
* Based On The SimpleChatMultiServerWithThreadPro.java, Encapsulate Sending Message 
* And Receiving Message With MultiThread -> Read And Write Data Can Be Mutually Independent
* Used With ChatClient.java
* Used With ChatResourceRelease.java
* This Chat Room (Can Receive Other's Message And Send Message To Specific Client) 
* -> Use MultiThread
* Goal: Fulfill The Group Chat And Private Chat Function By Using Collections
* @author Woo
*
*/
public class ChatServer {
	// When using MultiThreading
	// List<> is not safe when traverse and revise the List at the same time
	// private List<ServerChannel> All = new ArrayList<>();
	// key factor for fulfilling the group chat
	// CopyOnWriteArrayList does the action to the copy of the list
	private static CopyOnWriteArrayList<ServerChannel> All = new CopyOnWriteArrayList<ServerChannel>();
	public static void main(String[] args) throws Exception {
		// 1. designate a port by using ServerSocket to create a server
		System.out.println("---Server---");
		ServerSocket Server = new ServerSocket(8866);
		// 2. waiting for the connection of accept
		while (true) {
			Socket Client = Server.accept();
			System.out.println("One Client Has Been Connected To The Server....");
			// new Thread(new ServerChannel(Client)).start();
			ServerChannel C = new ServerChannel(Client);
			All.add(C); // store the channel for the client
			new Thread(C).start();
		}
	}
	
	// One Client Stands For One Channel
	static class ServerChannel implements Runnable {
		private DataInputStream Dis;
		private DataOutputStream Dos;
		private Socket Client;
		private boolean IsRunning;
		// receive the name from SendMessage(Msg) in the constructor of SendingChannel
		private String UserName; 
		public ServerChannel(Socket Client) {
			this.Client = Client;
			try {
				this.Dis = new DataInputStream(Client.getInputStream());
				this.Dos = new DataOutputStream(Client.getOutputStream());
				this.IsRunning = true;
				this.UserName = ReceiveMessage(); // get the user name
				// Notification -> show in the client 
				// this.ReceiveMessage() of the ReceivingChannel from the ChatClient
				ForwardMessage("Welcome To This Chat Room~"); 
				// Notification from System to other clients -> show in other clients
				// Others.ReceiveMessage() of the ReceivingChannel from the ChatClient
				ForwardToOthers(this.UserName + " Has Joined In This Chat Room.", true);
			} catch (IOException e) {
				System.out.println("Exception Happens In ServerChannel");
				Release();
			}
		}
		
		// Receiving Message 
		public String ReceiveMessage() {
			String Msg = "";
			try {
				Msg = Dis.readUTF();
			} catch (IOException e) {
				// e.printStackTrace();
				Release();
				System.out.println("Exception Happens In Receive Message From Server");
			}
			return Msg;
		}
		
		// Forwarding Message
		public void ForwardMessage(String Msg) {
			try {
				Dos.writeUTF(Msg);
				Dos.flush();
			} catch (IOException e) {
				System.out.println("Exception Happens In Forward Message From Server");
				Release();
			}
		}
		
		// Group chat
		// private chat -> prescribed format: @xxx: Message
		// (if the client wants to talk to specific client,
		// has to use @xxx: as the format)
		public void ForwardToOthers(String Msg, boolean IsSystem) {
			boolean IsPrivate = Msg.startsWith("@");
			if (IsPrivate) {
				//             first appearance
				int IndexCom = Msg.indexOf(":"); // find the tail of the user name
				String UName = Msg.substring(1, IndexCom); // [,) -> IndexCom is not included, get the targeted user name
				Msg = Msg.substring(IndexCom + 1).trim(); // message entity, omit the ":" and space in the first and last
				for (ServerChannel Others: All) {
					if (UName.equals(Others.UserName)) { // find the targeted user
						// call the ReceiveMessage() from ReceivingChannel of Other Clients
						Others.ForwardMessage(this.UserName + " @" + Others.UserName + ": " + Msg);
						break; // increase the efficiency
					}
				}
			} else { // not private chat
				for (ServerChannel Others : All) {
					if (Others == this) {
						continue; // no need to talk to themselves
					}
					// let other channels call their ForwardMessage()
					// then the ReceiveMessage() of them(other channels) 
					// will get the message from this(current client)
					if (! IsSystem) { // not the message from the system
						Others.ForwardMessage(this.UserName + " @All: " + Msg);
					} else {
						Others.ForwardMessage(Msg); // notification, line 60
					}
				}
			}
		}
		
		public void Release() {
			this.IsRunning = false;
			ChatResourceRelease.Close(Dos, Dis, Client);
			All.remove(this); // quit the chat room, the resources of the current client closed
			ForwardToOthers(this.UserName + " Has Left The Chat Room...", true); // line 102, notification to other clients
		}
		
		@Override
		public void run() {
			while (IsRunning) {
				String Msg = ReceiveMessage();
				if (!Msg.equals("")) {
					// ForwardMessage(Msg); // no need to talk to themselves
					ForwardToOthers(Msg, false); // normal message
				}
				// Release(); // do not close the socket
			}
		}
	}
}
