package Chapter14;

/**
 * Used For Annotation
 * @author Woo
 *
 */
/*
 * one class maps to one table
 * eg table: 
 * CREATE TABLE "Tb_Student" (
 * 	'ID' int(10) NOT NULL AUTO_INCREMENT,
 * 	'SName' varchar(10) DEFAULT NULL,
 *  'Age' int(3) DEFAULT NULL,
 *  PRIMARY KEY('ID')
 *  )
 */
@MyTable("Tb_Student") // create a table "Tb_Student" in JDBC
public class Student {
	@MyField(ColName = "ID", Type = "int", Length = 10)
	public int ID;
	@MyField(ColName = "SName", Type = "varchar", Length = 10)
	public String SName;
	@MyField(ColName = "Age", Type = "int", Length = 10)
	public int Age;
	
	public int getID() {
		return ID;
	}
	@MyMethod("Number")
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getSName() {
		return SName;
	}
	public void setSName(String sName) {
		SName = sName;
	}
	
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
}
