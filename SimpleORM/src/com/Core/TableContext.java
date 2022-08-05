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
@SuppressWarnings("all")
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
			Connection con = DBManager.getConnection();
			DatabaseMetaData dbmd = con.getMetaData();
			ResultSet tableRet = dbmd.getTables(null, "%", "%", new String[] {"TABLE"});
			while (tableRet.next()) {
				String tableName = (String) tableRet.getObject("TABLE_NAME");
				TableInformation ti = new TableInformation(tableName, 
														   new HashMap<String, ColumnInformation>(),
														   new ArrayList<ColumnInformation>());
				tables.put(tableName, ti);
				ResultSet set1 = dbmd.getColumns(null, "%", tableName, "%");
				while (set1.next()) {
					ColumnInformation ci1 = new ColumnInformation(set1.getString("COLUMN_NAME"),
																  set1.getString("TYPE_NAME"), 0);
					ti.getColumns().put(set1.getString("COLUMN_NAME"), ci1);
				}
				ResultSet set2 = dbmd.getPrimaryKeys(null, "%", tableName);
				while (set2.next()) {
					ColumnInformation ci2 = (ColumnInformation) ti.getColumns().get(set2.getObject("COLUMN_NAME"));
					ci2.setKeyType(1); 
					ti.getPrimaryKeys().add(ci2);
				}
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
		for (TableInformation ti : tables.values()) {
			try {
				Class c = Class.forName(DBManager.getConfiguration().getPoPackage() + 
										"." + StringUtils.firstChar2UpperCase(ti.getTableName()));
				poClassTableMap.put(c, ti);
			} catch (ClassNotFoundException e) {
				// e.printStackTrace();
			}
		}
	}
}
