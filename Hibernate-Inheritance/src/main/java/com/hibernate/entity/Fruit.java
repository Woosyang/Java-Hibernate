package com.hibernate.entity;

// parent class
// used with <union-subclass>
public class Fruit {
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

	public Fruit(int iD, String name) {
		super();
		ID = iD;
		Name = name;
	}
	
	public Fruit() {}
}
