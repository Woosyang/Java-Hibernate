package Chapter15.Visitor;

/**
 * Example Of Visitor Pattern
 * Used With Concrete Class Of Computer Part
 * @author Woo
 *
 */
public interface ComputerPartVisitor {
	public void Visit(Computer computer);
	public void Visit(Mouse mouse);
	public void Visit(Keyboard keyboard);
	public void Visit(Monitor monitor);
}

// concrete class
class ComputerPartDisplayVisitor implements ComputerPartVisitor {
	@Override
	public void Visit(Computer computer) {
		System.out.println("Displaying Computer.");
	}
	@Override
	public void Visit(Mouse mouse) {
		System.out.println("Displaying Mouse.");
	}
	@Override
	public void Visit(Keyboard keyboard) {
		System.out.println("Displaying Keyboard.");
	}
	@Override
	public void Visit(Monitor monitor) {
		System.out.println("Displaying Monitor.");
	}
}