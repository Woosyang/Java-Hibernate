package Com.Sxt.Core;
import java.io.IOException;
import java.net.*;

/**
 * Implement the MultiThreading Technology, Used With Dispatcher.java
 * Based On SimpleServerPro.java
 * Goal: Generate The Web Pages For 404, 505 (In Dispatcher.java)
 * @author Woo
 *
 */
public class Server {
	private ServerSocket Sc;
	private boolean IsRunning;
	
	// active the server
	public void StartServer() {
		IsRunning = true;
		try {
			Sc = new ServerSocket(8800);
			ReceiveData();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	// accept the connection, do the "request and response"
	public void ReceiveData() {
		while (IsRunning) {
			// Socket Client;
			// Servlet Sv = null;
			try {
				Socket Client = Sc.accept();
				System.out.println("One Client Has Been Connected To The Server...");
				// use MultiThreading Tech
			
				/*
				// get the request protocol
				Request Req = new Request(Client);
				// add the content dynamically with Response.java
				Response Rep = new Response(Client);
				Sv = WebApp.GetServletFromUrl(Req.getUri());
				if (Sv != null) {
					Sv.Service(Req, Rep);
					Rep.PushToBrowser(200);
				} else {
					Rep.PushToBrowser(404);
				}
				*/
				// replace the code above by using Dispatcher.java
				new Thread(new Dispatcher(Client)).start();
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println("There Is Something Wrong With The Client");
			}
		}
	}
	
	public void StopServer() {
		IsRunning = false;
		try {
			this.Sc.close();
			System.out.println("Server Is Stopped..");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Server Sp = new Server();
		Sp.StartServer();
		Sp.StopServer();
	}
}
