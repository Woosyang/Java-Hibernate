package Chapter17;
import java.sql.*;

/**
 * Test The Usage Of PreparedStatement
 * @author Woo
 *
 */
public class TestPreparedStatement {
	public static void main(String[] args) {
		Connection Con = null;
		PreparedStatement Ps = null;
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// create the connection
			Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
			// execute the SQL
			String Sql = "insert into user (username, pwd, regTime, age) values (?, ?, ?, ?)"; // ? -> place holder
			// the SQL statement above prevent the injection of the SQL
			Ps = Con.prepareStatement(Sql);
			// in JDBC, the index starts from 1 not 0
			
			// you can also use Ps.setObject() to replace the code below
			Ps.setString(1, "Sola"); // 1 -> first "?", setObject()
			Ps.setInt(2, 123456); // setObject()
			// Date is in the java.sql.* not java.util.*
			Ps.setDate(3, new java.sql.Date(System.currentTimeMillis())); // setObject()
			Ps.setInt(4, 18); // setObject()
			System.out.println("insert a sql statement");
			// execute() -> true, if there is a result set, false, if there is no result set
			// Ps.execute(); // execute the SQL statement, it is a boolean type
			int Count = Ps.executeUpdate(); // count how many SQL operations(insert, update, delete) happened
			System.out.println(Count); // 1
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// closing order is similar with I/O Stream
			if (Ps != null) {
				try {
					Ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (Con != null) {
				try {
					Con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
