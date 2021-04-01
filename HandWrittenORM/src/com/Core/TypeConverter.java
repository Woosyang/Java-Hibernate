package com.Core;

/**
 * The TypeConverter interface, it is used for the conversion of the data type between 
 * Java and database. (Java <-> Database)
 * @author Woo
 *
 */
public interface TypeConverter {
	/**
	 * convert the data type of the database to the data type of java
	 * @param columnType
	 * @return the data type of java
	 */
	public String databaseType2JavaType(String columnType);
	
	/**
	 * convert the data type of java to the data type of the database
	 * @param javaDataType
	 * @return the data type of database
	 */
	public String javaType2DatabaseType(String javaDataType);
}
