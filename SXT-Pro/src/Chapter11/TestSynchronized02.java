package Chapter11;

/**
 * SafeThread02
 * fix the unsafe thread02 -> with draw money
 * solve the problem of concurrency:
 * use synchronized block, the target you lock is more accurate
 * guarantee the correctness of the data
 * increase the efficiency as best as it can
 * @author Woo
 *
 */
public class TestSynchronized02 {
	public static void main(String[] args) {
		Account A = new Account(100, "Company");
		WithDrawMoneyPro B01 = new WithDrawMoneyPro(A, 50);
		WithDrawMoneyPro B02 = new WithDrawMoneyPro(A, 80);
		new Thread(B01, "Sam").start();
		new Thread(B02, "Smith").start();
	}
}

class WithDrawMoneyPro implements Runnable {
	Account Account;
	int Withdraw;
	int Cash = 0;
	
	public WithDrawMoneyPro(Account Account, int Withdraw) {
		this.Account = Account;
		this.Withdraw = Withdraw;
	}
	
	@Override
	public void run() {
		// optimize, increase the efficiency
		if (Account.Balance <= 0) {
			return;
		}
		Withdraw();
	}
	
	// encapsulation
	
	// wrong way to solve the concurrent problem
	// add the synchronized directly into Withdraw method
	// it can't lock the variable the threads fighting for (Account.Balance)
	// it only locks the fields of this object
	// have to lock the variable of the Account
	/*
	public void Withdraw() {
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
	System.out.println(Thread.currentThread().getName() + "---->" + " the balance is: " + Account.Balance);
	System.out.println(Thread.currentThread().getName() + "---->" + "the cash you have: " + Withdraw);
	}
	*/
	
	// right way
	public void Withdraw() {
		// block -> lock the field Account has
		synchronized (Account) {
			// wrong: if (Account.Balance < 0), the code will keep going
			// the outcome might be negative
			if (Account.Balance - Withdraw < 0) {
				return;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Account.Balance = Account.Balance - Withdraw;
			Cash = Cash + Withdraw;
			System.out.println(Thread.currentThread().getName() + "---->" + " the balance is: " + Account.Balance);
			System.out.println(Thread.currentThread().getName() + "---->" + " the cash you have: " + Withdraw);
		}	
	}
}
