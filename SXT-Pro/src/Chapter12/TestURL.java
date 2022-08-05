package Chapter12;
import java.net.*;

/**
 * URL: uniform resource locator
 * 1. protocol
 * 2. domain name, computer
 * 3. port number: default format 80
 * 4. resource for requesting 
 * eg: http://www.baidu.com:80/index.html?uname=woo&id=1024#a
 * #a: anchor
 * @author Woo
 *	
 */
public class TestURL {
	public static void main(String[] args) throws MalformedURLException {
		URL U = new URL("http://www.baidu.com:80/index.html?uname=woo&id=1024#a");
		// get the 4 values 
		System.out.println("Protocol is: " + U.getProtocol());
		System.out.println("Domain Name / IP: " + U.getHost());
		// completed format, anchor is not included
		System.out.println("Requested Resource01: " + U.getFile()); 
		System.out.println("Requested Resource02: " + U.getPath()); // URI
		System.out.println("Port Number: " + U.getPort());
		// get the parameters
		System.out.println("Parameters: " + U.getQuery());
		// get the anchor
		System.out.println("Anchor: " + U.getRef());
	}
}
