package Com.Sxt.Core;

/**
 * Used With WebContext.java And AnalyzeWebXML.java -> Service()
 * Used With Response.java And Request.java -> Goal 3 For SimpleServerPro
 * @author Woo
 *
 */
public interface Servlet {
	public void Service(Request Req, Response Rep);
}
