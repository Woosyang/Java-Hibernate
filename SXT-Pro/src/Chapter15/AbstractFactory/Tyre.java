package Chapter15.AbstractFactory;

/**
 * one of family of related product
 * @author Woo
 *
 */
public interface Tyre {
	public void Revolve();
}

class GoodTyre implements Tyre {
	@Override
	public void Revolve() {
		System.out.println("Durable");
	}
}

class MediumTyre implements Tyre {
	@Override
	public void Revolve() {
		System.out.println("Ok..");
	}
}

class BadTyre implements Tyre {
	@Override
	public void Revolve() {
		System.out.println("Sucks..");
	}
}