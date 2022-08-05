package com.Pool;
import java.sql.*;
import java.util.*;
import com.Core.DBManager;

/**
 * class of the connection pool for JDBC
 * @author Woo
 *
 */
public class DBConnectionPool {
	/**
	 * connection pool object
	 */
	private List<Connection> pool;
	
	/**
	 * max capacity of the connection pool
	 */
	private static final int POOL_MAX_SIZE = DBManager.getConfiguration().getPoolMaxSize(); 
	
	/**
	 * min capacity of the connection pool
	 */
	private static final int POOL_MIN_SIZE = DBManager.getConfiguration().getPoolMinSize();
	
	/**
	 * initializing the connection pool and make its capacity become to min_size
	 */
	public void intiPool() {
		if (pool == null) {
			pool = new ArrayList<>();
		} 
		while (pool.size() < DBConnectionPool.POOL_MIN_SIZE) {
			pool.add(DBManager.createConnection());
			System.out.println("initializing, the capacity of the connection pool is: " + pool.size());
		}
	}
	
	/**
	 * get the connection(object, top one in the pool) from the connection pool
	 * @return connection object
	 */
	public synchronized Connection getConnection() {
		int last_index = pool.size() - 1;
		Connection con = pool.get(last_index);
		// prevent the connection conflicts, because two methods may use the 
		// connection at the same time
		pool.remove(last_index); 
		return con;
	}
	
	/**
	 * put the connection object back to the connection pool
	 * it is not really close()
	 * @param con, connection object
	 */
	public synchronized void close(Connection con) {
		if (pool.size() > POOL_MAX_SIZE) {
			DBManager.Close(con);
		} else {
			pool.add(con); // put back
		}
	}
	
	public DBConnectionPool() {
		intiPool();
	}
}
