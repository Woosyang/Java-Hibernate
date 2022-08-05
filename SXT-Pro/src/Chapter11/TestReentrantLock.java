package Chapter11;

/**
 * ReentrantLock: the lock can be used continually
 * Non-ReentrantLock: the lock can't be used continually
 * create a reentrant lock: having the counter
 * @author Woo
 *
 */
public class TestReentrantLock {
	public void Test() {
		// synchronized block
		// first time to have the lock
		synchronized (this) {
			while (true) {
				// second time to have the same lock
				// lock this twice
				synchronized (this) {
					// the code can keep going down
					System.out.println("ReentrantLock~");
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	NonReLock L = new NonReLock();
	ReLock R = new ReLock();
	
	public void Usman() throws InterruptedException {
		// L.Lock();
		R.Lock();
		System.out.println(R.GetHoldCount()); // 1: 0 -> 1
		DoSomething();
		System.out.println(R.GetHoldCount());  // 2: 1 -> 2(Lock) then 1: 2 -> 1 (Unlock)
		// DoSomething(); can't keep going down, right now it is an endless loop if using the NonReLock L
		// L.Unlock();
		R.Unlock(); 
		System.out.println(R.GetHoldCount()); // 0: 1 -> 0
	}
	
	public void DoSomething() throws InterruptedException {
		// L.Lock(); // stuck here
		// //
		// L.Unlock();
		R.Lock();
		System.out.println(R.GetHoldCount()); // 2
		R.Unlock();
		System.out.println(R.GetHoldCount()); // 1
	}
	
	public static void main(String[] args) throws InterruptedException {
		// new TestReentrantLock().Test(); this is Reentrant
		/* using L.Lock() and L.UnLock() will cause an endless loop
		TestReentrantLock T = new TestReentrantLock();
		T.Usman();
		T.DoSomething();
		*/
		TestReentrantLock T = new TestReentrantLock();
		T.Usman();
		T.DoSomething();
		Thread.sleep(1000);
		System.out.println(T.R.GetHoldCount()); // make sure whether the lock has been released
		// if GetHoldCount() = 0 -> release the lock totally
	}
}

// create a non-reentrant lock
class NonReLock {
	// whether it is locked
	private boolean IsLocked = false;
	
	// producer: take the lock
	public synchronized void Lock() throws InterruptedException {
		while(IsLocked) {
			wait();
		}
		IsLocked = true; // only can be used in one time, then goes to the endless loop
	}
	
	// consumer: release the lock
	public synchronized void Unlock() {
		IsLocked = false;
		notify(); // for Lock()
	}
}

class ReLock {
	// whether it is locked
	private boolean IsLocked = false;
	private Thread LockedBy = null; // store the thread
	private int HoldCount = 0;
	// producer, use the lock
	public synchronized void Lock() throws InterruptedException {
		Thread T = Thread.currentThread();
		// if the thread is not itself
		while (IsLocked && LockedBy != T) {
			wait();
		}
		IsLocked = true;
		LockedBy = T;
		HoldCount ++; // locking times
	}
	// consumer, release the lock
	public synchronized void Unlock() {
		if (Thread.currentThread() == LockedBy) {
			HoldCount --;
			if (HoldCount == 0) { // no thread is using the lock
				IsLocked = false;
				notify();
				LockedBy = null;
			}
		}
	}
	int GetHoldCount() {
		return HoldCount;
	}
}
