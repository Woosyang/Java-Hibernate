package com.Core;

/**
 * conversion of the data type between MySql database and Java 
 * @author Woo
 *
 */
public class MySqlConverter implements TypeConverter {

	@Override
	public String databaseType2JavaType(String columnType) {
		if ("varchar".equalsIgnoreCase(columnType) || 
			"char".equalsIgnoreCase(columnType)) {
			return "String";
		} else if ("int".equalsIgnoreCase(columnType) ||
				   "tinyint".equalsIgnoreCase(columnType) ||
				   "smallint".equalsIgnoreCase(columnType) ||
				   "integer".equalsIgnoreCase(columnType)) {
			return "Integer";
		} else if ("bigint".equalsIgnoreCase(columnType)) {
			return "Long";
		} else if ("double".equalsIgnoreCase(columnType)) {
			return "Double";
		} else if ("float".equalsIgnoreCase(columnType)) {
			return "Float";
		} else if ("clob".equalsIgnoreCase(columnType)) {
			return "java.sql.Clob";
		} else if ("blob".equalsIgnoreCase(columnType)) {
			return "java.sql.Blob";
		} else if ("date".equalsIgnoreCase(columnType)) {
			return "java.sql.Date";
		} else if ("time".equalsIgnoreCase(columnType)) {
			return "java.sql.Time";
		} else if ("timestamp".equalsIgnoreCase(columnType)) {
			return "java.sql.Timestamp";
		} else {
			// just ignore other data format(type) of MySql database
			// you can keep adding the data type of MySql database
			return null;
		}
	}

	@Override
	public String javaType2DatabaseType(String javaDataType) {
		return null;
	}
}
