package Chapter15.AbstractFactory;

/**
 * one of family of related product
 * @author Woo
 *
 */
public interface Engine {
	public void Run();
}

class GoodEngine implements Engine {
	@Override
	public void Run() {
		System.out.println("Good Engine");
	}
}

class MediumEngine implements Engine {
	@Override
	public void Run() {
		System.out.println("Medium Engine");
	}
}

class BadEngine implements Engine {
	@Override
	public void Run() {
		System.out.println("Bad Engine");
	}
}