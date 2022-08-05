package Chapter11;

/**
 * used for unsafe thread02 and synchronized02
 * @author Woo
 *
 */
public class Account {
	public int Balance;
	private String Name;
	// constructor
	public Account(int Balance, String Name) {
		this.Balance = Balance;
		this.Name = Name;
	}
}
