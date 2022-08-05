package Chapter11;

/**
 * Stop the thread:
 * 1. when the thread is finished by it self (reach the times the thread wants)
 * 2. external interference --> adding the flag(boolean)
 * don not use stop(), destroy()
 * @author Woo
 *
 */
public class TerminateThread implements Runnable {
	// adding the flag to record whether this flag can be executed normally
	private boolean Flag = true;
	private String Name;
	
	// constructor: a block of codes similar to the method
	public TerminateThread(String Name) {
		this.Name = Name;
	}
	
 	@Override
	public void run() {
		// relative flag(boolean), true -> run, false ->stop
		int i = 0;
		while (Flag) {
			System.out.println(Name + "----->" + "New Rap New Star" + i ++);
		}
	}   
	
 	// offer the method to change the flag(true, false) from outside 
 	public void Terminate() {
 		this.Flag = false;
 	}
 	
 	public static void main(String[] args) {
 		TerminateThread Tt = new TerminateThread("Smelly D");
 		new Thread(Tt).start(); // one path
 		for (int i = 0; i < 100; i ++) { // another path
 			if (i == 80) {
 				Tt.Terminate();
 				System.out.println("WoW"); // over Rain over me 80 
 			}
 			System.out.println("Rain Over Me" + i ++);
 		}
 	}
 }
