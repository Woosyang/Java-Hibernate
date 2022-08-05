package Chapter11;

/**
 * In computer programming, the proxy pattern is a software design pattern.
 * A proxy, in its most general form, is a class functioning as an interface 
 * to something else. In layman¡¯s term, a proxy class in java is 
 * a class that delegates responsibility ¡°in-place of¡± or ¡°on behalf of¡± another class. 
 * The object, a proxy imitates is called the implementation object.
 * 
 * static proxy:
 * public interface implemented by:
 * 1. agent object
 * 2. actual object
 * @author Woo
 *
 */
public class TestStaticProxy {
	public static void main(String[] args) {
		new WeddingCompany(new James()).HappyMarry();
		// similar with
		// Object in the ()
		// new Thread(Target Object For Thread).start();
	}
}

interface Marry {
	void HappyMarry();
}

// actual object
class James implements Marry {
	@Override
	public void HappyMarry() {
		System.out.println("Be Happy");
	}
}

// agent object
// make the actual object become much more powerful
class WeddingCompany implements Marry {
	// actual object, target
	// right now it is James
	private Marry Target;
	public WeddingCompany(Marry Target) {
		this.Target = Target;
	}
	
	@Override
	public void HappyMarry() {
		Prepare();
		this.Target.HappyMarry();
		Ready();
	}
	
	public void Prepare() {
		System.out.println("Dress up");
	}
	
	public void Ready() {
		System.out.println("Kiss");
	}
}