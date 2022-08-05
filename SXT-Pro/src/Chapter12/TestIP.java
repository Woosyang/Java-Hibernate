package Chapter12;
import java.net.*;

/**
 * IP: locate a node(Computer, Router...)
 * InetAddress: got multiple static method inside
 * 1. getLocalHost(): local PC
 * 2. getByName(): return the IP according to the domain name
 * member method:
 * getHostAddress(): get the IP of the host
 * getHostName(): get the name of PC
 * @author Woo
 */
public class TestIP {
	public static void main(String[] args) throws UnknownHostException {
		// use getLocalHost() to create an InetAddress object
		InetAddress Addr = InetAddress.getLocalHost(); // local
		System.out.println(Addr.getHostAddress()); // 192.168.1.110
		System.out.println(Addr.getHostName()); // PC Name
		
		// get the InetAddress according to domain name
		Addr = InetAddress.getByName("www.shsxt.com");
		System.out.println(Addr.getHostAddress()); // return the IP of the web site
		System.out.println(Addr.getHostName()); // www.shsxt.com
		
		// get the InetAddress according to IP, if the IP is illegal, it won't get the result
		Addr = InetAddress.getByName("123.56.138.186");
		System.out.println(Addr.getHostAddress()); // return the IP of the web site
		System.out.println(Addr.getHostName()); // output the IP not the domain name
		// DNS server doesn't allow the mapping of IP to domain name
	}
}
