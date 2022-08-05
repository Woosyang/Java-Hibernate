package Chapter12;
import java.io.*;
import java.net.*;

/**
 * Receiving Port: use OOP idea to do the encapsulation
 * 			       use multi-thread technology to simulate 2 persons talk to each other 
 * based on UDPConversionServcer
 * @author Woo
 *
 */
public class UDPReceivingPortOfCommunication implements Runnable {
	private DatagramSocket Server;
	private DatagramPacket Packet;
	private String Name;
	public UDPReceivingPortOfCommunication(int Port, String Name) {
		try {
			this.Name = Name;
			this.Server = new DatagramSocket(Port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (true) {
			byte[] Container = new byte[80 * 1024]; // 80K
			Packet = new DatagramPacket(Container, 0, Container.length);
			try {
				Server.receive(Packet);
				byte[] Data = Packet.getData();
				// Packet's length is keeping growing, use getLength() can output all of the message (including older one)
				int Len = Packet.getLength(); // not Data.length! -> it will only output one line for the current time 
				String S = new String(Data, 0, Len);
				System.out.println(Name + ": " + S);
				if (S.equals("Bye")) {
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Server.close();
	}
}
