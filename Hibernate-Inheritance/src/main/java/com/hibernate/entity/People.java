package com.hibernate.entity;

// parent class
// used with <subclass>
public class People {
	private int ID;
	private String Name;
	private int Age;
	
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
	
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	
	public People() {}
	
	public People(int iD, String name, int age) {
		ID = iD;
		Name = name;
		Age = age;
	}
}
