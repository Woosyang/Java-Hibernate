package Chapter17;
import java.sql.*;

/**
 * Test The SQL Execute Statement and SQL Injection
 * @author Woo
 *
 */
public class TestStatement {
	// Statement is used to execute static SQL statements and returns the object
	// it generated as the result
	/* There are 3 types of the Statement
	 * 1. Statement: created by createStatement(), used to send simple SQL 
	 * 	  statement.(Without parameters)
	 * 
	 * 2. PreparedStatement: inherited from the statement interface, created
	 * 	  by preparedStatement(), used to send the SQL statement with one or
	 * 	  multiple parameters, PreparedStatement is more efficient than Statement
	 *    and it can prevent the SQL injection
	 * 
	 * 3. CallableStatement: inherited from PreparedStatement, created by 
	 * 	  prePareCall, used to call the process of storing
	 */
	public static void main(String[] args) {
		Connection Con = null;
		Statement Stmt = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
			// 1. Statement 
			Stmt = Con.createStatement();
			// String Sql = "insert into user (username, pwd, regTime, age) values ('Dola', 567, now(), 20)";
				// another format to write the statement 
				/*	
					String Name = "Wosa";
					Sql = "insert into user (username, pwd, regTime, age) values ('"+Name+"', 567, now(), 20)";
				*/
			// Stmt.execute(Sql);
			
			// test the injection of SQL
			String Sql = "delete from user where id = 4";
			// another format to write the SQL
			/*  // using Statement to write in this way will let the database 
			    // have errors -> malicious injection
				String ID = "5 or 1=1";
				Sql = "delete from user where id = " + ID;
			*/
			Stmt.execute(Sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// closing order is similar with I/O Stream
			// first open close in the last
			if (Stmt != null) {
				try {
					Stmt.close();
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
