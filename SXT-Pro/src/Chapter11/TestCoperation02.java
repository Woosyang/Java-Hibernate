package Chapter11;

/**
 * Cooperation Mode: Producer - Consumer
 * using the semaphore: true, false
 * @author Woo
 *
 */
public class TestCoperation02 {
	public static void main(String[] args) {
		Radio R = new Radio();
		new DJ(R).start();
		new Audience(R).start();
	}
}

// producer
class DJ extends Thread {
	Radio R;
	public DJ(Radio R) {
		this.R = R;
	}
	@Override
	public void run() {
		for (int i = 0; i < 20; i ++) {
			if (i % 2 == 0) {
				this.R.PlayMusic("<Watermelon Sugar>");
			} else {
				this.R.PlayMusic("<Come Around Me>");
			}
		}
	}
}

// consumer
class Audience extends Thread {
	Radio R;
	public Audience(Radio R) {
		this.R = R;
	}
	// the times of the loop must be same as producer's
	@Override
	public void run() {
		for (int i = 0; i < 20; i ++) {
			this.R.ListenToMusic();
		}
	}
}

// same resource
class Radio {
	String Music;
	// semaphore: true -> DJ plays the music, audience is waiting
	// false -> Audience listen to the music, DJ is waiting
	boolean Flag = true;
	// play 
	public synchronized void PlayMusic(String Name) {
		// if DJ is waiting
		if (!Flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("DJ is playing " + Name);
		this.Music = Name;
		// switch
		// this.Flag = ! this.Flag;
		this.notifyAll();
		this.Flag = ! this.Flag;
	}
	
	// listen
	public synchronized void ListenToMusic() {
		// if audience is waiting
		if (Flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Audiences are listening to " + this.Music);
		// switch
		// this.Flag = ! this.Flag;
		this.notifyAll();
		this.Flag = ! this.Flag;
	}
}