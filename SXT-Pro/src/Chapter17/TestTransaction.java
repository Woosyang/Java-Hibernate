package Chapter17;
import java.sql.*;

/**
 * Test Transaction
 * @author Woo
 *
 */
/*
 * transaction : a set of SQL statement that either execute successfully or fail at the
 * 				 same time, and it is a unit of work that is performed against a 
 * 				 database. Also it is the propagation of one or more changes to the 
 * 				 database.
 * transaction starts when you executing the DML statement(Insert, Update, Delete) after
 * connecting to the database or when the previous transaction is finished and then 
 * execute a new DML statement.
 * Also it ends when 1. you use RollBack or Commit 2. you execute a DDL statement such 
 * as CREATE TABLE, in this case, it will execute the Commit automatically 3. you execute
 * DCL statement such as GRANT, it will execute the Commit automatically 4. you close the
 * connection with database 5. you execute a DML statement but it fails in the transaction,
 * in this case this failed DML statement will execute the RollBack
 */
/*
 * four feature of the transaction(ACID):
   Atomicity: ensures that all operations within the work unit are completed successfully. 
 			  Otherwise, the transaction is aborted at the point of failure and all the 
 			  previous operations are rolled back to their former state.

   Consistency: ensures that the database properly changes states upon a successfully 
   				committed transaction.

   Isolation: enables transactions to operate independently of and transparent to each other.

   Durability: ensures that the result or effect of a committed transaction persists in 
   			   case of a system failure.
 */
public class TestTransaction {
	public static void main(String[] args) {
		Connection Con = null;
		PreparedStatement Ps01 = null;
		PreparedStatement Ps02 = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc", "root", "root");
			// Con.setAutoCommit(true); // in JDBC the default value for the commit is true
			// explain the transaction
			String Sql = "insert into user (username, pwd) values (?, ?)"; // ? -> place holder
			Ps01 = Con.prepareStatement(Sql);
			Ps01.setObject(1, "Sql+");
			Ps01.setObject(2, 12456);
			Ps01.execute();
			System.out.println("insert an user");
			Thread.sleep(3000);
			// make the mistake to illustrate the transaction, 3 "?"
			// Sql = "insert into user (username, pwd) values (?, ?, ?)";
			// if the mistake happens, inserting 2 additional data by Ps01 and Ps02
			// will fail because the Ps01 and Ps02 are in the same transaction
			// this two statements either commit together successfully or not
			Ps02 = Con.prepareStatement(Sql);
			Ps02.setObject(1, "Sql-");
			Ps02.setObject(2, 123456);
			Ps02.execute();
			System.out.println("insert an user");
			// Con.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				Con.rollback();
			} catch (SQLException a) {
				a.printStackTrace();
			}
		} finally {
			if (Ps02 != null) {
				try {
					Ps02.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (Ps01 != null) {
				try {
					Ps01.close();
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
