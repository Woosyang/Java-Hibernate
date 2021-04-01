package com.Bean;
import java.util.*;

/**
 * Table Information class, used to store the information of the whole table
 * @author Woo
 *
 */
public class TableInformation {
	/**
	 * table's name
	 */
	private String tableName;
	
	/**
	 * all the information of the columns
	 */
	private Map<String, ColumnInformation> columns;
	
	/**
	 * the unique primary key, right now it can only deal with the case that the table
	 * only can have a primary key
	 */
	private ColumnInformation onlyPrimaryKey;
	
	/**
	 * store the composite primary key
	 */
	private List<ColumnInformation> PrimaryKeys;
	
	public List<ColumnInformation> getPrimaryKeys() {
		return PrimaryKeys;
	}

	public void setPrimaryKeys(List<ColumnInformation> primaryKeys) {
		PrimaryKeys = primaryKeys;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Map<String, ColumnInformation> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, ColumnInformation> columns) {
		this.columns = columns;
	}

	public ColumnInformation getOnlyPrimaryKey() {
		return onlyPrimaryKey;
	}

	public void setOnlyPrimaryKey(ColumnInformation onlyPrimaryKey) {
		this.onlyPrimaryKey = onlyPrimaryKey;
	}

	public TableInformation(String tableName, Map<String, ColumnInformation> columns,
							ColumnInformation onlyPrimaryKey) {
		super();
		this.tableName = tableName;
		this.columns = columns;
		this.onlyPrimaryKey = onlyPrimaryKey;
	}
	
	public TableInformation(String tableName, Map<String, ColumnInformation> columns,
							List<ColumnInformation> primaryKeys) {
		super();
		this.tableName = tableName;
		this.columns = columns;
		PrimaryKeys = primaryKeys;
	}

	public TableInformation() {}
}
