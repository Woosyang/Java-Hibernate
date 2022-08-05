package com.hibernate.entity;

// subclass 
// used with <joined-subclass>
public class Dog extends Animal {
	private int ID;
	private String Name;
	private String Food;
	
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
	
	public String getFood() {
		return Food;
	}
	public void setFood(String food) {
		Food = food;
	}
	
	public Dog(int iD, String name, int iD2, String name2, String food) {
		ID = iD2;
		Name = name2;
		Food = food;
	}
	
	public Dog() {}
}
