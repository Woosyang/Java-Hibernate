package Chapter17;
import java.sql.*;
import java.util.*;

/**
 * ORM: Object Relational Mapping
 * The whole table refers to a class, each column in the table refers to each field in
 * the the object realizing the class, each row of the table refers to each object 
 * realizing the class.
 * Used With JDBCUtil.java
 * Used With DB.properties
 * Use List<Map> to store multiple rows in the table, List<List> also can do
 * Use Map<Map> to store multiple rows in the table 
 * @author Woo
 *
 */
public class TestORM02 {
	
	// use Map to store one row of the table
	public static void Test01() {
		Connection Con = null;
		PreparedStatement Ps = null;
		ResultSet Rs = null;
		// use a whole map to store one row in the table
		Map <String, Object> Row = new HashMap<>();
		try {
			Con = JDBCUtil.getMySqlConnection02();
			Ps = Con.prepareStatement("select Name, Salary, Age from Employee where ID = ?");
			Ps.setObject(1, 1); // first row of the table
			Rs = Ps.executeQuery();
			while (Rs.next()) {
				Row.put("EmpName", Rs.getObject(1));
				Row.put("Salary", Rs.getObject(2));
				Row.put("Age", Rs.getObject(3));			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.Close(Rs, Ps, Con);
		}
		// traverse the map to get the data
		for (String Key : Row.keySet()) {
			System.out.print(Key + "-->" + Row.get(Key) + " ");
		}
	}
	
	// use List<Map> to store multiple rows of the table
	public static void Test02() {
		Connection Con = null;
		PreparedStatement Ps = null;
		ResultSet Rs = null;
		// use a whole map to store one row in the table
		// Map <String, Object> Row = new HashMap<>();
		List<Map<String, Object>> List = new ArrayList<>();
		try {
			Con = JDBCUtil.getMySqlConnection02();
			Ps = Con.prepareStatement("select Name, Salary, Age from Employee where ID > ?");
			Ps.setObject(1, 1); // first row of the table
			Rs = Ps.executeQuery();
			while (Rs.next()) {
				Map<String, Object> Row = new HashMap<>();
				Row.put("EmpName", Rs.getObject(1));
				Row.put("Salary", Rs.getObject(2));
				Row.put("Age", Rs.getObject(3));
				List.add(Row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.Close(Rs, Ps, Con);
		}
		// traverse the map to get the data
		for (Map<String, Object> M : List) {
			for (String Key : M.keySet()) {
				System.out.print(Key + "-->" + M.get(Key) + " ");
			}
			System.out.println();
		}
	}
	
	public static void Test03() {
		Connection Con = null;
		PreparedStatement Ps = null;
		ResultSet Rs = null;
		// use a whole map to store one row in the table
		// Map <String, Object> Row = new HashMap<>();
		Map<String, Map<String, Object>> Map = new HashMap<>();	
		try {
			Con = JDBCUtil.getMySqlConnection02();
			Ps = Con.prepareStatement("select Name, Salary, Age from Employee where ID < ?");
			Ps.setObject(1, 3); // first row of the table
			Rs = Ps.executeQuery();
			while (Rs.next()) {
				// do not mention this variable as member variable if you want to traverse
				Map<String, Object> Row = new HashMap<>(); 
				Row.put("EmpName", Rs.getObject(1));
				Row.put("Salary", Rs.getObject(2));
				Row.put("Age", Rs.getObject(3));
				// use "Name" as the primary key of the Map
				// each Employee's information refers to one row in the Map
				// System.out.println(Rs.getString(1));
				Map.put(Rs.getString(1), Row); // each row in the Map maps to the Row Map
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.Close(Rs, Ps, Con);
		}
		// traverse the map to get the data
		for (String EmpName : Map.keySet()) {
			Map<String, Object> Row = Map.get(EmpName);
			for (String Key : Row.keySet()) {
				System.out.print(Key + "-->" + Row.get(Key) + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		// Test01(); // one row of the table using Map<String, Object>
		// Test02(); // multiple rows of the table using List<Map<String, Object>>
		Test03();
	}
}
