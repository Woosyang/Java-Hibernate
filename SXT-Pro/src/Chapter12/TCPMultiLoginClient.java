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
public class TCPMultiLoginClient {
	public static void main(String[] args) throws Exception {
		// 1. create a connection: use Socket to create a client + the address and port of the Server 
		System.out.println("---Client---");
		// Port Number must be matched with the Server Port Number
		// Port Number must not be used before, should not be conflicted with other TCP program
		Socket Client = new Socket("localhost", 8800);
		// console fetch the information from the account -> using BufferedReader
		/*
		BufferedReader Console = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please Input Your Account Name: ");
		String UserName = Console.readLine();
		System.out.print("Please Input Your PassWord: ");
		String UserPassWord = Console.readLine();
		*/
		Channel C = new Channel(Client);
		
		// 2. action: the operation of IO stream
		// Request Before Response
		C.SendRequest();
		// use DataOutputStream -> send
		//	                        wrapping                     byte Stream
		/*
		DataOutputStream Dos = new DataOutputStream(Client.getOutputStream());
		// String S = "Hello";
		Dos.writeUTF("UName=" + UserName + "&UPwd=" + UserPassWord);
		Dos.flush();
		*/
		// receive the result from the server regarding to the user's logging status
		
		// get the response from the server, Socket Client = Server.accept() in the Server class
		C.ShowResponse();
		/*
		DataInputStream Dis = new DataInputStream(Client.getInputStream()); // read
		String Res = Dis.readUTF();
		System.out.println(Res);
		*/
		
		// 3. release the resource
		C.Release();
		/*
		Dos.close();
		Dis.close();
		Client.close();
		*/
	}
	
	static class Channel {
		private Socket Client;
		private DataInputStream Dis;
		private DataOutputStream Dos;
		private BufferedReader Console;
		public String UserName = "";
		public String UserPassWord = "";
		public Channel(Socket Client) throws Exception {
			this.Client = Client;
			/*
			 * // InputStreamReader -> convert the byte stream to char stream 
			 * this.Console = new BufferedReader(new InputStreamReader(System.in));
			 */
			Init();
			this.Dis = new DataInputStream(Client.getInputStream());
			this.Dos = new DataOutputStream(Client.getOutputStream());
		}
		
		// prepared for the input
		public void Init() throws IOException {
			this.Console = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Please Input Your Account Name: ");
			UserName = Console.readLine();
			System.out.print("Please Input Your PassWord: ");
			UserPassWord = Console.readLine();
		}
		
		// send
		public void SendRequest() throws IOException {
			Dos.writeUTF("UName=" + UserName + "&UPwd=" + UserPassWord);
			Dos.flush();
		}
		
		// receive
		public void ShowResponse() throws IOException {
			String Res = Dis.readUTF();
			System.out.println(Res);
		}
		
		public void Release() throws Exception {
			if (Dis != null) {
				Dis.close();
			}
			if (Dos != null) {
				Dos.close();
			}
			if (Client != null) {
				Client.close();
			}
		}
	}
}
