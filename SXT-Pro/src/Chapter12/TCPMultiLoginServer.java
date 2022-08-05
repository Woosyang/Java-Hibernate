package Chapter12;
import java.io.*;
import java.net.*;

/**
 * Sending Port: Client _Data_=> Server (Multiple Clients Login To A Server)
 * get familiar with the basic process
 * simulate a user login to an account
 * create a client
 * 1. create a connection: use Socket to create a client + the address and port of the Server(Destination)
 * 2. action: the operation of IO stream
 * 3. release the resource
 * @author Woo
 *
 */
public class TCPMultiLoginServer {
	public static void main(String[] args) throws Exception {
		System.out.println("---Server---");
		// 1. create a connection: use Socket to create a client + the address and port
		ServerSocket Server = new ServerSocket(8800);
		boolean IsRunning = true;
		// use multi-thread to create numbers of clients 
		while (IsRunning) {
			// starts to connect
			Socket Client = Server.accept();
			System.out.println("One Client Has Been Connected To The Server....");
			// new Channel(Client) initialize all the fields
			// Client has been assigned, Dis and Dos has been created
			// .start() active the run() of the class
			// run() call the GetRequest() and SendResponse(Msg) with the Dis and Dos
			// which is initialized in the constructor
			new Thread(new Channel(Client)).start();
		}
		Server.close();
	}
	
	// use static class, each class is for each channel
	static class Channel implements Runnable {
		// initialize
		private Socket Client; // inner class
		private DataInputStream Dis;
		private DataOutputStream Dos;
		// constructor 
		private Channel(Socket C) {
			this.Client = C;
			try {
				// Output
				this.Dis = new DataInputStream(C.getInputStream()); // read the byte[]
				// Input, close first
				this.Dos = new DataOutputStream(C.getOutputStream()); // write to the byte[]
			} catch (IOException e) {
				e.printStackTrace();
				Release(); // if there is an exception, close all the sources
			}
		}
		
		// prepare for the run(), receive the data
		public String GetRequest() {
			String Data = null;
			try {
				Data = Dis.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return Data;
		}
		
		// prepare for the run(), send the data
		public void SendResponse(String Msg) {
			try {
				Dos.writeUTF(Msg);
				Dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// close all the source
		public void Release() {
			try { // assume it only use the DataOutPutStream one time
				if (Dos != null) {
					Dos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (Dis != null) { // assume it only use the DataInputStream one time
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
		}
		
	 	@Override // for the start()
		public void run() {
			String UName = "";
			String UPwd = "";
			// analyze the data
			String Receive = GetRequest(); // read the data from the client
			String[] Data = Receive.split("&");
			for (String SubData : Data) {
				String[] Info = SubData.split("=");
				if (Info[0].equals("UName")) {
					UName = Info[1];
					System.out.println("The User Name You Have Typed Is " + Info[1]);
				} else if (Info[0].equals("UPwd")) {
					UPwd = Info[1];
					System.out.println("The Pass Word You Have Typed Is " + Info[1]);
				}
			}
			// verify and send the response
			//    not "=="
			if (UName.equals("Woosty") && UPwd.equals("123456")) {
				SendResponse("Logging Successfully~ Welcome Back!");
			} else {
				SendResponse("The PassWord Or The User Name You Typed Is Not Valid");
			}
			Release();
		}
		
	}
}
