package com.Core;

/**
 * Encapsulate the Java fields and setter and getter method of the source code
 * @author Woo
 *
 */
public class JavaFieldGetSet {
	/**
	 * the source code information of the field, eg: private int UserId; 
	 */
	private String fieldInformation;
	/**
	 * the source code information of the get method, eg: public int getUserId()
	 */
	private String getInformation;
	/**
	 * the source code information of the set method, eg: void setUserId(int Id) {this.Id = Id}
	 */
	private String setInformation;
		
	public String getFieldInformation() {
		return fieldInformation;
	}
	public void setFieldInformation(String fieldInformation) {
		this.fieldInformation = fieldInformation;
	}
	
	public String getGetInformation() {
		return getInformation;
	}
	public void setGetInformation(String getInformation) {
		this.getInformation = getInformation;
	}
	
	public String getSetInformation() {
		return setInformation;
	}
	public void setSetInformation(String setInformation) {
		this.setInformation = setInformation;
	}
	
	public JavaFieldGetSet(String fieldInformation, String getInformation, String setInformation) {
		super();
		this.fieldInformation = fieldInformation;
		this.getInformation = getInformation;
		this.setInformation = setInformation;
	}
	
	
	public JavaFieldGetSet() {}
	
	@Override
	public String toString() {
		StringBuilder Sb = new StringBuilder();
		Sb.append(fieldInformation + "\n");
		Sb.append(getInformation + "\n");
		Sb.append(setInformation + "\n");
		return Sb.toString();
	}
}
