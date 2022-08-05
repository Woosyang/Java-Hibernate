package Chapter15.Visitor;

/**
 * Test Visitor Pattern
 * For the objects stored in a collection, they may have different types 
 * (even if they have a common interface). For the objects in the collection, they can 
 * accept a class of objects called visitors to access them. Different visitors can 
 * access them in different ways.
 * not so popular
 * @author Woo
 *
 */
/*
 * In Visitor pattern, we use a visitor class which changes the executing algorithm 
 * of an element class. By this way, execution algorithm of element can vary as and 
 * when visitor varies. This pattern comes under behavior pattern category. As per the 
 * pattern, element object has to accept the visitor object so that visitor object 
 * handles the operation on the element object.
 * 
 */
public class VisitorTest {
	public static void main(String[] args) {
		ComputerPart computer = new Computer();
		computer.Accept(new ComputerPartDisplayVisitor());
	}
}
