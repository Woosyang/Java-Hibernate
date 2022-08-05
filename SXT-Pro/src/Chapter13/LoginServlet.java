package Chapter13;

/**
 * implement with Servlet, maps to Web.xml
 * @author Woo
 *
 */
public class LoginServlet implements Servlet {
	@Override
	public void Service() {
		System.out.println("LoginServlet");
	}
	
	// goal 3 for the SimpleServerPro.java
	// for the SimpleServerPro.java and Response.java
	@Override
	public void Service(Request Req, Response Rep) {
		// line 50 to 60 in SimpleServerPro.java
		Rep.Print("<html>");
		Rep.Print("<head>");
		Rep.Print("<title>");
		//                                  Request.java, show the value in Postman
		Rep.Print("Server Is Responsing For " + Req.GetParameterValue("uname") + " ~"); // sxt
		Rep.Print("</title>");
		Rep.Print("</head>");
		Rep.Print("<body>");
		Rep.Print("Welcome.....");
		Rep.Print("</body>");
		Rep.Print("</html>");
	}
}
