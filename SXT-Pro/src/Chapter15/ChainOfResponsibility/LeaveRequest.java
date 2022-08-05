package Chapter15.ChainOfResponsibility;

/**
 * Encapsulate All The Information Of The Leave Request Form
 * Example Of Chain Of Responsibility Pattern
 * @author Woo
 *
 */
public class LeaveRequest {
	private String Name;
	private int AbsentDays;
	private String Reason;
	
	public LeaveRequest(String name, int absentDays, String reason) {
		super();
		Name = name;
		AbsentDays = absentDays;
		Reason = reason;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAbsentDays() {
		return AbsentDays;
	}

	public void setAbsentDays(int absentDays) {
		AbsentDays = absentDays;
	}

	public String getReason() {
		return Reason;
	}

	public void setReason(String reason) {
		Reason = reason;
	}
}
