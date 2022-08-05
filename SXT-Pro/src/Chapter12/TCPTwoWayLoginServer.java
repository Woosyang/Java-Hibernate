package Chapter12;
import java.io.*;
import java.net.*;

/**
 * Receiving Port: Client _Data_=> Server (Bi-Direction)
 * get familiar with the basic process
 * create a server
 * 1. designate a port with ServerSocket to create a server
 * 2. wait for the connection of the accept with blocked state
 * 3. action: the operation of IO stream
 * 4. release the resource
 * @author Woo
 *
 */
public class TCPTwoWayLoginServer {
	public static void main(String[] args) throws IOException {
		// 1. designate a port by using ServerSocket to create a server
		System.out.println("---Server---");
		ServerSocket Server = new ServerSocket(8888);
		// 2. waiting for the connection of accept
		Socket Client = Server.accept();
		System.out.println("One Client has been connected.....");
		// 3. action: IO Stream operations
		//                          wrapping        byte stream
		DataInputStream Dis = new DataInputStream(Client.getInputStream());
		String S = Dis.readUTF();
		String Uname = ""; // for the response 
		String Pwd = ""; // for the response
		// analyze the elements in the String S
		String[] Data = S.split("&"); // cut the String into 2 parts according to '&'
		// '&' not included
		for (String Info : Data) {
			String SubData[] = Info.split("="); // cut the Sub String into 2 parts according to '='
			// '=' is not included
			if (SubData[0].equals("UName")) {
				System.out.println("The User Name You have Typed is: " + SubData[1]);
				Uname = SubData[1];
			} else if (SubData[0].equals("UPwd")) {
				System.out.println("The Pass Word You have Typed is: " + SubData[1]);
				Pwd = SubData[1];
			}	
		}
		// verify the information of the user
		// the user name and password has already been set up(Woosty, 123456)
		// send the data to the Client according to "Socket Client = Server.accept();"
		// response
		DataOutputStream Dos = new DataOutputStream(Client.getOutputStream());
		if (Uname.equals("Woosty") && Pwd.equals("123456")) {
			// login successfully -> Output -> Output Stream -> Client.getOutputStream()
			Dos.writeUTF("Login Successfully~ Welcome Back!"); // output in the client
		} else {
			Dos.writeUTF("The UserName Or The PassWord Is Not Valid"); // output in the client
		}
		// 4. release the resource
		Dis.close();
		Dos.close();
		Server.close();
	}
}
