package Chapter17;
import java.sql.*;
import java.util.*;

/**
 * ORM: Object Relational Mapping
 * The whole table refers to a class, each column in the table refers to each field in
 * the the object realizing the class, each row of the table refers to each object 
 * realizing the class.
 * Used With Employee, Department
 * Used a JavaBean Object to store one or multiple rows in the table
 * @author Woo
 *
 */
public class TestORM03 {
	// use one JavaBean Object to store one row in the table
	public static void Test01() {
		Connection Con = null;
		PreparedStatement Ps = null;
		ResultSet Rs = null;
		Employee Emp = null;
		try {
			Con = JDBCUtil.getMySqlConnection02();
			Ps = Con.prepareStatement("select Name, Salary, Age from Employee where ID = ?");
			Ps.setObject(1, 1); // first row of the table
			Rs = Ps.executeQuery();
			while (Rs.next()) {
				Emp = new Employee(Rs.getString(1), Rs.getDouble(2), Rs.getInt(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.Close(Rs, Ps, Con);
		}
		// print the data directly without traverse
		System.out.println(Emp.getName() + ", " + Emp.getSalary() + ", " + Emp.getAge());
	}
	
	// use list of java bean to store multiple rows in the table
	public static void Test02() {
		Connection Con = null;
		PreparedStatement Ps = null;
		ResultSet Rs = null;
		Employee Emp = null;
		// use a whole map to store one row in the table
		List<Employee> List = new ArrayList<>();
		try {
			Con = JDBCUtil.getMySqlConnection02();
			Ps = Con.prepareStatement("select Name, Salary, Age from Employee where ID > ?");
			Ps.setObject(1, 1); // first row of the table
			Rs = Ps.executeQuery();
			while (Rs.next()) {
				Emp = new Employee(Rs.getString(1), Rs.getDouble(2), Rs.getInt(3));
				List.add(Emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.Close(Rs, Ps, Con);
		}
		// traverse the List<Employee>
		for (Employee emp : List) {
			System.out.println(emp.getName() + ", " + emp.getSalary() + ", " + emp.getAge());
		}
	}
	
	public static void main(String[] args) {
		// Test01(); // use one java bean object to store one row of the table
		Test02();
	}
}
