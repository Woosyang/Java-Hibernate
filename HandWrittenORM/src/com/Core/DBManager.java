package com.Core;
import java.io.*;
import java.sql.*;
import java.util.*;
import com.Bean.Configuration;
import com.Pool.DBConnectionPool;

/**
 * Database Manager, maintain the management of the connection between database and object.
 * (Has connection pool)
 * @author Woo
 *
 */
public class DBManager {
	/**
	 * information of the configuration file
	 */
	private static Configuration conf;
	// used with createConnection()
	/**
	 * connection pool object
	 */
	private static DBConnectionPool pool; // pool will call the DBManger, don't initialize here
	static { // static block, only be executed one time
		Properties pros = new Properties();
		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("DB.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		conf = new Configuration();
		conf.setDataBase(pros.getProperty("DataBase"));
		conf.setDriver(pros.getProperty("Driver"));
		conf.setURL(pros.getProperty("URL"));
		conf.setUser(pros.getProperty("User"));
		conf.setPassWord(pros.getProperty("PassWord"));
		conf.setSrcPath(pros.getProperty("SrcPath"));
		conf.setPoPackage(pros.getProperty("PoPackage"));
		conf.setQueryClass(pros.getProperty("QueryClass"));
		conf.setPoolMaxSize(Integer.parseInt(pros.getProperty("PoolMaxSize")));
		conf.setPoolMinSize(Integer.parseInt(pros.getProperty("PoolMinSize")));
		// load the TableContext class to get the connection of table in database and class in java
		// generate the java bean classes in Po package
		System.out.println(TableContext.class);
	}
	
	/**
	 * get the object of the connection with database
	 * @return connection
	 */
	public static Connection getConnection() {
		if (pool == null) {
			pool = new DBConnectionPool();
		}
		return pool.getConnection();
	}
	
	/*// original 
	public static Connection getConnection() {
		try {
			Class.forName(conf.getDriver());
			// create the connection directly, later we add the connection pool to increase the efficiency
			return DriverManager.getConnection(conf.getURL(), conf.getUser(), conf.getPassWord());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	*/
	
	/**
	 * create a new Connection object with Connection pool
	 * @return
	 */
	public static Connection createConnection() {
		try {
			Class.forName(conf.getDriver());
			// create the connection directly, later we add the connection pool to increase the efficiency
			return DriverManager.getConnection(conf.getURL(), conf.getUser(), conf.getPassWord());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * close the passed object(Connection, PreparedStatement, ResultSet)
	 * @param T Rs, Ps, Conn
	 */
	public static void Close(AutoCloseable ...T) {
		// used with connection pool
		for (AutoCloseable t : T) {
			if (t instanceof Connection) {
				pool.close((Connection) t);
			} else {
				try {
					t.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/* // original Close() 
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
	*/
	
	/**
	 * return a Configuration object
	 * @return
	 */
	public static Configuration getConfiguration() {
		return conf;
	}
}
