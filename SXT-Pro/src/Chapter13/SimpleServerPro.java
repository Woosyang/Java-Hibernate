package Chapter13;
import java.io.*;
import java.net.*;

/**
 * Used With Response.java And Request.java
 * Based On SimpleServer
 * Goal: 1. Encapsulate the Response -> Content Can Be Added Dynamically; Focus On Status Code
 * 			To Joint The Response Protocol Information 
 *		 2. Encapsulate the Request -> Get The Method Name, URI And Requested Parameters
 * 	     3. Import The Servlet Interface -> Reduce The Coupling(Omit The Contents In Line 50 - 60 In SimpleServerPro.java)
 * 		 4. Integrate The Server With Configuration File
 * @author Woo
 *
 */
public class SimpleServerPro {
	private ServerSocket Sc;
	
	public static void main(String[] args) {
		SimpleServerPro Sp = new SimpleServerPro();
		Sp.StartServer();
	}
	
	public void StartServer() {
		try {
			Sc = new ServerSocket(8899);
			ReceiveData();
		} catch (IOException e) {
			System.out.println("There Is Something Wrong With The Server");
		}	
	}
	
	// accept the connection
	// Post: request parameters is after the request body
	// Get: request parameters is behind the URL
	public void ReceiveData() {
		Socket Client;
		Servlet Sv = null;
		try {
			Client = Sc.accept();
			System.out.println("One Client Has Been Connected To The Server...");

			// get the request protocol
			Request Req = new Request(Client);
			
			// add the content dynamically with Response.java
			Response Rep = new Response(Client);
			// replace "Content.append()" with "Res.Print()"
			// the content-length is already figured out in Print()/Println() 
			
			// I, Original Way
			// only focus on the content, can be dynamically changed
			/*
			Rep.Print("<html>");
			Rep.Print("<head>");
			Rep.Print("<title>");
			//                                  Request.java, show the value in Postman
			Rep.Print("Server Is Responsing~" + Req.GetParameterValue("uname")); // sxt
			Rep.Print("</title>");
			Rep.Print("</head>");
			Rep.Print("<body>");
			Rep.Print("Welcome.....");
			Rep.Print("</body>");
			Rep.Print("</html>");
			
			// only focus on the status code
			Rep.PushToBrowser(200);
			*/
			
			/* II, original way improved with if-else statement with Servlet
			// replace the code above by using Servlet.Service(Req, Rep) -> reduce coupling
			// in the Request.java, the "/" has been already filtered out
			if (Req.getUri().equals("login")) { // "/login"
				Sv = new LoginServlet();
			} else if (Req.getUri().equals("reg")) { // "/reg"
				Sv = new RegisterServlet();
			} else {
				// index.html
			}
			Sv.Service(Req, Rep);
			// only focus on the status code
			Rep.PushToBrowser(200);
			*/
			
			// III optimize the code above by using WebApp.java
			Sv = WebApp.GetServletFromUrl(Req.getUri()); // no need to new WebApp cuz it uses static block
			if (Sv != null) {
				Sv.Service(Req, Rep);
				// only focus on the status code
				Rep.PushToBrowser(200);
			} else {
				// error
				Rep.PushToBrowser(404);
			}
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("There Is Something Wrong With The Client");
		}
	}
}
