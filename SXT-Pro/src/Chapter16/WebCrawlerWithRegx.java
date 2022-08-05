package Chapter16;
import java.net.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.*;
import java.io.*;

/**
 * Create a web crawler to fetch the data(core code) by using Regex
 * @author Woo
 *
 */
public class WebCrawlerWithRegx {
	
	// fetch the source code of the corresponding URL
	public static String GetURLContent(String StrUrl, String CharSet) {
		StringBuilder Sb = new StringBuilder();
		try {
			URL Url = new URL(StrUrl);
			BufferedReader Br = new BufferedReader(new InputStreamReader(Url.openStream(), Charset.forName(CharSet)));
			String Temp = " ";
			while (Temp != null) {
				Temp = Br.readLine();
				if (Temp == null) {
					break;
				}
				Sb.append(Temp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Sb.toString();
	}
	
	public static List<String> GetMatcherSubString(String Des, String Regex) {
		// get all the link, "/" needs to be escaped to "\\/"
		// Pattern P = Pattern.compile("<link[\\s\\S]+?\\/>"); // get the whole content of the <link></link>
		// get the content of the href = ""
		// (.+?) -> not greedy for ".+"
		// "href=\"(.+?)\""
		List<String> List = new ArrayList<>();
		Pattern P = Pattern.compile(Regex); // \" -> ""
		Matcher M = P.matcher(Des);
		while (M.find()) {
			List.add(M.group());
		}
		return List;
	}
	
	public static void main(String[] args) {
		String DesStr = GetURLContent("https://www.yahoo.com", "utf-8");
		// System.out.println(DesStr);
		// List<String> A = GetMatcherSubString(DesStr, "href=\"(.+?)\""); 
		List<String> A = GetMatcherSubString(DesStr, "href=\"([\\w\\s./:]+?)\""); 
		for (String a : A) {
			System.out.println(a);
		}
	}
}
