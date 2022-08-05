package Chapter17;
import java.sql.*;

/**
 * Test Batch With Statement
 * @author Woo
 *
 */
public class TestBatch {
	public static void main(String[] args) {
		Connection Con = null;
		Statement Stmt = null;
		ResultSet Rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "root");
			// commit the transaction manually
			Con.setAutoCommit(false);
			long Start = System.currentTimeMillis(); // test the performance of the batch processing
			Stmt = Con.createStatement();
			// create 20000 rows of the data
			for (int i = 0; i < 20000; i ++) {
				Stmt.addBatch("insert into user (username, pwd, regtime) values ('Sql"+ i + "', 123456, now())");
			}
			Stmt.executeBatch();
			Con.commit();
			long End = System.currentTimeMillis();
			System.out.println("inserting 20000 rows data costs " + (End - Start) + "ms");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (Rs != null) {
				try {
					Rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
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
