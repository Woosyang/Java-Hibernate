package com.hibernate.entity;
import java.util.*;

public class Category {
	private int ID;
	private String Name;
	private Set<Item> Items = new HashSet<>();
	
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
	
	public Set<Item> getItems() {
		return Items;
	}
	public void setItems(Set<Item> items) {
		Items = items;
	}
	
	public Category(int iD, String name, Set<Item> items) {
		ID = iD;
		Name = name;
		Items = items;
	}
	
	public Category() {}
}
