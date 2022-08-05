package Chapter17;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * helper class for JDBC connection
 * @author Woo
 *
 */
public class JDBCUtil {
	// Properties can help to read and process the information inside properties file
	static Properties Pros = null;
	// it is called when loading JDBCUtil class
	static {
		Pros = new Properties();
		try {
			// properties file must in the Src folder
			Pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("DB.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// assume the port number of database is 3306, static block
	public static Connection getMySqlConnection01(String Database, String Account, String Password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + Database, Account, Password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// assume use properties file and the parameter name in the properties file is settled
	// if you want to use other company's database, you can just modify the name inside
	// getProperty() corresponding to the name defined in properties file(Oracle)
	public static Connection getMySqlConnection02() {
		try {
			Class.forName(Pros.getProperty("MySqlDriver"));
			return DriverManager.getConnection(Pros.getProperty("MySqlURL"), 
											   Pros.getProperty("MySqlUser"),
											   Pros.getProperty("MySqlPassWord"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	// close the resource of the JDBC
	public static void Close(AutoCloseable ...T) {
		for (AutoCloseable t : T) {
			if (t != null) {
				try {
					t.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
