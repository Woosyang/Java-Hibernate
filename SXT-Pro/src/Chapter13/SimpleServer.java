package Chapter13;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Used With login.html
 * Goal: 1. Use SeverSocket To Build The Connection With Browser, Get The Request Protocol
 * 		 2. Return Response Protocol
 * @author Woo
 *
 */
public class SimpleServer {
	private ServerSocket Sc;
	public static void main(String[] args) {
		SimpleServer S = new SimpleServer();
		S.StartServer();
	}
	
	// active the web server
	public void StartServer() {
		try {
			Sc = new ServerSocket(8880);
			ReceiveData();
		} catch (IOException e) {
			System.out.println("There Is Something Wrong With The Server");
		}
	}
	
	// accept the connection
	// Post: request parameters is after the request body
	// Get: request parameters is behind the URL
	public void ReceiveData() {
		try {
			Socket Client = Sc.accept();
			System.out.println("One Client Has Been Connected To The Server...");
			
			// request -> Request.java
			// get the request protocol
			InputStream Is = Client.getInputStream();
			byte[] Datas = new byte[1024 * 1024]; // receiving container
			int Len = Is.read(Datas); // receiving length
			String RequestContent = new String(Datas, 0, Len);
			System.out.println(RequestContent);
			
			// response -> Response.java 
			StringBuilder Content = new StringBuilder();
			Content.append("<html>");
			Content.append("<head>");
			Content.append("<title>");
			Content.append("Server Is Responsing~");
			Content.append("</title>");
			Content.append("</head>");
			Content.append("<body>");
			Content.append("Welcome.....");
			Content.append("</body>");
			Content.append("</html>");
			int Size = Content.toString().getBytes().length; // the length of the content 
			// make up the ResponseConent
			StringBuilder ResponseContent = new StringBuilder();
			String Space = " ";
			String Crlf = "\r\n";
			// return the response protocol
			// 1. status(response)-line: HTTP/1.1 200 OK -> Protocol/Version Status Code Description
			ResponseContent.append("HTTP/1.1").append(Space).append(200).append(Space).append("Ok");
			ResponseContent.append(Crlf);
			
			// 2. response header: (last line should be a space line)
			/* eg
			 Date: Mon, 27 Jul 2009 12:28:53 GMT
			 Server: Apache/2.2.14; charset=GBK
	  		 Content-Type: text/html
	  		 Content-Length: 88
			*/
			// no space after ":" for "Date", "Server", "Content-Type", "Content-Length"!
			ResponseContent.append("Date:").append(new Date()).append(Crlf)
						   .append("Server:").append("Sxt Server/0.0.1;charset=UTF-8").append(Crlf)
						   .append("Content-Type:text/html").append(Crlf)
						   .append("Content-Length:").append(Size).append(Crlf)
						   .append(Crlf); // last line -> space line
			
			// 3. Message-Body
			ResponseContent.append(Content.toString());
			
			// write to the client
			BufferedWriter Bw = new BufferedWriter(new OutputStreamWriter(Client.getOutputStream()));
			Bw.write(ResponseContent.toString());
			Bw.flush();
		} catch (IOException e) {
			System.out.println("There Is Something Wrong With The Client");
		}
	}
}
