package Chapter13;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Goal 2 For SimpleServerPro
 * Encapsulate The Request Part Of The ReceiveData() in SimpleServer.java
 * Encapsulate The Request Parameters With Map
 * Helper Class For SimpleServerPro.java
 * @author Woo
 *
 */
public class Request {
	// protocol information
	private String RequestInfo;
	// request method
	private String RequestMethod;
	// request URI
	private String Uri;
	// request parameters
	private String Query; // after "?"
	// store the request parameter
	// one field may relates to multiple information -> use List to store those information
	// eg: fav=1&fav=2, same fav but 2 different values
	private Map<String, List<String>> ParameterMap;
	
	public Request(InputStream Is) {
		ParameterMap = new HashMap<>();
		byte[] Datas = new byte[1024 * 1024]; // receiving container
		int Len;
		try {
			Len = Is.read(Datas);
			this.RequestInfo = new String(Datas, 0, Len);
			System.out.println(RequestInfo);
		} catch (IOException e) {
			e.printStackTrace();
			return; // stop the Request if there is something wrong with Response
		}
		// split the string -> get Requested Mehtod(Post/Get), URI and Requested Parameters
		ParseRequestInfo();
	}
	
	public Request(Socket Client) throws Exception {
		this(Client.getInputStream()); // call the constructor above
	}
	
	private void ParseRequestInfo() {
		System.out.println("--Start To Split The String--");
		System.out.println("..1. get the request method: From Start Of The String To The First '/'.. ");
		// filter out the space after the Post/ Get by using trim()
		this.RequestMethod = this.RequestInfo.substring(0, this.RequestInfo.indexOf("/")).toLowerCase().trim();
		System.out.println("Request Method Name: " + RequestMethod);
		System.out.println("..2. get the request url: From The First '/' To 'HTTP/'..");
		System.out.println("may contain the requested parameters if it has '?' inside");
		System.out.println("the string before the '?' is the uri");
		// 1. get the position of the first "/" 
		int IndexS = this.RequestInfo.indexOf("/") + 1; // remove the "/" 
		// 2. get the position of "HTTP/" 
		int IndexE = this.RequestInfo.indexOf("HTTP/"); // the position of "H" 
		// 3. get the substring 
		this.Uri = this.RequestInfo.substring(IndexS, IndexE).trim(); // [,), need to filter out the spaces after the Uri 
		// 4. get the position of "?"
		int IndexQ = this.Uri.indexOf("?");
		if (IndexQ >= 0) { // the URI has the requested parameters
			String[] UrlArray = this.Uri.split("\\?"); // careful, "?" must be escpaed cuz "?" means something in regex
			this.Uri = UrlArray[0];
			this.Query = UrlArray[1].trim(); // requested parameters
		}
		System.out.println("Requested URI: " + this.Uri);
		System.out.println("..3. get the request parameters..");
		System.out.println("if the method is 'GET', the requested parameters are already fetched");
		System.out.println("if the method is 'POST', the requested parameters may be inside the request body");
		if (this.RequestMethod.equals("post")) {
			// request parameters inside request body -> from the last "\r\n" to the end
			String Qstr = this.RequestInfo.substring(this.RequestInfo.lastIndexOf("\r\n")).trim();
			if (Query == null) { // no parameters after the "?"
				Query = Qstr; // Query may still be null cuz Qstr may be null
			} else {
				Query = Query + "&" + Qstr; // joint the string
			}
			Query = Query == null ? "" : Query;
		}
		System.out.println("Requested Query: " + this.Query);
		
		// convert the Query to Map -> easy to find the information to the corresponding uname
		// eg for Query: fav=1&fav=2&uname=sxt&age=18
		if (Query != null) {
			QueryToMap();
		}
	}
	
	// split the this.Query and store them with Map
	private void QueryToMap() {
		// split the string according to "&"
		// eg: fav=1&fav=2
		String[] KeyValues = Query.split("&");
		// eg: KeyValues[0] = fav=1, KeyValues[1] = fav=2
		for (String Qry : KeyValues) {
			// split the substirng one more time according to "="
			// get the corresponding fields
			// eg: Kv[0] = fav, Kv[1] = 1
			String[] Kv = Qry.split("=");
			Kv = Arrays.copyOf(Kv, 2); // avoid the substring has nothing after "=", make sure the Kv's length is always 2
			// get the Key -> feild
			String Key = Kv[0];
			// get the Value
			// may be a garble code if it is other language(CHN)
			String Value = Kv[1] == null ? null : Decode(Kv[1], "UTF-8"); 
			if (!ParameterMap.containsKey(Key)) { // it is the first time to store the value of this feild
				ParameterMap.put(Key, new ArrayList<String>()); // create a new K-V pair
			}
			// ParameterMap.get(Key) -> get the corresponding ArrayList
			ParameterMap.get(Key).add(Value); // add the information after "=" to corresponding Key
			// it works for both the Key already exist and the new Key
		}
	}
	
	// decode the other language as the value of the feild for the broswer
	private String Decode(String Value, String Enc /*char-set*/) {
		try {
			return java.net.URLDecoder.decode(Value, Enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// get the corresponding value according to the feild
	// eg: fav=1&fav=2 -> get 1, 2 according to "fav"
	// get multiple values from the Key
	public String[] GetParameterValues(String Key) {
		List<String> Vs = ParameterMap.get(Key);
		if (Vs == null || Vs.size() < 0) { // no values
			return null;
		}
		return Vs.toArray(new String[0]); // convert List to String[], String[0] -> save the memory
	}
	
	// get one value from the Key cuz the Key only got one value
	public String GetParameterValue(String Key) {
		String[] Vs = GetParameterValues(Key); // directly call the method above
		return Vs == null ? null : Vs[0]; // Vs[0] is also a List
	}

	public String getRequestMethod() {
		return RequestMethod;
	}

	public String getUri() {
		return Uri;
	}

	public String getQuery() {
		return Query;
	}
}
