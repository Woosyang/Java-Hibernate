package Chapter15.Mediator;

/**
 * Example Of Mediator Pattern
 * Used With Mediator
 * @author Woo
 *
 */
// abstract, colleague class
public interface Department {
	public void ownAction(); // the own job of this department 
	// the request from this department to let the mediator to handle
	public void requestAction();
}

// concrete colleague class
// concrete colleague and concrete mediator both have the reference of each other
// so that they can the method from each other mutually
// each concrete colleague only knows its own job and does not know others'
// but they all know the mediator
class ScienceDepartment implements Department {
	private Mediator M; // has the reference of the mediator
	private String Name;
	public ScienceDepartment(Mediator m, String name) {
		super();
		M = m;
		Name = name;
		M.Register(Name, this);
	}
	
	@Override
	public void ownAction() {
		System.out.println(this.Name + ": Focus on the research...");
	}
	
	// the request left to mediator to handle
	@Override
	public void requestAction() {
		System.out.println(this.Name + " is lack of money, needs financial support...");
		// add corresponding function from mediator
		M.Order("FundingDepartment");
	}
}

class FundingDepartment implements Department {
	private Mediator M; // has the reference of the mediator
	private String Name;
	public FundingDepartment(Mediator m, String name) {
		super();
		M = m;
		Name = name;
		M.Register(Name, this);
	}
	
	@Override
	public void ownAction() {
		System.out.println(this.Name + ": Focus on the investment...");
	}
	
	// the request left to mediator to handle
	@Override
	public void requestAction() {
		System.out.println("Please let the science and marketing departments focus on their works...");
		// add corresponding function from mediator
		// code line
	}
}

class MarketingDepartment implements Department {
	private Mediator M; // has the reference of the mediator
	private String Name;
	public MarketingDepartment(Mediator m, String name) {
		super();
		M = m;
		Name = name;
		M.Register(Name, this);
	}
	
	@Override
	public void ownAction() {
		System.out.println("Focus on the project bidding...");
	}
	
	// the request left to mediator to handle
	@Override
	public void requestAction() {
		System.out.println(this.Name + " needs money to nail down the recent project...");
		// add corresponding function from mediator
		// code line
	}
}

