package Com.Sxt.Core;
import java.net.*;
import java.util.Date;
import java.io.*;

/**
 * Goal 1 For SimpleServerPro 
 * Encapsulate The Response Part Of The ReceiveData() in SimpleServer.java
 * Write The Html File As The Response To Broswer
 * Helper Class For SimpleServerPro.java
 * @author Woo
 *
 */
public class Response {
	private BufferedWriter Bw; // write to the broswer
	// content, can be dynamically added in SimpleServerPro.java
	private StringBuilder Content;
	// protocol header (status line + reponse header + Crlf)
	private StringBuilder HeadInfo;
	private int Len; // Content-Length
	private final String SPACE = " ";
	private final String CRLF = "\r\n";
	// constructor
	private Response() {
		// initialize
		Content = new StringBuilder();
		HeadInfo = new StringBuilder();
		Len = 0;
	}
	
	public Response(Socket Client) {
		this(); // overload the Response()
		try {
			Bw = new BufferedWriter(new OutputStreamWriter(Client.getOutputStream()));
		} catch (IOException e) {
			HeadInfo = null;
			e.printStackTrace();
		}
	}
	
	public Response(OutputStream Os) {
		this();
		Bw = new BufferedWriter(new OutputStreamWriter(Os));
	}
	
	// add the content dynamically, used in SimpleServerPro.java
	public Response Print(String Info) {
		Content.append(Info);
		Len += Info.getBytes().length;
		return this;
	}
	
	// add the content dynamically, used in SimpleServer.java
	public Response Println(String Info) {
		Content.append(Info).append(CRLF);
		Len += (Info + CRLF).getBytes().length; // CRLF also has its length
		return this;
	}
	
	// build up response header information
	private void GenerateHeadInfo(int StatusCode) {
		// 1. joint the response line -> Protocol/Version Status Code Description
		HeadInfo.append("HTTP/1.1").append(SPACE).append(StatusCode).append(SPACE);
		// the stauts description varys according to the status code -> use switch
		switch (StatusCode) {
			case 200:
				HeadInfo.append("Ok").append(CRLF);
				break;
			case 404:
				HeadInfo.append("Not Found").append(CRLF);
				break;
			case 505:
				HeadInfo.append("Server Error").append(CRLF);
				break;
		}
		
		// 2. response header, the last line should be a space line
		HeadInfo.append("Date:").append(new Date()).append(CRLF)
		   		.append("Server:").append("Sxt Server/0.0.1;charset=UTF-8").append(CRLF)
		   		.append("Content-Type:text/html").append(CRLF)
		   		.append("Content-Length:").append(Len).append(CRLF)
		   		.append(CRLF); // last line -> space line
	}
	
	// forward the response information to broswer
	public void PushToBrowser(int StatusCode) throws Exception {
		if (HeadInfo == null) {
			StatusCode = 505; // something wrong with response of the server
		}
		GenerateHeadInfo(StatusCode);
		// no need to use Bw.write()
		Bw.append(HeadInfo);
		Bw.append(Content);
		Bw.flush();
	}
}
