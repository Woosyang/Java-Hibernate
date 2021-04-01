package com.Core;
import java.lang.reflect.*;

/**
 * QueryFactory class, it is used to create query object based on configuration files
 * @author Woo
 *
 */
@SuppressWarnings("all")
public class QueryFactory {
	private static QueryFactory factory = new QueryFactory();
	private static Class c;
	
	private static Query prototype;
	static {
		try {
			c = Class.forName(DBManager.getConfiguration().getQueryClass());
			prototype = (Query) c.getConstructor().newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private QueryFactory() {} // private constructor
	
	public static Query createQuery01() {
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
