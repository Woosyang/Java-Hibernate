package Chapter12;
import java.net.*;

/**
 * how to find the port: 
 * -aon|findstr "" -> cmd
 *	tasklist|findstr "" -> cmd
 * Port: range: 0 -> 65535 eg: 8080 -> TomCat, 1521 -> Oracle, 3306 -> MySql 
 * 1. distinguish the software
 * 2. 2 bytes: 0 -> 65535 UDP TCP
 * 3. number of the port can't be same when using the same protocol
 * 4. use the number as great as possible to define the port
 * InetSocketAddress: 
 * constructor: new InetSocketAddress(DNS, port number);
 * method: 
 * 1. getHostName()
 * 2. getAddress()
 * 3. getPort()
 * @author Woo
 *
 */
public class TestPort {
	public static void main(String[] args) {
		//                                                           DNS   port number
		InetSocketAddress SocketAddress01 = new InetSocketAddress("127.0.0.1", 8080);
		InetSocketAddress SocketAddress02 = new InetSocketAddress("localhost", 9000);
		System.out.println(SocketAddress01.getHostName());
		System.out.println(SocketAddress01.getAddress()); // 127.0.0.1 / 127.0.0.1
		System.out.println(SocketAddress02.getAddress()); // local host / 127.0.0.1 
		System.out.println(SocketAddress02.getPort()); // 9000
	}
}
