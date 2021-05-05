package Com.Sxt.Core;
import java.io.*;
import java.net.*;

/**
 * Used With SimpleServerWithThread.java To Let Server Keep Process "Request And Response"
 * Needs To Use Request.java And Response.java
 * @author Woo
 *
 */
public class Dispatcher implements Runnable {
	private Socket Client;
	private Request Req;
	private Response Rep;
	public Dispatcher(Socket Client) {
		this.Client = Client;
		try {
			// get the request and response protocol
			Req = new Request(Client);
			Rep = new Response(Client);
		} catch (Exception e) {
			e.printStackTrace();
			Release();
		}
	}
	
	@Override
	public void run() {
		try {
			// if there is no url -> it returns to index.html
			if (Req.getUri().equals("") || null == Req.getUri()) {
				InputStream Is = Thread.currentThread().getContextClassLoader().getResourceAsStream("index.html");
				// "index.html" or "error.html"should beneath 
				// the Package(under the Src folder), otherwise the file can't be found 
				String A = new String(Is.readAllBytes());
				// System.out.println(A);
				// from front-end write to back-end, for postman testing
				Rep.Print(A);
				Rep.PushToBrowser(200);
				Is.close();
				return; // no need to execute the subsequent code
			}
			
			Servlet Sv = WebApp.GetServletFromUrl(Req.getUri());
			if (Sv != null) {
				Sv.Service(Req, Rep);
				Rep.PushToBrowser(200);
			} else { // use error.html
				// error
				// convert the error.html to string and write it to broswer
				InputStream Is = Thread.currentThread().getContextClassLoader().getResourceAsStream("error.html");
				Rep.Print(new String(Is.readAllBytes()));
				Rep.PushToBrowser(404);
				Is.close();
			}
		} catch (Exception e) {
			// since the server is keeping receiving and sending data
			// when Client.getInputStream() becomes null -> Request becomes null -> exception
			e.printStackTrace();
			try {
				Rep.Println("The Server Is Not Working....");
				Rep.PushToBrowser(505);
			} catch (Exception a) {
				a.printStackTrace();
			}
		}
				// right now the current request is still using the thread, needs to be closed for other requests
		this.Release(); // avoid other requests can't get corresponding responses
	}
	
	// release the resouce
	public void Release() {
		try {
			Client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
