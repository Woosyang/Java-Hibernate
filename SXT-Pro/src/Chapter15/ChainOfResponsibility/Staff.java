package Chapter15.ChainOfResponsibility;

/**
 * Used With LeaveRequest
 * Example Of Chain Of Responsibility Pattern
 * Person who approves the leave request
 * @author Woo
 *
 */
// each role of the chain of responsibility has the almost same logic
// the relationship of each role will be made as a chain in the client part
// client can set the relationship with each role 
public abstract class Staff {
	protected String Name; // can be used in its child class
	protected Staff NextStaff; // like linked list, and that's why it called as COR
	// constructor
	public Staff(String name) {
		super();
		Name = name;
	}
	// set the next role in the chain of responsibility
	public Staff getNextStaff() {
		return NextStaff;
	}
	
	public void setNextStaff(Staff nextStaff) {
		NextStaff = nextStaff;
	}
	
	// handle the leave request, main function
	public abstract void handleRequest(LeaveRequest request);
}

class TeachingAssistant extends Staff {
	public TeachingAssistant(String name) {
		super(name);
	}
	
	// only can handle the case that absent days are less than 3 days
	@Override
	public void handleRequest(LeaveRequest request) {
		if (request.getAbsentDays() < 3) {
			System.out.println("Student: " + request.getName());
			System.out.println("AbsentDays: " + request.getAbsentDays());
			System.out.println("Reason: " + request.getReason());
			System.out.println("TA " + this.Name + " Approved It.");
		} else { // doesn't have the authority, render it to its upper level
			if (this.NextStaff != null) {
				this.NextStaff.handleRequest(request);
			}
		}
	}
}

class Professor extends Staff {

	public Professor(String name) {
		super(name);
	}
	
	// only can handle the case that absent days are between 3 to 10 days
	@Override
	public void handleRequest(LeaveRequest request) {
		if (request.getAbsentDays() < 10 && request.getAbsentDays() > 3) {
			System.out.println("Student: " + request.getName());
			System.out.println("AbsentDays: " + request.getAbsentDays());
			System.out.println("Reason: " + request.getReason());
			System.out.println("Pro " + this.Name + " Approved It.");
		} else { // doesn't have the authority, render it to its upper level
			if (this.NextStaff != null) {
				this.NextStaff.handleRequest(request);
			}
		}
	}
}

// can add other class to handle the request, it is very flexible
class Dean extends Staff {
	public Dean(String name) {
		super(name);
	}
	
	// only can handle the case that absent days are between 10 to 20 days
	@Override
	public void handleRequest(LeaveRequest request) {
		if (request.getAbsentDays() < 20 && request.getAbsentDays() >= 10) {
			System.out.println("Student: " + request.getName());
			System.out.println("AbsentDays: " + request.getAbsentDays());
			System.out.println("Reason: " + request.getReason());
			System.out.println("Dean " + this.Name + " Approved It.");
		} else { // doesn't have the authority, render it to its upper level
			this.NextStaff.handleRequest(request);
		}
	}
}

class HeadMaster extends Staff {

	public HeadMaster(String name) {
		super(name);
	}
	
	// only can handle the case that absent days are greater than 20 days
	@Override
	public void handleRequest(LeaveRequest request) {
		if (request.getAbsentDays() >= 20) {
			System.out.println("Student: " + request.getName());
			System.out.println("AbsentDays: " + request.getAbsentDays());
			System.out.println("Reason: " + request.getReason());
			System.out.println("HeadMaster " + this.Name + " Approved It.");
		} else { // doesn't have the authority, render it to its upper level
			System.out.println("Request Rejected !!!");
		}
	}
}