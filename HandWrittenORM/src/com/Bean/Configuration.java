package com.Bean;

/**
 * Configuration, manage the information of the configuration file
 * @author Woo
 *
 */
public class Configuration {
	/**
	 * Driver class
	 */
	private String Driver;
	/**
	 * JDBC URL
	 */
	private String URL;
	/**
	 * Database's Account
	 */
	private String User;
	/**
	 * Database's PassWord 
	 */
	private String PassWord;
	/**
	 * which Database is used 
	 */
	private String DataBase;
	/**
	 * the path of the source code in the project
	 */
	private String SrcPath;
	/**
	 * the package for generating the java class by scanning(PO: persistence object)
	 * 
	 */
	private String PoPackage;
	
	/**
	 * which query class is used to make the SQL query(the path of the file)
	 */
	private String QueryClass;
	
	/**
	 * minimum capacity of the connection pool
	 */
	private int poolMinSize;
	
	/**
	 * max capacity of the connection pool
	 */
	private int poolMaxSize;
	
	public String getQueryClass() {
		return QueryClass;
	}
	public void setQueryClass(String queryClass) {
		QueryClass = queryClass;
	}
	
	public String getDriver() {
		return Driver;
	}
	public void setDriver(String driver) {
		Driver = driver;
	}
	
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
	
	public String getDataBase() {
		return DataBase;
	}
	public void setDataBase(String dataBase) {
		DataBase = dataBase;
	}
	
	public String getSrcPath() {
		return SrcPath;
	}
	public void setSrcPath(String srcPath) {
		SrcPath = srcPath;
	}
	
	public int getPoolMinSize() {
		return poolMinSize;
	}
	public void setPoolMinSize(int poolMinSize) {
		this.poolMinSize = poolMinSize;
	}
	
	public int getPoolMaxSize() {
		return poolMaxSize;
	}
	public void setPoolMaxSize(int poolMaxSize) {
		this.poolMaxSize = poolMaxSize;
	}
	
	public String getPoPackage() {
		return PoPackage;
	}
	public void setPoPackage(String poPackage) {
		PoPackage = poPackage;
	}
	
	public Configuration(String driver, String uRL, String user, String passWord, String dataBase, 
						 String srcPath, String poPackage, String queryClass) {
		super();
		Driver = driver;
		URL = uRL;
		User = user;
		PassWord = passWord;
		DataBase = dataBase;
		SrcPath = srcPath;
		PoPackage = poPackage;
		QueryClass = queryClass;
	}
	public Configuration() {}
}
