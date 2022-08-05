package Chapter10;
import java.io.*;

/**
 * DataInputStream & DataOutputStream
 * can save the type of the data, no need to do the casting
 * 1. write out first then read in
 * 2. the order of reading out should be same as the order of writing in
 * @author Woo
 *
 */
public class TestDataInputStreamAndDataOutputStream {
	public static void main(String[] args) throws IOException {
		byte Data[] = null;
		// use ByteArrayOutputStream and ByteArrayInputStream
		// write
		// no need to close
		ByteArrayOutputStream Bos = new ByteArrayOutputStream();
		//            the parameter inside should be node stream
		DataOutputStream Dos = new DataOutputStream(Bos);
		Dos.writeInt(26);
		Dos.writeUTF("Wu");
		Dos.writeBoolean(true);
		Dos.writeChar('A');
		Dos.flush();
		// any file can be converted into a byte[]
		Data = Bos.toByteArray(); // the data wrote in toDos become to the byte[] by Bos
		System.out.println(Data.length); // file's size
		// read
		ByteArrayInputStream Bis = new ByteArrayInputStream(Data);
		DataInputStream Dis = new DataInputStream(Bis);
		// the order of reading out should be matched as writing in
		int a = Dis.readInt();
		String b = Dis.readUTF();
		boolean c = Dis.readBoolean();
		char d = Dis.readChar();
		System.out.println(a + ", "+ b +", " + c + ", " + d);
		// use FileInputStream and FileOutputStream
		Dos.close();
		Dis.close();
		OutputStream Os = new FileOutputStream("Text/AB.txt");
		Dos = new DataOutputStream(Os); // wrapping
		Dos.writeInt(25);
		Dos.writeUTF("Zhu");
		Dos.writeBoolean(true);
		Dos.writeChar('S');
		Dos.flush();
		// the text inside file is garbled code
		// no need to worry
		InputStream Is = new FileInputStream("Text/AB.txt");
		Dis = new DataInputStream(Is); // wrapping
		a = Dis.readInt();
		b = Dis.readUTF();
		c = Dis.readBoolean();
		d = Dis.readChar();
		System.out.println(a + ", "+ b +", " + c + ", " + d);
		Dos.close();
		Dis.close();
		Os.close();
		Is.close();
	}
}
