package Chapter15.AbstractFactory;

/**
 * one of family of related product
 * @author Woo
 *
 */
public interface Seat {
	public void SitOn();
}

class GoodSeat implements Seat {
	@Override
	public void SitOn() {
		System.out.println("Comfortable");
	}
}

class MediumSeat implements Seat {
	@Override
	public void SitOn() {
		System.out.println("So-So");
	}
}

class BadSeat implements Seat {
	@Override
	public void SitOn() {
		System.out.println("Bloody Hell");
	}
}