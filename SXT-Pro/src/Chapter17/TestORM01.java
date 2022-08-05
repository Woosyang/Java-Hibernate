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
 * Test to use Object[] to encapsulate a row in the table
 * Use List<Object[]> to store multiple rows in the table
 * @author Woo
 *
 */
public class TestORM01 {
	public static void main(String[] args) {
		Connection Con = null;
		PreparedStatement Ps = null;
		ResultSet Rs = null;
		// Object[] Objs = null; // only can store one row data in the table
		List<Object[]> List = new ArrayList<>();
		try {
			Con = JDBCUtil.getMySqlConnection02();
			// Ps = Con.prepareStatement("select Name, Salary, Age from Employee where ID = ?");
			Ps = Con.prepareStatement("select Name, Salary, Age from Employee where ID > ?");
			Ps.setObject(1, 1); // ID = 1
			Rs = Ps.executeQuery();
			// Objs = new Object[3]; // store the information of one certain row in the table
			while (Rs.next()) {
				//                         Name                    Salary                   Age   
				// System.out.println(Rs.getString(1) + ", " + Rs.getDouble(2) + ", " + Rs.getInt(3));
				Object[] Objs = new Object[3];
				Objs[0] = Rs.getObject(1);
				Objs[1] = Rs.getObject(2);
				Objs[2] = Rs.getObject(3);
				List.add(Objs); // store multiple rows in the table
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.Close(Rs, Ps, Con);
		}
		
		// even the connection with the database is closed, we still got the data
		// by using an array to store it
		// System.out.println(Objs[0] + ", " + Objs[1] + ", " + Objs[2]);
		
		// traverse each row in the table <-> traverse the List
		for (Object[] O : List) {
			System.out.println(O[0] + ", " + O[1] + ", " + O[2]);
		}
	}
}
