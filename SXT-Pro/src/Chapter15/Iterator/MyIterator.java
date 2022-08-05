package Chapter15.Iterator;

/**
 * Example Of The Iterator Pattern
 * Abstract iterator, is defined as an interface to get the first, next element or to 
 * determine whether the cursor is pointing at the last element or the current element
 * Implemented by MyConcreteIterator
 * @author Woo
 *
 */
// implemented by MyConcreteIterator as a inner class in the class MyConcreteAggregate
public interface MyIterator {
	public void First(); // make the cursor point to the first element
	public void Next(); // make the cursor point to the next element
	public boolean hasNext(); // determine whether the iterator has elements left
	boolean isFirst();
	boolean isLast();
	Object getCurrentObject(); // return the current object pointed by cursor
}
