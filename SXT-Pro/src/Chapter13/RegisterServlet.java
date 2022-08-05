package Chapter13;

/**
 * implement with Servlet, maps to Web.xml
 * @author Woo
 *
 */
public class RegisterServlet implements Servlet {
	@Override
	public void Service() {
		System.out.println("RegisterServlet");
	}
	
	
	@Override
	public void Service(Request Req, Response Rep) {
		Rep.Println("Successfully Registered....");
	}
}
