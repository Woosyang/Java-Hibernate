package com.Po;
import java.sql.*;
import java.util.*;

public class Product {

	private java.sql.Blob Picture;
	private java.sql.Timestamp RegTime;
	private Integer ID;
	private String Pwd;
	private null Info;
	private String Name;


	public java.sql.Blob getPicture() {
		return Picture;
	}
	public java.sql.Timestamp getRegTime() {
		return RegTime;
	}
	public Integer getID() {
		return ID;
	}
	public String getPwd() {
		return Pwd;
	}
	public null getInfo() {
		return Info;
	}
	public String getName() {
		return Name;
	}
	public void setPicture(java.sql.Blob Picture) {
		this.Picture = Picture;
	}
	public void setRegTime(java.sql.Timestamp RegTime) {
		this.RegTime = RegTime;
	}
	public void setID(Integer ID) {
		this.ID = ID;
	}
	public void setPwd(String Pwd) {
		this.Pwd = Pwd;
	}
	public void setInfo(null Info) {
		this.Info = Info;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public Product() {}
}
