package Chapter05;

/**
 * Test the CallBack mechanism
 * @author Woo
 *
 */

public class TestCallbackMethod {
	public static void main(String[] args) {
		O o = new O();
		P p = new P(o);
		p.AskQuestion("What's your feeling now?");
	}
}
// 1. fulfill the interface
interface CallBack {
	public void Answer(String Result);
}

class P implements CallBack {
	@Override
	public void Answer(String Result) {
		System.out.println("The answer is " + Result);  
	}
	// 2. include the reference of the class B
	private O b;
	public P(O b) {
		this.b = b;
	}
	public void AskQuestion(String Question) {
		b.Execute(P.this, Question);
	}
}

class O {
	// 3. class B has a method which contains the CallBack as the parameter
	public void Execute(CallBack callback, String results) {
		System.out.println("The Question of A is " + results);
		// assume it takes time
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// get the answer
		String result = "Happy";
		// return the answer
		callback.Answer(result);
	}
}