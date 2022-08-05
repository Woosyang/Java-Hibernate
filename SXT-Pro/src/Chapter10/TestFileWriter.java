package Chapter10;
import java.io.*;

/**
 * based on TestFileInputStream01
 * FileWriter: character stream
 * @author Woo
 *
 */
public class TestFileWriter {
	public static void main(String[] args) {
		File Src = new File("Text/CD.txt");
		Writer W = null;
		try {
			String Msg = "Love is a funny thing, ³¯³¯ÄºÄº";
			// Method I: char Flush[] = Msg.toCharArray(); // byte Flush[] = Msg.getBytes()
			W = new FileWriter(Src);
			// W.write(Flush, int off, int len);
			// Method I: W.write(Flush);
			// Method II: 
			W.write(Msg);
			// W.write("Á½ÇéÈôÊÇ³¤¾ÃÊ±");
			// Method III: W.append("Love is a funny thing, ").append("³¯³¯ÄºÄº");
			W.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (W != null) {
					W.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
