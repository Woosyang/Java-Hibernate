package Chapter15.Iterator;
import java.util.*;

/**
 * Example Of The Iterator Pattern
 * Aggregate, concrete aggregate has a collections inside
 * @author Woo
 *
 */
// Abstract aggregate
public interface MyAggregate {
	public MyIterator createIterator();
}

// concrete
class MyConcreteAggregate implements MyAggregate {
	private List<Object> List = new ArrayList<>();
	
	public MyConcreteAggregate() {} // no need to make the List as the parameter
	
	// add an element
	public void addObject(Object Obj) {
		this.List.add(Obj);
	}
	
	// remove an element
	public void removeObject(Object Obj) {
		this.List.remove(Obj);
	}
	
	public List<Object> getList() {
		return List;
	}

	public void setList(List<Object> list) {
		List = list;
	}

	// use a inner class to define an iterator and the inner class can directly get
	// access to the fields of the outer(List)
	// implement the MyIterator interface
	private class MyConcreteIterator implements MyIterator {
		int Cursor = 0; // mark the place when traversing 
		@Override
		public void First() {
			Cursor = 0;
		}

		@Override
		public void Next() { // a little bit different from the next() of the iterator(JDK)
			if (Cursor < List.size()) {
				Cursor ++; // make sure the list will not be overflowed
			}
		}

		@Override
		public boolean hasNext() {
			// not (Cursor < List.size() - 1), careful
			return (Cursor < List.size()); // whether the cursor points to the last
		}

		@Override
		public boolean isFirst() {
			return (Cursor == 0);
		}

		@Override
		public boolean isLast() {
			return (Cursor == List.size() - 1);
		}

		@Override
		public Object getCurrentObject() {
			return List.get(Cursor);
		}
	}
	
	@Override
	public MyIterator createIterator() {
		return new MyConcreteIterator();
	}	
}