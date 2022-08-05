package Chapter15.Facade;

/**
 * Example Of Facade Pattern
 * @author Woo
 *
 */
public interface Shape {
	public void Draw();
}

class Square implements Shape {
	@Override
	public void Draw() {
		System.out.println("It is a square..");
	}
}

class Rectangle implements Shape {
	@Override
	public void Draw() {
		System.out.println("It is a rectangle..");
	}
}

class Circle implements Shape {
	@Override
	public void Draw() {
		System.out.println("It is a circle..");
	}
}