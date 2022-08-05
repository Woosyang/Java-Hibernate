package Chapter17;
import java.sql.*;

/**
 * Test The Connection With The Database
 * @author Woo
 *
 */
public class TestConnection {
	public static void main(String[] args) {
		Connection Con = null;
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// establish the connection with the data base
			// creating the connection is not efficient, takes time because
			// the Connection object actually contains the Socket Object, it is a
			// remote connection
			
			long Start = System.currentTimeMillis();
			
			// DriverManager is for managing JDBC, acting between the user and the driver
			// Also it tracks the available driver application and makes the connection
			// between the database and the driver
			
			//                                               getConnection() needs
			Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "root");
			
			// Connection connects to the specific database and execute SQL statements 
			// in the connection context and return the results
			// Also getConnction() is based on the connection defined in the URL of JDBC 
			long End = System.currentTimeMillis();
			
			System.out.println(Con);
			System.out.println("Creating the connection costs " + (End - Start) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// needs to close the connection
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
