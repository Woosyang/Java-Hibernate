package Chapter15.Composite;

/**
 * The Common Structure For Composite Pattern
 * Used in the scenario where the relationship between the part(object) and the whole 
 * (a set of objects) is represented by a tree structure. And client can use the same 
 * way to deal with the part and the whole.
 * @author Woo
 *
 */
/*
 * Composite is used where we need to treat a group of objects in similar way as 
 * a single object. Composite composes objects in term of a tree structure to 
 * represent part as well as whole hierarchy. This type of design pattern comes under 
 * structural pattern as this pattern creates a tree structure of group of objects.
 * This pattern creates a class that contains group of its own objects. This class 
 * provides ways to modify its group of same objects.
 * 
 */
// Component: define the common feature that Leaf and Composite both have
public interface Component {
	public void Operation();
}

// Leaf: implements all Component methods, does not have any child node
interface Leaf extends Component {
	public void Operation();
}

// Composite: has a collection, can have child node
// implements methods to manipulate children
// implements all Component methods by delegating them to its children
interface Composite extends Component {
	// public List<Leaf> L;
	public void Add(Component C); // manage the children
	public void Operation();
	public void Remove(Component C); // manage the children
	public Component getChild(int Index); // manage the children
}