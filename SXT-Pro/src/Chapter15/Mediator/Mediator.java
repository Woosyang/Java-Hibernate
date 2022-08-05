package Chapter15.Mediator;
import java.util.*;

/**
 * Example Of Mediator Pattern
 * If the connection between objects in a system is presented as a network structure, 
 * there are a large number of many-to-many relationships between objects, which will 
 * lead to extremely complex relationships. These objects are called "colleague objects".
 * We can introduce a mediator object to let every "colleague object" only interact
 * with it in order to reduce the complexity.  
 * @author Woo
 *
 */
// abstract, define a connection between Mediator and Colleague  
public interface Mediator {
	public void Register(String Name, Department D); // connection
	public void Order(String Name); // ask the specific department to its job
}

// concrete mediator, realize all the abstract method from mediator
// it needs to have all of the information of all of the concrete mediator
// then it can send the order to a specific concrete colleague according to the request
// from corresponding concrete colleague 
class Chief implements Mediator {
	// all the information of all the concrete colleague 
	private Map<String, Department> M = new HashMap<>();
	
	@Override
	public void Register(String Name, Department D) {
		M.put(Name, D);
	}
	
	// it varies according to the concrete colleague's request
	/* 
	 * eg, ScienceDepartment needs the help of FundingDepartment
	 * add M.Order("FundingDepartment") in the requestAction() of ScienceDepartment
	 * Mediator and Colleague calls each other mutually
	 */
	@Override
	public void Order(String Name) {
		// it can have multiple operations according to the parameter Name
		// those operations depend on the concrete colleague
		M.get(Name).ownAction(); 
		// M.get(Name).requestAction();
	}	
}