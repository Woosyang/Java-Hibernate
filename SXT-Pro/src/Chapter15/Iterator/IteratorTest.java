package Chapter15.Iterator;

/**
 * Test The Iterator Pattern
 * Can also use this to realize reverse iterator in the inner class of MyConcreteIterator
 * @author Woo
 *
 */
/*
 * 
 * Iterator pattern is very commonly used design pattern in Java and .Net programming 
 * environment. This pattern is used to get a way to access the elements of a 
 * collection object in sequential manner without any need to know its underlying 
 * representation.
 * Iterator pattern falls under behavioral pattern category.
 * 
 */
public class IteratorTest {
	public static void main(String[] args) {
		MyConcreteAggregate Mca = new MyConcreteAggregate();
		Mca.addObject("One");
		Mca.addObject("Two");
		Mca.addObject("Three");
		MyIterator Miterator = Mca.createIterator();
		// use my own iterator to traverse the objects added in the collection
		while (Miterator.hasNext()) {
			System.out.println(Miterator.getCurrentObject());
			Miterator.Next(); // it is different from the iterator in JDK
			// the next() in iterator of JDK has combined the 2 line code above
		}
 	}
}
