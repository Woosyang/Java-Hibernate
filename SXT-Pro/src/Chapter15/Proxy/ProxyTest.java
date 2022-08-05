package Chapter15.Proxy;

import java.lang.reflect.Proxy;

/**
 * Test The Proxy Pattern
 * Used With SuperStar interface
 * Client only contacts the ProxyStar, never calls the method of the RealStar(real role)
 * need to initialize the RealStar and the ProxyStar
 * @author Woo
 *
 */
/*
 * In proxy pattern, a class represents functionality of another class. This type of 
 * design pattern comes under structural pattern.
 * In this pattern, we create object having original object to interface its 
 * functionality to outer world.
 *
 */
// static proxy, the proxy class is defined by ourselves

// dynamic proxy, the proxy class is prepared by JDK and used with InvocationHandler
// all of the method declared by the abstract role will be managed by the invoke()
// of the handler
public class ProxyTest {
	public static void main(String[] args) {
		System.out.println("Test The Static Proxy Pattern: ");
		// order: Client -> Proxy -> Real
		// abstract role(SuperStar): external interface for defining the real role
		//                           and proxy role
		
		// real role: implement the abstract role(interface), define the real 
		
		// functionality and be called by the proxy(agent) role
		SuperStar Real = new RealStar();
		
		// proxy role: implement the abstract role(interface), realized the abstract
		// method of the interface by calling the real method of the real role. Also
		// it can modify the functionality of the real role
		SuperStar Proxy01 = new StaticProxyStar(Real);
		
		Proxy01.Confer();  // proxy role's job
		Proxy01.signContract(); // proxy role's job
		Proxy01.sellTicket(); // proxy role's job
		Proxy01.Perform(); // real role's job
		Proxy01.getPaid(); // proxy role's job
		
		System.out.println("Test The Dynamic Proxy Pattern: ");
		// order: Client -> Proxy -> Handler -> Real
		SuperStar Proxy02 = new StaticProxyStar(Real);
		SuperStarHandler Handler = new SuperStarHandler(Real);
		// do not use "Proxy" to defien an object otherwise the Proxy from java.lang.reflect
		// will not show up
		// dynamic proxy role
		Proxy02 = (SuperStar) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), 
													 new Class[] {SuperStar.class}, Handler); 
		// if we don't do manage the method inside invoke() in the Handler class
		// all of the output of the methods inside proxy role will be same as real role's 
		Proxy02.Confer(); // same as the real role's
		// signContract(), sellTicket(), Perform(), getPaid() are all the same as the
		// real roles'(RealStar)
		// all of the method of the proxy class comes to invoke() of the Handler class
		// invoke() can manage all of the method of the proxy class
		Proxy02.Perform();
	}
}
