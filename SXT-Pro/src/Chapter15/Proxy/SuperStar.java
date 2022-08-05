package Chapter15.Proxy;

/**
 * Interface For The Actual Role And Proxy Role
 * @author Woo
 *
 */
public interface SuperStar {
	public void Confer(); // talk to the sponsor
	public void signContract(); // confirm the show
	public void sellTicket(); // sell the ticket
	public void Perform(); // sing, dance...
	public void getPaid(); // get money
}
