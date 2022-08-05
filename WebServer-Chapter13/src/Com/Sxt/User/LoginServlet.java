package Com.Sxt.User;
import Com.Sxt.Core.*;

/**
 * implement with Servlet, maps to Web.xml
 * @author Woo
 *
 */
public class LoginServlet implements Servlet {
	// goal 3 for the SimpleServerPro.java
	// for the SimpleServerPro.java and Response.java
	@Override
	public void Service(Request Req, Response Rep) {
		// line 50 to 60 in SimpleServerPro.java
		Rep.Print("<html>");
		Rep.Print("<head>");
		// decode other language in response  
		Rep.Print("<meta http-equiv=\"content-type\" content=\"text/html;charset=UTF-8\">");
		Rep.Print("<title>");
		//                                  Request.java, show the value in Postman
		Rep.Print("Welcome, My Friend:  " + Req.GetParameterValue("uname") + " ~"); // sxt
		Rep.Print("</title>");
		Rep.Print("</head>");
		Rep.Print("<body>");
		Rep.Print("Welcome~ " + Req.GetParameterValue("uname"));
		Rep.Print("</body>");
		Rep.Print("</html>");
	}
}
