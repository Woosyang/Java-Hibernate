package com.Core;
import java.lang.reflect.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import com.Bean.*;
import com.Po.*;
import com.Utils.*;
import com.Vo.EmployeeVo;

/**
 * Used to make a query based on MySql
 * @author Woo
 *
 */
@SuppressWarnings("all")
public class MySqlQuery extends Query {
	// since most of the Query abstract class / interfaces of different databases has
	// similar functions(executeDML(), insert(), delete(), update(), queryRows(),
	//                   queryUniqueRow(), queryValue(), queryNumber())
	// we can put the code of the functions inside Query abstract class
	
	// write the code below in abstract class Query
	// @Override
	// public int executeDML(String sql, Object[] params) {
		// Connection con = DBManager.getConnection();
		// int count = 0; // counter, count how many records would be changed due to the query
		// PreparedStatement ps = null;
		// try {
			// ps = con.prepareStatement(sql);
			/*// write the code below in JDBCUtils
			// only deal with the case that query with certain column name
			   if (params != null) {
			    	for (int i = 0; i < params.length; i ++) {
						// in JDBC, index starts with 1
						ps.setObject(i + 1, params[i]);
					}
				}
			*/
			// set the parameter for sql query
			// JDBCUtils.handleParameters(ps, params);
			// System.out.println(ps); // check out the Sql query
			// count = ps.executeUpdate();
		// } catch (Exception e) {
			// e.printStackTrace();
		// } finally {
			// DBManager.Close(con, ps);
		// }
		// return count;
	// }
	
	// write the code below in abstract class Query
	// @Override
	// public void insert(Object obj) {
			// put the obj into the table
			// eg: insert into user (id, uname, pwd) values (?, ?, ?)
			// Class c = obj.getClass();
			// get the table information(one row)
			// TableInformation tableInformation = TableContext.poClassTableMap.get(c);
			// StringBuilder Sql = new StringBuilder("insert into " + tableInformation.getTableName() + " ("); // make the query statement
			// get all the fields of the obj
			// Field[] fs = c.getDeclaredFields();
			// not sure about how many parameters will be set in the Sql query, so use array list
			// store the parameters of the Object[] in Sql query for the executeDML(sql, params)
			// List<Object> params = new ArrayList<>(); 
			// int countNotNullField = 0; // count the number of the not null fields
			// for (Field f : fs) {
				// String fieldName = f.getName();
				// use the ReflectionUtils to call the getter method
				// Object fieldValue = ReflectionUtils.invokeGet(fieldName, obj);
				// if (fieldValue != null) {
					// eg:id, uname, pwd)
					// Sql.append(fieldName + ", ");
					// params.add(fieldValue);
					// countNotNullField ++;
				// }
			// }
		// Sql.setCharAt(Sql.length() - 2, ')'); // )
		// Sql.append(" values (");
		// for (int i = 0; i < countNotNullField; i ++) {
			// Sql.append("?, ");
		// }
		// Sql.setCharAt(Sql.length() - 2, ')');
		// Sql.append(";");
		// System.out.println(Sql.toString());
		// call the executeDML() to insert a record into the table
		// executeDML(Sql.toString(), params.toArray());
	// }
	
	// write the code below in abstract class Query
	// @Override
	// public void delete(Class clazz, Object id) {
		// eg: parameter: (Emp, 2) ---> result: delete from Emp where id = 2;
		// find the table according to Class object
		// however, there is a problem:
		// the name of the java file(Class object) starts with the upper case
		// but the name of the table doesn't(it may starts with upper / lower case)
		// which makes the name of the java file can't match up with the table name
		// to solve this problem, we needs to use a Map<> to connect the name of the 
		// java file with the table name in TableContext.java
		
		// 1. find the table according to Class object
		// TableInformation tableInformation = TableContext.poClassTableMap.get(clazz);
		// ColumnInformation onlyPrimaryKey = tableInformation.getOnlyPrimaryKey();
		
		// 2. make the Sql query String, onlyPrimaryKey could be id or something else
		// String Sql = "delete from " + tableInformation.getTableName() + " where " + 
					  // onlyPrimaryKey.getName() + " = ?;";
		// System.out.println(Sql);
		// call the DML statement
		// executeDML(Sql, new Object[] {id});
	// }
	
	// write the code below in abstract class Query
	// @Override
	// public void delete(Object obj) {
		// delete one row of the table, each column of the table is the field of the 
		// java bean file, one of the column is the primary key of the table
		// Class c = obj.getClass();
		// TableInformation tableInformation = TableContext.poClassTableMap.get(c);
		// get the primary key, also its name is the name of the field in java bean class
		// ColumnInformation onlyPrimaryKey = tableInformation.getOnlyPrimaryKey();
		// use reflection mechanism to call the setter and getter method corresponding
		// to the field of the java bean class
		
		/* // write those code in ReflectionUtils.java 
		try {
			// get the value of the primary key value
			Method m = c.getMethod("get" + StringUtils.firstChar2UpperCase(onlyPrimaryKey.getName()), null);
			Object primaryKeyValue = m.invoke(obj, null);
			
			// call the delete(Object obj, Object id) method above to delete the 
			// targeted row of the table
			delete(c, primaryKeyValue);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		*/
		
		// use ReflectionUtils to handle the reflection
		// String fieldName = onlyPrimaryKey.getName();
		
		// get the primary key's value
		// Object id = ReflectionUtils.invokeGet(fieldName, obj);
		
		// call the delete(Object obj, Object id) method above to delete the 
		// targeted row of the table
		// delete(c, id);
	// }
	
	// write the code below in abstract class Query
	// @Override
	// public int update(Object obj, String[] fieldName) {
		// eg: put the parameters need to be updated, "uname", "id" in obj
		// -> update Emp set uname = ?, pwd = ? where id = ?
		// get the table information
		// Class c = obj.getClass();
		// TableInformation tableInformation = TableContext.poClassTableMap.get(c);
		// List<Object> params = new ArrayList<>(); // similar with insert()
		// StringBuilder Sql = new StringBuilder("update " + tableInformation.getTableName());
		// Sql.append(" set ");
		// for (String f : fieldName) {
			// Sql.append(f + " = ?, ");
			// Object value = ReflectionUtils.invokeGet(f, obj); // get the updated value of each field
			// params.add(value); // left to the JDBCUtils in executeDML()
		// }
		// Sql.setCharAt(Sql.length() - 2, ' ');
		// get the information of the primary key
		// ColumnInformation onlyPrimaryKey = tableInformation.getOnlyPrimaryKey();
		// Object primaryKeyValue = ReflectionUtils.invokeGet(onlyPrimaryKey.getName(), obj);
		// params.add(primaryKeyValue); // must put the key value in the last because of the last "?"
		// Sql.append("where " + onlyPrimaryKey.getName() + " = ?");
		// System.out.println(Sql.toString());
		// return executeDML(Sql.toString(), params.toArray()); // params.toArray() -> Object[]
	// }
	
	// write the code below in abstract class Query
	// @Override
	// public List queryRows(String sql, Class clazz, Object[] params) {
		// similar with executeDML()
		// Connection con = DBManager.getConnection();
		// List<Object> list = null; // the container for storing the result(the object needs to be found) 
		// PreparedStatement ps = null;
		// ResultSet rs = null;
		// try {
			// list = new ArrayList<>(); // initialization outside the loop
			// prepared statement of the Sql query
			// sql and params are offered by database
			// ps = con.prepareStatement(sql);
			// JDBCUtils.handleParameters(ps, params);
			// rs = ps.executeQuery();
			// from java.sql.*, for getting the number of the columns
			// ResultSetMetaData metaData = rs.getMetaData();
			// multiple lines in the table
			// while (rs.next()) {
				// use reflection to create a new instance whose constructor only can be null
				// call the constructor of the java bean
				// Object eachRow = clazz.getConstructor().newInstance();
				// multiple columns in each row
				// eg: select uname, pwd, age from user where id > ? and age < ?
				// for (int i = 0; i < metaData.getColumnCount(); i ++) {
			 		// in JDBC index starts with 1
					// field of the object
					// String columnName = metaData.getColumnLabel(i + 1); // getColumnName() is also ok
					// the value of the field in the object
					// Object columnValue = rs.getObject(i + 1);
					
					// use reflection to the setter method of the eachRow object 
					// corresponding to each record of the table, then assign the 
					// columnValue to each field of the object
					
					/*
					// write the code below in the ReflectionUtils
					// Method m = clazz.getDeclaredMethod("set" + StringUtils.firstChar2UpperCase(columnName), columnValue.getClass());
					Method m = eachRow.getClass().getDeclaredMethod("set" + StringUtils.firstChar2UpperCase(columnName), columnValue.getClass());
					m.invoke(eachRow, columnValue);
					*/
					// ReflectionUtils.invokeSet(eachRow, columnName, columnValue);
				// }
				// System.out.println(ReflectionUtils.invokeGet("Name", eachRow));
				// list.add(eachRow);
			// }
		// } catch (Exception e) {
			// e.printStackTrace();
		// } finally {
			// DBManager.Close(ps, rs, con);
		// }
		// return list;
	// }

	@Override
	public Object queryUniqueRow(String sql, Class clazz, Object[] params) {
		// use the queryRow() to search the object
		List list = new MySqlQuery().queryRows(sql, clazz, params);
		return (list.size() == 0 || list == null) ? null : list.get(0);
		/*
		Connection con = DBManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			JDBCUtils.handleParameters(ps, params); // set the parameters of the SQL query
			rs = ps.executeQuery();
			Object uniqueRow = clazz.getConstructor().newInstance();
			ResultSetMetaData metaData = rs.getMetaData(); // same in queryRows()
			// database to java, value is the database, assign those values to fields of the java bean object
			while (rs.next()) { 
				for (int i = 0; i < metaData.getColumnCount(); i ++) {
					String columnName = metaData.getColumnLabel(i + 1);
					Object value = rs.getObject(i + 1);
					ReflectionUtils.invokeSet(uniqueRow, columnName, value);
				}
			}
			return uniqueRow;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			DBManager.Close(rs, ps, con);
		}
		*/
	}
	
	// write the code below in abstract class Query
	// @Override
	// public Object queryValue(String sql, Object[] params) {
		// Connection con = DBManager.getConnection();
		// Object value = null;
		// PreparedStatement ps = null;
		// ResultSet rs = null;
		// try {
			// ps = con.prepareStatement(sql);
			// JDBCUtils.handleParameters(ps, params);
			// System.out.println(ps);
			// rs = ps.executeQuery();
			// while (rs.next()) {
				// value = rs.getObject(1); // relates to count(*) in SQL query, only got one column
			// }
		// } catch (Exception e) {
			// e.printStackTrace();
		// } finally {
			// DBManager.Close(rs, ps, con);
		// }
		// return value; // how many rows matching the requirements of the SQL query
	// }
	
	// write the code below in abstract class Query
	/*
	@Override
	public Number queryNumber(String sql, Object[] params) {
		return (Number) queryValue(sql, params);
	}
	*/
	
	@Override
	public Object queryPagenate(int pageNum, int Size) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void testDelete01() {
		// test the delete query and executeDML query
		Employee e = new Employee();
		e.setID(2);
		new MySqlQuery().delete(e);
	}
	
	public static void testDelete02() {
		// test the delete query and executeDML query
		Employee e = new Employee();
		new MySqlQuery().delete(e.getClass(), 10);
	}
	
	public static void testInsert() {
		// test the insert query
		Employee e = new Employee();
		e.setAge(18);
		e.setBonus(100000);
		Date d = new Date(2020121785862L);
		e.setCheckInDate(d);
		e.setDeptID(2);
		e.setName("Saki");
		e.setSalary(8500.0);
		e.setID(2);
		new MySqlQuery().insert(e);
	}
	
	public static void testUpdate() {
		// test the update query
		Employee e = new Employee();
		e.setID(3);
		e.setAge(22);
		// e.setName("Harry");
		e.setDeptID(1);
		new MySqlQuery().update(e, new String[] {"Age", "DeptID"});
	}
	
	public static void testQueryRows01() {
		// test the select query
		// the column Name's case should remain the same in Sql query 
		String Sql = "select ID, name, age from employee where age > ? and ID < ?";
		Class c = Employee.class;
		List<Employee> list = new MySqlQuery().queryRows(Sql, c, new Object[] {18, 10});
		// System.out.println(list.size());
		for (Employee e : list) {
			System.out.println(e.getName());
		}
	}
	
	public static void testQueryRows02() {
		// used complicated SQL query with created java bean class EmployeeVo
		String Sql = "select e.ID, e.Name, e.Age, Bonus + Salary 'Income', "
				   + "d.name 'DeptName', d.address 'DeptAddr' from Employee e join "
				   + "Department d on e.DeptID = d.ID;";
		Class c = EmployeeVo.class;
		List<EmployeeVo> list = new MySqlQuery().queryRows(Sql, c, null);
		for (EmployeeVo e : list) {
			System.out.println(e.getName() +", " + e.getIncome() + ", " + e.getDeptAddr());
		}
	}
	
	public static void testQueryUniqueRow() {
		String sql = "select * from employee where Age = ? and ID = ?";
		Employee e = (Employee) new MySqlQuery().queryUniqueRow(sql, Employee.class, new Object[] {20, 1});
		System.out.println(e.getName());
	}
	
	public static void testQueryValue() {
		String sql = "select count(*) from Employee where Age > ? and ID < ?";
		Object obj = new MySqlQuery().queryValue(sql, new Object[] {18, 10});
		System.out.println(obj);
	}
	
	public static void testQueryNumber() {
		// almost the same like testQueryValue()
		String sql = "select count(*) from Employee where Age > ? and ID < ?";
		Number num = new MySqlQuery().queryNumber(sql, new Object[] {18, 10});
		System.out.println(num);
	}
	
	public static void testQueryByID() {
		Employee e = (Employee) new MySqlQuery().queryByID(4, Employee.class);
		System.out.println(e.getName());
	}
	
	public static void main(String[] args) {
		// testDelete01();
		// testDelete02();
		// testInsert();
		// testUpdate();
		// testQueryRows01();
		// testQueryRows02();
		// testQueryUniqueRow();
		// testQueryValue();
		// testQueryNumber();
		testQueryByID();
	}
}
