package Chapter09;

/**
 * Create a node for the linked list
 * @author Woo
 *
 */

public class Node {
	Node Previous; // forward node null
	Object Element; // stored data
	Node Next; // backward node null
	
	public Node(Node Pre, Node Nex, Object Ele) {
		this.Previous = Pre;
		this.Next = Nex;
		this.Element = Ele;
	}
	
	public Node(Object Ele) {
		this.Element = Ele;
	}
}
