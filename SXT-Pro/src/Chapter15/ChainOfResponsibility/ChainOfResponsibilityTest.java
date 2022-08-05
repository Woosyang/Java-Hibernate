package Chapter15.ChainOfResponsibility;

/**
 * Test The Chain Of Responsibility Pattern
 * Chain Of Responsibility can replace the code with too much if-else statements
 * Make a set of objects which can handle the same request as a chain, the request
 * (object needs to be handled) will be passed along the chain. Each role in the
 * chain will handle the request if it can, if it can't it will pass the request to
 * its next.
 * We can also use other collections to generate the chain, encapsulate a chain object
 * by using array, array list, set...
 * @author Woo
 *
 */
/*
 * The chain of responsibility pattern creates a chain of receiver objects for a 
 * request. This pattern decouples sender and receiver of a request based on type of 
 * request. This pattern comes under behavioral patterns.
 * In this pattern, normally each receiver contains reference to another receiver. 
 * If one object cannot handle the request then it passes the same to the next receiver
 * and so on.
 * 
 */
public class ChainOfResponsibilityTest {
	public static void main(String[] args) {
		Staff A = new TeachingAssistant("Tom");
		Staff B = new Professor("Ryan");
		Staff C = new Dean("Zu");
		Staff D = new HeadMaster("Steve");
		
		// set the relationship with each role, like the linked list
		// can encapsulate a chain class to set up the relationship
		// use array, array list...
		A.NextStaff = B; // you can use setNextStaff()
		B.NextStaff = C;
		C.NextStaff = D;
		
		// the object needs to be handled
		LeaveRequest Lf = new LeaveRequest("James", 20, "Corona Virus");
		// starts with Staff A
		A.handleRequest(Lf); // Steve will handle
	}
}
