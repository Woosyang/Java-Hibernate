package com.Bean;

/**
 * Encapsulate the information of a single column of the table.
 * @author Woo
 *
 */
public class ColumnInformation {
	
	/**
	 * column name in the table
	 */
	private String name;
	
	/**
	 * the data type of the value in the column
	 */
	private String dataType;
	
	/**
	 * the key type of the column(0: normal key, 1: primary key, 2: external key)
	 */
	private int keyType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getKeyType() {
		return keyType;
	}

	public void setKeyType(int keyType) {
		this.keyType = keyType;
	}

	public ColumnInformation(String name, String dataType, int keyType) {
		super();
		this.name = name;
		this.dataType = dataType;
		this.keyType = keyType;
	}
	
	public ColumnInformation() {}
}
