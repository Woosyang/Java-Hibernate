package Chapter15.Visitor;

/**
 * Example Of Visitor Pattern
 * Used With ComputerPartVisitor
 * @author Woo
 *
 */
public interface ComputerPart {
	public void Accept(ComputerPartVisitor computerPartVisitor);
}

// concrete class, used with concrete ComputerPartVisitor
class Keyboard implements ComputerPart {
	@Override
	public void Accept(ComputerPartVisitor computerPartVisitor) {
		computerPartVisitor.Visit(this);
	}
}

class Monitor implements ComputerPart {
	@Override
	public void Accept(ComputerPartVisitor computerPartVisitor) {
		computerPartVisitor.Visit(this);
	}
}

class Mouse implements ComputerPart {
	@Override
	public void Accept(ComputerPartVisitor computerPartVisitor) {
		computerPartVisitor.Visit(this);
	}
}

class Computer implements ComputerPart {
	ComputerPart[] Parts;
	public Computer(){
		Parts = new ComputerPart[] { new Mouse(), new Keyboard(), new Monitor() };		
	} 
	@Override
	public void Accept(ComputerPartVisitor computerPartVisitor) {
		for (int i = 0; i < Parts.length; i ++) {
			Parts[i].Accept(computerPartVisitor);
	    }
	    computerPartVisitor.Visit(this);
	}
}