package Chapter11;

/**
 * example: account of the bank
 * @author Woo
 *
 */
public class UnsafeThread02 {
	public static void main(String[] args) {
		Account A = new Account(100, "Company");
		WithdrawMoney Woman = new WithdrawMoney(A, 80, "Nancy");
		WithdrawMoney Man = new WithdrawMoney(A, 70, "Smith");
		new Thread(Woman).start();
		new Thread(Man).start();
	}
}

// can use extend to start the thread
class WithdrawMoney implements Runnable {
	Account Account; 
	int Withdraw;
	int Cash = 0;
	String Name;
	public WithdrawMoney(Account Account, int Withdraw, String Name) {
		this.Name = Name;
		this.Account = Account;
		this.Withdraw = Withdraw;
	}
	
	@Override
	public void run() {
		// still doesn't work
		if (Account.Balance < 0) {
			return;
		}
		// simulate the delay
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Account.Balance = Account.Balance - Withdraw;
		Cash = Cash + Withdraw;
		System.out.println(Name + "------>" + "the balance of the account is: " + Account.Balance);
		System.out.println(Name + "------>" + "the cash you have is: " + Cash);
	}
	
}