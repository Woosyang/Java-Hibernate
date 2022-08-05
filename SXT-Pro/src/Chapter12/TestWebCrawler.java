package Chapter12;
import java.net.*;
import java.io.*;

/**
 * principle of the web crawler
 * @author Woo
 *
 */
public class TestWebCrawler {
	public static void main(String[] args) throws Exception {
		// 1. get the URL
		URL U = new URL("https://www.google.com");
		// 2. download the resource
		InputStream Is = U.openStream();
		// character stream                                               char-set 
		BufferedReader Br = new BufferedReader(new InputStreamReader(Is, "UTF-8"));
		String Msg01 = " ";		
		while (Msg01 != null) {
			Msg01 = Br.readLine();
			if(Msg01 == null) {
				break;
			}
			System.out.println(Msg01);
		}
		// not every web site can be accessed by web crawler
		// try another web site(reject the web crawler)
		U = new URL("https://www.dianping.com");
		
		// it will cause a exception
		// Is = U.openStream(); won't work, you have to simulate a browser
		// using Connection
		HttpURLConnection Conn = (HttpURLConnection) U.openConnection();
		// request - respond mode
		// get() or post()
		Conn.setRequestMethod("GET");
		Conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36");
		// InputStreamReader(Conn.getInputStream(), "UTF-8")
		Br = new BufferedReader(new InputStreamReader(Conn.getInputStream(), "UTF-8"));
		System.out.println("--------------------");
		// use a new string to receive the source code of the new web site
		String Msg02 = " ";
		while (Msg02 != null) {
			Msg02 = Br.readLine();
			if(Msg02 == null) {
				break;
			}
			System.out.println(Msg02);
		}
		Br.close();
		// 3. analyze
		// 4. process
	}
}
