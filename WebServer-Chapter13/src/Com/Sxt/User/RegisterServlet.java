package Com.Sxt.User;
import Com.Sxt.Core.*;

/**
 * implement with Servlet, maps to Web.xml
 * @author Woo
 *
 */
public class RegisterServlet implements Servlet {
	// focus on the logic of the business
	@Override
	public void Service(Request Req, Response Rep) {
		// <input type ="text" name="uname" id="uname"/>
		String Uname = Req.GetParameterValue("uname"); // find the value of the user name
		String[] Favs = Req.GetParameterValues("fav"); // find the value of the corresponding fav -> "0", "1", "2"
		Rep.Print("<html>");
		Rep.Print("<head>");
		// decode other language in response  
		Rep.Print("<meta http-equiv=\"content-type\" content=\"text/html;charset=UTF-8\">");
		Rep.Print("<title>");
		//                                  Request.java, show the value in Postman
		Rep.Print("Registered Successfully~"); // sxt
		Rep.Print("</title>");
		Rep.Print("</head>");
		Rep.Print("<body>");
		Rep.Println("The User Name You Have Created Is: " + Uname);
		Rep.Println("The Character You Have Selected Are: ");
		for (String Va : Favs) {
			if (Va.equals("0")) {
				Rep.Print("A");
			} else if (Va.equals("1")) {
				Rep.Print("B");
			} else {
				Rep.Print("C");
			}
		}
		Rep.Print("</body>");
		Rep.Print("</html>");
	}
}
