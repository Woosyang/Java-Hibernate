package Chapter10;
import java.io.*;

/**
 * encoding: String -> Bytes
 * decoding: Bytes -> String
 * @author Woo
 *
 */
public class ContentEncodeAndDeCode {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String Msg = "Black Live Matters, ÉúÃü³Ï¿É¹ó"; // GBK
		// encoding: byte[]
		byte[] Data = Msg.getBytes();
		System.out.println(Data.length);
		// encoding: use other Char-Set
		Data = Msg.getBytes("UTF-16LE"); // needs to throw exception
		System.out.println(Data.length);           // has to match the encoding Char-Set
		// decoding: String(byte[] bytes, int offset, int length, String charseName)
		Msg = new String(Data, 0, 50, "UTF-16LE"); 
		String msg00 = new String(Data, 0, Data.length, "GBK");
		System.out.println(Msg);
		System.out.println(msg00); // garbled code
		// the reason why garbled code occurs:
		// 1. the length of the byte[] is not correct
		String msg01 = new String(Data, 0, Data.length - 1, "UTF-16LE");
		System.out.println(msg01); // garbled code
		// 2. the Char-Set is not matched with the originals 
		String msg02 = new String(Data, 0, Data.length, "UTF-8");
		System.out.println(msg02); // garbled code
	}
}
