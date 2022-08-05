package Chapter15.Proxy;

/**
 * Simulate the process of how SuperStarHandler create a dynamic proxy class
 * for RealStar class
 * @author Woo
 *
 */
public class DynamicProxyStarSimulation implements SuperStar {
	private SuperStarHandler Handler;
	// explain why all of the method of the proxy role will be managed by invoke()
	@Override
	public void Confer() {
		try {
			Handler.invoke(this, this.getClass().getDeclaredMethod(""), null);
		} catch (Throwable e) {
			e.printStackTrace();
		} // just simulate
	}

	@Override
	public void signContract() {
		try {
			Handler.invoke(this, this.getClass().getDeclaredMethod(""), null);
		} catch (Throwable e) {
			e.printStackTrace();
		} // just simulate
	}

	@Override
	public void sellTicket() {
		try {
			Handler.invoke(this, this.getClass().getDeclaredMethod(""), null);
		} catch (Throwable e) {
			e.printStackTrace();
		} // just simulate
	}

	@Override
	public void Perform() {
		try {
			Handler.invoke(this, this.getClass().getDeclaredMethod(""), null);
		} catch (Throwable e) {
			e.printStackTrace();
		} // just simulate
	}

	@Override
	public void getPaid() {
		try {
			Handler.invoke(this, this.getClass().getDeclaredMethod(""), null);
		} catch (Throwable e) {
			e.printStackTrace();
		} // just simulate
	}
}
