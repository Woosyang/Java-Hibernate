package com.Core;
import java.lang.reflect.*;

/**
 * QueryFactory class, it is used to create query object based on configuration files
 * @author Woo
 *
 */
// need to new a Query class
@SuppressWarnings("all")
public class QueryFactory {
	// use singleton
	private static QueryFactory factory = new QueryFactory();
	private static Class c; // only be loaded one time and use reflection to create a new instance
	
	// use prototype must let the Query implements Cloneable interface and Override clone()
	private static Query prototype;
	static {
		try {
			/// load the query class by using the DB.properties file
			c = Class.forName(DBManager.getConfiguration().getQueryClass());
			prototype = (Query) c.getConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private QueryFactory() {} // private constructor
	
	// use the singleton to create the query class
	// but the efficiency is a little bit low
	public static Query createQuery01() {
		// return new MySqlQuery(); // it works but we needs to use the configuration file
		try {
			return (Query) c.getConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Query createQuery02() {
		try {
			return (Query) prototype.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
