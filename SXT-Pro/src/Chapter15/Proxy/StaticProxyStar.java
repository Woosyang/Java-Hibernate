package Chapter15.Proxy;

/**
 * Used With SuperStar
 * Need to have the real role as the field inside(reference)
 * @author Woo
 *
 */
public class StaticProxyStar implements SuperStar {
	private SuperStar Star; // interface, not the RealStar class
	
	public StaticProxyStar(SuperStar Harris) {
		 Star = Harris;
	}
	
	@Override
	public void Confer() {
		System.out.println("Hello Ellen Show..."); // ProxyStar focus on
	}

	@Override
	public void signContract() {
		System.out.println("20k$ for Harris"); // ProxyStar focus on
	}

	@Override
	public void sellTicket() {
		System.out.println("each ticket is for 30$"); // ProxyStar focus on
	}

	@Override
	public void Perform() {
		Star.Perform(); // let the real role to do it
	}

	@Override
	public void getPaid() {
		System.out.println("Earned 1000k$ ~"); // ProcyStar focus on
	}
}
