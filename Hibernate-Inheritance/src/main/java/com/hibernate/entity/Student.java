package com.hibernate.entity;

// use <subclass> for mapping
public class Student extends People {
	private String School;
	private int ID;
	private String Name;
	private int Age;
	
	public String getSchool() {
		return School;
	}
	public void setSchool(String school) {
		School = school;
	}
	
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
	
	public Student(String school, int iD, String name, int age) {
		School = school;
		ID = iD;
		Name = name;
		Age = age;
	}
	
	public Student() {}
}
