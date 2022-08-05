package Chapter12;
import java.io.*;
import java.net.*;

/**
 * Sending Port: use OOP idea to do the encapsulation
 * 			     use multi-thread technology to simulate 2 persons talk to each other 
 * based on UDPConversionClient
 * @author Woo
 *
 */
public class UDPSendingPortOfCommunication implements Runnable {
	private int Port;
	private String Des;
	private int DesPort;
	private DatagramSocket Client;
	DatagramPacket Packet; 
	private BufferedReader Br;
	// constructor
	public UDPSendingPortOfCommunication(int Port, String Des, int DesPort) {
		this.Port = Port;
		this.Des = Des;
		this.DesPort = DesPort;
		try {
			Client = new DatagramSocket(this.Port); // create a port for client itself
			Br = new BufferedReader(new InputStreamReader(System.in)); // convert byte stream to char stream
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (true) { 
			String S;
			try {
				S = Br.readLine(); // IO Exception
				byte[] Data = S.getBytes();
				Packet = new DatagramPacket(Data, 0, Data.length, new InetSocketAddress(this.Des, this.DesPort));
				Client.send(Packet);
				if (S.equals("Bye")) {
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Client.close(); // release the resource
	}
}
