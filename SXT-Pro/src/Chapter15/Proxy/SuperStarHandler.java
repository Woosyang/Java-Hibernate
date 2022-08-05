package Chapter15.Proxy;
import java.lang.reflect.*;

/**
 * Test The Dynamic Proxy Pattern
 * Used With SuperStar interface
 * Called By The Proxy Test
 * Need to have the SuperStar(realized by real role) as the parameter inside
 * @author Woo
 *
 */
public class SuperStarHandler implements InvocationHandler {
	private SuperStar RealStar;
	
	public SuperStarHandler(SuperStar Star) {
		RealStar = Star;
	}
	
	// manage all of the method of the dynamic proxy class
	@Override  //                       reflection
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		/* if the method got the returned value, then it should be
		 * Object O = method.invoke(RealStar, args);
		 * return O;
		 */
		// filter out the method client really want for the real role
		if (method.getName().equals("Perform")) {
			method.invoke(RealStar, args);
		}
		return null;
	}	
}
