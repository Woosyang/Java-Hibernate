package com.hibernate.entity;

// subclass
// used with <union-subclass>
public class Grape extends Fruit {
	private int ID;
	private String Name;
	private String Color;
	
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
	
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	
	public Grape(int iD, String name, int iD2, String name2, String color) {
		ID = iD2;
		Name = name2;
		Color = color;
	}
	
	public Grape() {}
}
