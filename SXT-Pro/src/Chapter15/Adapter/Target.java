package Chapter15.Adapter;

/**
 * Target Class/Interface: the client can directly use, it can be a concrete class
 * or an abstract class or an interface.
 * Client use target to call the function of the adaptee class  
 * @author Woo
 *
 */
public interface Target {
	public void HandleRequest();
}
