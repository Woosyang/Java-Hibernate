package Chapter15.Proxy;

/**
 * Implement the SuperStar interface
 * @author Woo
 *
 */
public class RealStar implements SuperStar {
	
	@Override
	public void Confer() {
		System.out.println("Do nothing"); // left it to the ProxyStar(Manager)
	}
	
	@Override
	public void signContract() {
		System.out.println("Do nothing"); // left it to the ProxyStar(Manager)
	}

	@Override
	public void sellTicket() {
		System.out.println("Do nothing"); // left it to the ProxyStar(Manager)
	}

	@Override
	public void Perform() {
		System.out.println("Harris is singing: Watermelon Sugar~"); // the only thing RealStar focus on
	}

	@Override
	public void getPaid() {
		System.out.println("Do nothing"); // left it to the ProxyStar(Manager)
	}
}
