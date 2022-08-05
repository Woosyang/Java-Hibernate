package com.Core;
import java.util.*;
import java.sql.*;
import com.Bean.*;
import com.Utils.JavaFileUtils;
import com.Utils.StringUtils;

/**
 * Table Context class, it is used to manage the relationship between all the structures
 * of the tables in database and all the structures of classes in java. 
 * It is used to create a java class according to the information from the table in database. 
 * @author Woo
 *
 */
public class TableContext {
	/**
	 * the key is the table name, the value is the table information
	 */
	public static Map<String, TableInformation> tables = new HashMap<>();
	
	/**
	 * connect the class from Po package with the information in the table
	 * so that the object of the class can be reused multiple times 
	 */
	public static Map<Class, TableInformation> poClassTableMap = new HashMap<>();
	
	private  TableContext() {}
	
	static {
		try {
			// initialization of the information in the table
			Connection con = DBManager.getConnection();
			DatabaseMetaData dbmd = con.getMetaData();
			// Retrieves a description of the tables available in the given catalog. 
			// Only table descriptions matching the catalog, schemaPattern, tableNamePattern and table types 
			// criteria are returned. 
			// getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types)
			// catalog: must match the catalog name as it is stored in the database; 
			//			"" retrieves those without a catalog; null means that the catalog name should not be used to narrow the search
			// schemaPattern: "" and null are the same above, also can use "_" and "%" as the parameter
			// "_" means single character wildcard, "%" means multiple character wildcard
			// tableNamePattern: must match the table name as it is stored in the database
			// table types: typical types are "TABLE", "VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM"
			ResultSet tableRet = dbmd.getTables("mydb", "%", "%", new String[] {"TABLE"});
			while (tableRet.next()) {
				String tableName = (String) tableRet.getObject("TABLE_NAME");
				TableInformation ti = new TableInformation(tableName, 
														   new HashMap<String, ColumnInformation>(),
														   new ArrayList<ColumnInformation>());
				tables.put(tableName, ti);
				
				// search all the columns in the table
				// getColumns(String catalog, String schemaPattern, String tableNamePattern, String columnNamePattern)
				// all the pattern can be filled with "%" or "_" depends on the requirement
				ResultSet set1 = dbmd.getColumns(null, "%", tableName, "%");
				while (set1.next()) {
					ColumnInformation ci1 = new ColumnInformation(set1.getString("COLUMN_NAME"),
																  set1.getString("TYPE_NAME"), 0);
					ti.getColumns().put(set1.getString("COLUMN_NAME"), ci1);
				}
				// search the user table's primary key
				// getPrimaryKeys(String catalog, String schema, String table)
				ResultSet set2 = dbmd.getPrimaryKeys(null, "%", tableName);
				while (set2.next()) {
					ColumnInformation ci2 = (ColumnInformation) ti.getColumns().get(set2.getObject("COLUMN_NAME"));
					ci2.setKeyType(1); // set the key type as the primary key type
					ti.getPrimaryKeys().add(ci2);
				}
				// get the only primary key for the convenience 
				// if it is composite key, set it as null
				if (ti.getPrimaryKeys().size() > 0) {
					ti.setOnlyPrimaryKey(ti.getPrimaryKeys().get(0));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// update the java files of the PO package
		updateJavaPOFile();
		
		// load the Class objects of the java files in PO so that it can be reused in
		// multiple times in Query interface
		loadPOTables(); 
	}
	
	public static Map<String, TableInformation> getTableInformation() {
		return tables;
	}
	
	/**
	 * update the java files of the PO package(based on configuration) according 
	 * to the table information, fulfill the goal of conversion from table to class
	 */
	public static void updateJavaPOFile() {
		Map<String, TableInformation> Map = TableContext.tables;
		for (TableInformation t : Map.values()) {
			JavaFileUtils.createJavaPoFile(t, new MySqlConverter());
		}
	}
	
	/**
	 * load the classes of the java files in PO package
	 */
	public static void loadPOTables() {
		// eg:
		// Class c = Class.forName("com.Po.Emp");
		// poClassTableMap.put(c, TableInformation);
		for (TableInformation ti : tables.values()) {
			try {
				Class c = Class.forName(DBManager.getConfiguration().getPoPackage() + 
										"." + StringUtils.firstChar2UpperCase(ti.getTableName()));
				poClassTableMap.put(c, ti);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		Map<String, TableInformation> Tables = TableContext.tables;
		System.out.println(Tables);
	}
}
