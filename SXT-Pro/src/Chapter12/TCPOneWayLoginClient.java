package Chapter12;
import java.io.*;
import java.net.*;
/**
 * Sending Port: Client _Data_=> Server (One Direction)
 * get familiar with the basic process
 * simulate a user login to an account
 * create a client
 * 1. create a connection: use Socket to create a client + the address and port of the Server(Destination)
 * 2. action: the operation of IO stream
 * 3. release the resource
 * @author Woo
 *
 */
public class TCPOneWayLoginClient {
	public static void main(String[] args) throws Exception {
		// 1. create a connection: use Socket to create a client + the address and port of the Server 
		System.out.println("---Client---");
		Socket Client = new Socket("localhost", 8888);
		// console fetch the information from the account -> using BufferedReader
		BufferedReader Console = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Please Input Your Account Name: ");
		String UserName = Console.readLine();
		System.out.print("Please Input Your PassWord: ");
		String UserPassWord = Console.readLine();
		// 2. action: the operation of IO stream
		// use DataOutputStream -> send
		//                         wrapping                     byte Stream
		DataOutputStream Dos = new DataOutputStream(Client.getOutputStream());
		// String S = "Hello";
		Dos.writeUTF("UName=" + UserName + "&UPwd=" + UserPassWord);
		Dos.flush();
		// 3. release the resource
		Dos.close();
		Client.close();
	}	 
}
