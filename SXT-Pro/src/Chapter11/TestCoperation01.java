package Chapter11;

/**
 * Cooperation Mode: Producer - Consumer, Pipelining
 * using the buffer zone
 * @author Woo
 *
 */
public class TestCoperation01 {
	public static void main(String[] args) {
		SynContainer S = new SynContainer(); // has to be initialized
		new Producer(S).start();
		new Consumer(S).start();
	}
}

// producer
class Producer extends Thread {
	SynContainer Syn; // using the same resource 
	public Producer(SynContainer Syn) {
		this.Syn = Syn;
	}
	@Override
	public void run() {
		// want to produce how many
		// if the iteration's times is smaller than the consumer's
		// the whole program will suspend
		for (int i = 0; i < 100; i ++) {
			System.out.println("No." + i + "th" + " Bread" + " is Done ~");
			Syn.Push(new Bread(i));
		}
	}
}

// consumer
class Consumer extends Thread {
	SynContainer Syn; // using the same resource
	public Consumer(SynContainer Syn) {
		this.Syn = Syn;
	}
	
	@Override
	// consume
	public void run() {
		for (int i = 0; i < 100; i ++) {
			System.out.println("No." + 	Syn.Pop().ID + "th " + "Bread" + " is Gone ..");
		}
	}
}

// buffer zone
class SynContainer {
	// container for the resources
	Bread[] Br = new Bread[10]; // max number for producer and consumer
	int Count = 0; // counter
	// store the resource
	// producing
	// if you don't use the synchronized, it will cause the "out of bounce" problem
	// lock this.Br
	public synchronized void Push(Bread Br) {
		// make sure whether the container has the extra space
		// if it does, let Push(Br) work, if it doesn't
		// wait
		if (Count == this.Br.length) {
			try {
				this.wait(); // thread is blocked, need consumer to unlock this
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.Br[Count ++] = Br; 
		// Count ++
		// assume the max Capacity is 10: 0 - 9
		// when Count = 9, Br[Count] = Br[9], then Count = 10 
		this.notifyAll(); // let Consumer know it can keep going
	}
	// require the resource
	// get the resource from the bottom
	// requiring
	// if you don't use the synchronized, it will cause the "out of bounce" problem
	// lock this.Br
	public synchronized Bread Pop() {
		// count --
		// make sure whether the container has the resource
		// if it does, let the Pop() work, if it doesn't
		// wait
		if (Count == 0) {
			try {
				this.wait(); // thread is block, needs producer to unlock
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// if Count = 10, it must decrease first to 9 otherwise it will be out of bounce
		Bread Br = this.Br[-- Count];
		this.notifyAll(); // let the Producer know they can keep going
		return Br;
	}
}

// product
class Bread {
	int ID;
	public Bread(int ID) {
		this.ID = ID;
	}
}