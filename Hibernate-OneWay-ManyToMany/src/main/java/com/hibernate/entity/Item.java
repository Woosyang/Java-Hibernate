package com.hibernate.entity;

public class Item {
	private int ID;
	private String Name;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
	public Item(int iD, String name) {
		super();
		ID = iD;
		Name = name;
	}
	
	public Item() {}
}
