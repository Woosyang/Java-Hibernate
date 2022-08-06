package com.hibernate.entity;
import java.util.*;

public class Item {
	private int ID;
	private String Name;
	private Set<Category> Categories = new HashSet<>();
	
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
	
	public Set<Category> getCategories() {
		return Categories;
	}
	public void setCategories(Set<Category> categories) {
		Categories = categories;
	}
	
	public Item(int iD, String name, Set<Category> categories) {
		super();
		ID = iD;
		Name = name;
		Categories = categories;
	}
	
	public Item() {}
}
