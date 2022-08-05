package Chapter15.Mediator;

/**
 * Test The Mediator Pattern
 * Decouple the relationship between multiple colleague objects. Each object holds a 
 * reference to the mediator object and only deals with it(mediator object). 
 * This pattern can manage these interactions through the mediator object
 * @author Woo
 *
 */
/*
 * 
 * Mediator pattern is used to reduce communication complexity between multiple objects 
 * or classes. This pattern provides a mediator class which normally handles all the 
 * communications between different classes and supports easy maintenance of the code 
 * by loose coupling. 
 * Mediator pattern falls under behavioral pattern category.
 * 
 */
public class MediatorTest {
	public static void main(String[] args) {
		Mediator C = new Chief();
		Department S = new ScienceDepartment(C, "ScienceDepartment");
		Department F = new FundingDepartment(C, "FundingDepartment");
		Department M = new MarketingDepartment(C, "MarketingDepartment");
		// the job of the Science Department
		S.ownAction();
		// let the Science Department interact with Funding Department
		// made by the Mediator C
		// progress: ScienceDepartment -> Cheif -> FundingDepartment
		// not ScienceDepartment -> FundingDepartment directly
		S.requestAction(); // the job of the Funding Department will come out
	}
}
