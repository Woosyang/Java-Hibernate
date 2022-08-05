package Chapter17;
import java.sql.*;

/**
 * Test The Usage Of ResultSet
 * @author Woo
 *
 */
public class TestResultSet {
	public static void main(String[] args) {
		Connection Con = null;
		PreparedStatement Ps = null;
		ResultSet Rs = null;
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// create the connection
			Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
			String Sql = "select id, username, pwd from user where id > ?"; // ? -> place holder
			Ps = Con.prepareStatement(Sql);
			Ps.setInt(1, 5); // record the row whose id > 5
			// executeQuery() execute the select SQL statement and returns the result set
			Rs = Ps.executeQuery();
			// Rs.next() -> Moves the cursor forward one row from its current position
			while (Rs.next()) {
				System.out.println(Rs.getInt(1) + "----" + Rs.getString(2)+ "----" + Rs.getInt(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closing order: ResultSet -> PreparedStatement -> Connection
			// must to use three try-catch block to close them separately
			if (Rs != null) {
				try {
					Con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
