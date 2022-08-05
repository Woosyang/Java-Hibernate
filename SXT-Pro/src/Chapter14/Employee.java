package Chapter14;

/**
 * Used With TestJavaAssist.java
 * @author Woo
 *
 */
@Author(Name="Chiba", Year = 2005)
public class Employee {
	private int EmpNo;
	private String Name;
	
	public int getEmpNo() {
		return EmpNo;
	}
	public void setEmpNo(int empNo) {
		EmpNo = empNo;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public Employee(int empNo, String name) {
		super();
		EmpNo = empNo;
		Name = name;
	}
	
	public void SayHello(int a) {
		System.out.println("Say Hi: " + a);
	}
	public Employee() {}
}
