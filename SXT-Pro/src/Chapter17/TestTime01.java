package Chapter17;
import java.sql.*;
import java.util.Random;

/**
 * Test The Time(java.sql.Date, Time, Timestamp)
 * @author Woo
 *
 */
public class TestTime01 {
	public static void main(String[] args) {
		Connection Con = null;
		PreparedStatement Ps = null;
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// create the connection
			Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "root");
			// Ps01 = Con.prepareStatement("insert into user (username, pwd, regtime) values (?, ?, ?)");
			// the code above needs to be add more column to make the test
			Ps = Con.prepareStatement("insert into user (username, pwd, regtime, latestOnline) values (?, ?, ?, ?)");
			Ps.setObject(1, "user01");
			Ps.setObject(2, "54321");
			// if you want to insert a specific date, you can use Calendar or DateFormat
			
			// includes minutes and seconds, use Timestamp and use setTimestamp() to set the parameter  
			Timestamp Stamp = new Timestamp(System.currentTimeMillis());
			Ps.setTimestamp(3, Stamp);
			
			// current time, use Date and use setDate() to set the parameter
			Date D = new java.sql.Date(System.currentTimeMillis()); // must be distinguished with java.util.Date
			Ps.setDate(4, D);
			Ps.execute();
			System.out.println("insert an user...");
			// insert 1000 rows in the table user
			for (int i = 0; i < 1000; i ++) {
				Ps = Con.prepareStatement("insert into user (username, pwd, regtime, latestOnline) values (?, ?, ?, ?)");
				Ps.setObject(1, "user0" + (i + 1));
				Ps.setObject(2, "5432" + 9 * Math.random());
				int Rd = 100000000 + new Random().nextInt(1000000000); // generate a random number
				Stamp = new Timestamp(System.currentTimeMillis() - Rd);
				D = new java.sql.Date((long) (System.currentTimeMillis() - Rd * Math.random()));
				Ps.setTimestamp(3, Stamp);
				Ps.setDate(4, D);
				Ps.execute();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
