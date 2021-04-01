package com.Po;
import java.sql.*;
import java.util.*;

public class Department {

	private String Address;
	private Integer ID;
	private String Name;


	public String getAddress() {
		return Address;
	}
	public Integer getID() {
		return ID;
	}
	public String getName() {
		return Name;
	}
	public void setAddress(String Address) {
		this.Address = Address;
	}
	public void setID(Integer ID) {
		this.ID = ID;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public Department() {}
}
