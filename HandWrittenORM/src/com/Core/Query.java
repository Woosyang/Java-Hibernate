package com.Core;
import java.lang.reflect.*;
import java.sql.*;
import java.util.*;
import com.Bean.*;
import com.Utils.*;

/**
 * The Query interface, used to make a query, the core class offering service toward outside
 * @author Woo
 *
 */
// 2 main kinds of the statement for query: 
// 1. DML(insert, update, delete), 2. Search(select)
@SuppressWarnings("all")
public abstract class Query implements Cloneable {
	
	// used with any select query method like queryRows().... 
	// handle the Connection, PreparedStatement, ResultSet for the query methods
	// use template pattern
	/**
	 * use template pattern to encapsulate JDBC operations in to a template so that
	 * they can used in many other methods
	 * @param Sql, Sql query
	 * @param Params, the parameters inside Sql query
	 * @param Clazz, the java class needs to be encapsulate(be assigned values)
	 * @param Cb, the realizing class of the CallBack interface, execute the call back method
	 * @return query result 
	 */
	public Object executeQueryTemplate(String Sql, Object[] Params, Class Clazz, CallBack Cb) {
		Connection Con = DBManager.getConnection();
		PreparedStatement Ps = null;
		ResultSet Rs = null;
		List list = null;
		try {
			Ps = Con.prepareStatement(Sql);
			// set the parameters for Sql query by using JDBCUtils
			JDBCUtils.handleParameters(Ps, Params);
			Rs = Ps.executeQuery();
			return Cb.doExecute(Con, Ps, Rs);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			DBManager.Close(Rs, Ps, Con);
		}
	}
	
	// insert, delete, update(java to database)
	// query(database to java)
	/**
	 * directly execute a DML statement
	 * @param Sql, Sql query
	 * @param Params, parameters
	 * @return how many SQL lines recorded will be changed after executing this SQL statement
	 */
	public int executeDML(String Sql, Object[] Params) {
		Connection con = DBManager.getConnection();
		int count = 0; // counter, count how many records would be changed due to the query
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(Sql);
			// set the parameter for sql query
			JDBCUtils.handleParameters(ps, Params);
			// System.out.println(ps); // check out the Sql query
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.Close(con, ps);
		}
		return count;
	}
	
	/**
	 * store an object into the database
	 * put the fields of the objects which are not null in to the database
	 * each field maps to each column of the table in one row
	 * if the field is Number type and it is null, then it will be put into database
	 * as 0
	 * @param Obj, the object needs to be stored
	 */
	public void insert(Object Obj) {
		// convert the obj into one row of the table
		// eg: insert into user (id, uname, pwd) values (?, ?, ?)
		Class c = Obj.getClass();
		// get the table information(one row)
		TableInformation tableInformation = TableContext.poClassTableMap.get(c);
		StringBuilder Sql = new StringBuilder("insert into " + tableInformation.getTableName() + " ("); // make the query statement
		// get all the fields of the obj
		Field[] fs = c.getDeclaredFields();
		// not sure about how many parameters will be set in the Sql query, so use array list
		// store the parameters of the Object[] in Sql query for the executeDML(sql, params)
		List<Object> params = new ArrayList<>(); 
		int countNotNullField = 0; // count the number of the not null fields
		for (Field f : fs) {
			String fieldName = f.getName();
			// use the ReflectionUtils to call the getter method
			Object fieldValue = ReflectionUtils.invokeGet(fieldName, Obj);
			if (fieldValue != null) {
				// eg:id, uname, pwd)
				Sql.append(fieldName + ", ");
				params.add(fieldValue);
				countNotNullField ++;
			}
		}
		Sql.setCharAt(Sql.length() - 2, ')'); // )
		Sql.append(" values (");
		for (int i = 0; i < countNotNullField; i ++) {
			Sql.append("?, ");
		}
		Sql.setCharAt(Sql.length() - 2, ')');
		Sql.append(";");
		// System.out.println(Sql.toString());
		// call the executeDML() to insert a record into the table
		executeDML(Sql.toString(), params.toArray());
	}
	
	/**
	 * delete the data(one row) of the corresponding object belongs to the class in the 
	 * table(the record of the designated primary key ID) 
	 * @param Clazz, the Class Object of the class corresponding to the table
	 * @param ID, primary key
	 */
	public void delete(Class Clazz, Object ID) { // eg: delete from User where id = ?
		// eg: parameter: (Emp, 2) ---> result: delete from Emp where id = 2;
		// find the table according to Class object
		// however, there is a problem:
		// the name of the java file(Class object) starts with the upper case
		// but the name of the table doesn't(it may starts with upper / lower case)
		// which makes the name of the java file can't match up with the table name
		// to solve this problem, we needs to use a Map<> to connect the name of the 
		// java file with the table name in TableContext.java
				
		// 1. find the table according to Class object
		TableInformation tableInformation = TableContext.poClassTableMap.get(Clazz);
		ColumnInformation onlyPrimaryKey = tableInformation.getOnlyPrimaryKey();
				
		// 2. make the Sql query String, onlyPrimaryKey could be id or something else
		String Sql = "delete from " + tableInformation.getTableName() + " where " + 
					  onlyPrimaryKey.getName() + " = ?;";
		// System.out.println(Sql);
		// call the DML statement
		executeDML(Sql, new Object[] {ID});
	}
	
	/**
	 * delete the data of the corresponding object(the class of the object maps to the table,
	 * the primary key value of the object maps to the data(one row) in the table)
	 * @param Obj, the object needs to be deleted
	 */
	public void delete(Object Obj) {
		// delete one row of the table, each column of the table is the field of the 
		// java bean file, one of the column is the primary key of the table
		Class c = Obj.getClass();
		TableInformation tableInformation = TableContext.poClassTableMap.get(c);
		// get the primary key, also its name is the name of the field in java bean class
		ColumnInformation onlyPrimaryKey = tableInformation.getOnlyPrimaryKey();
		// use reflection mechanism to call the setter and getter method corresponding
		// to the field of the java bean class
		// use ReflectionUtils to handle the reflection
		String fieldName = onlyPrimaryKey.getName();
		// get the primary key's value
		Object id = ReflectionUtils.invokeGet(fieldName, Obj);
		// call the delete(Object obj, Object id) method above to delete the 
		// targeted row of the table
		delete(c, id);	
	}
	
	/**
	 * update the data(one row) corresponding to the object in the table, only update 
	 * the value of the designate field of the table
	 * @param Obj, the Object needs to be updated
	 * @param fieldName, the value inside each column of the table needs to be updated
	 * @return how many SQL lines recorded will be changed after executing this SQL statement
	 */
	public int update(Object Obj, String[] fieldName) { 
		// eg: put the parameters need to be updated, "uname", "id" in obj
		// -> update Emp set uname = ?, pwd = ? where id = ?
		// get the table information
		Class c = Obj.getClass();
		TableInformation tableInformation = TableContext.poClassTableMap.get(c);
		List<Object> params = new ArrayList<>(); // similar with insert()
		StringBuilder Sql = new StringBuilder("update " + tableInformation.getTableName());
		Sql.append(" set ");
		for (String f : fieldName) {
			Sql.append(f + " = ?, ");
			Object value = ReflectionUtils.invokeGet(f, Obj); // get the updated value of each field
			params.add(value); // left to the JDBCUtils in executeDML()
		}
		Sql.setCharAt(Sql.length() - 2, ' ');
		// get the information of the primary key
		ColumnInformation onlyPrimaryKey = tableInformation.getOnlyPrimaryKey();
		Object primaryKeyValue = ReflectionUtils.invokeGet(onlyPrimaryKey.getName(), Obj);
		params.add(primaryKeyValue); // must put the key value in the last because of the last "?"
		Sql.append("where " + onlyPrimaryKey.getName() + " = ?");
		// System.out.println(Sql.toString());
		return executeDML(Sql.toString(), params.toArray()); // params.toArray() -> Object[]
	}
	
	/**
	 * search and return multiple rows(data) recorded, and encapsulate each row into an
	 * object of the class designated by Clazz
	 * @param Sql, query statement
	 * @param Clazz, Class Object that encapsulate the java bean class into a data
	 * @param Params, parameters
	 * @return result of the query
	 */
	public List queryRows(final String Sql, final Class Clazz, final Object[] Params) {
		// use template pattern and call back mechanism to improve the code below
		/*
		// similar with executeDML()
		Connection con = DBManager.getConnection();
		List<Object> list = null; // the container for storing the result(the object needs to be found) 
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			list = new ArrayList<>(); // initialization outside the loop
			// prepared statement of the Sql query
			// sql and params are offered by database
			ps = con.prepareStatement(Sql);
			JDBCUtils.handleParameters(ps, Params);
			rs = ps.executeQuery();
			// from java.sql.*, for getting the number of the columns
			ResultSetMetaData metaData = rs.getMetaData();
			// multiple lines in the table
			while (rs.next()) {
				// use reflection to create a new instance whose constructor only can be null
				// call the constructor of the java bean
				Object eachRow = Clazz.getConstructor().newInstance();
				// multiple columns in each row
				// eg: select uname, pwd, age from user where id > ? and age < ?
				for (int i = 0; i < metaData.getColumnCount(); i ++) {
					// in JDBC index starts with 1
					// field of the object
					String columnName = metaData.getColumnLabel(i + 1); // getColumnName() is also ok
					// the value of the field in the object
					Object columnValue = rs.getObject(i + 1);
					// use reflection to the setter method of the eachRow object 
					// corresponding to each record of the table, then assign the 
					// columnValue to each field of the object
					ReflectionUtils.invokeSet(eachRow, columnName, columnValue);
				}
				// System.out.println(ReflectionUtils.invokeGet("Name", eachRow));
				list.add(eachRow);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.Close(ps, rs, con);
		}
		return list;
		*/
		
		// use executeQueryTemplate() to improve the code above
		// use anonymous class to realize the CallBack
		// every parameter used by anonymous class must be constant which only can be initialized once
		return (List) executeQueryTemplate(Sql, Params, Clazz, new CallBack() {
				@Override
				public Object doExecute(Connection con, PreparedStatement ps, ResultSet rs) {
					List list = null;
					try {
						// con, ps, rs offered by executeQueryTemplate()
						ResultSetMetaData metaData = rs.getMetaData();
						while (rs.next()) {
							if (list == null) {
								list = new ArrayList<>();
							}
							Object eachRow = Clazz.getConstructor().newInstance();
							for (int i = 0; i < metaData.getColumnCount(); i ++) {
								String columnName = metaData.getColumnLabel(i + 1);
								Object value = rs.getObject(i + 1);
								ReflectionUtils.invokeSet(eachRow, columnName, value);
							}
							list.add(eachRow);
						}
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
					return list;
				}
			});
	}
	
	/**
	 * search and return only one row(data) recorded, and encapsulate this row into an
	 * object of the class designated by Clazz
	 * @param Sql, query statement
	 * @param Clazz, Class Object that encapsulate the java bean class into a data
	 * @param Params, parameters
	 * @return result of the query
	 */
	public Object queryUniqueRow(String Sql, Class Clazz, Object[] Params) {
		/*
		Connection con = DBManager.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(Sql);
			JDBCUtils.handleParameters(ps, Params); // set the parameters of the SQL query
			rs = ps.executeQuery();
			Object uniqueRow = Clazz.getConstructor().newInstance();
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
		return executeQueryTemplate(Sql, Params, Clazz, new CallBack() {
			@Override
			public Object doExecute(Connection con, PreparedStatement ps, ResultSet rs) {
				try {
					rs = ps.executeQuery();
					ResultSetMetaData metaData = rs.getMetaData();
					Object uniqueRow = Clazz.getConstructor().newInstance();
					while (rs.next()) {
						for (int i = 0; i < metaData.getColumnCount(); i ++) {
							Object value = rs.getObject(i + 1);
							String columnName = metaData.getColumnLabel(i + 1);
							ReflectionUtils.invokeSet(uniqueRow, columnName, value);
						}
					}
					return uniqueRow;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}	
			}
		});
	}
	
	/**
	 * search and return a value, in this case the table only got one row and one column
	 * @param Sql, query statement
	 * @param Params, Sql parameters
	 * @return result of the query
	 */
	public Object queryValue(String Sql, Object[] Params) {
		/*
		Connection con = DBManager.getConnection();
		Object value = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(Sql);
			JDBCUtils.handleParameters(ps, Params);
			// System.out.println(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				value = rs.getObject(1); // relates to count(*) in SQL query, only got one column
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.Close(rs, ps, con);
		}
		return value; // how many rows matching the requirements of the SQL query
		*/
		return executeQueryTemplate(Sql, Params, null, new CallBack() {
			@Override
			public Object doExecute(Connection con, PreparedStatement ps, ResultSet rs) {
				Object value = null;
				try {
					rs = ps.executeQuery();
					while (rs.next()) {
						value = rs.getObject(1);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return value;
			}
		});
	}
	
	/**
	 * search and return a number, in this case the table only got one row and one column
	 * @param Sql, query statement
	 * @param Params, Sql parameters
	 * @return result number of the query
	 */
	public Number queryNumber(String Sql, Object[] Params) {
		return (Number) queryValue(Sql, Params);
	}
	
	/**
	 * search the corresponding value in the table of database according to the given
	 * primary key
	 * @param ID, primary key
	 * @param Clazz, given Class object
	 * @return object of the class
	 */
	// select * from user where id = ? -> similar with delete query, find the delete()
	public Object queryByID(Object ID, Class Clazz) {
		TableInformation ti = TableContext.poClassTableMap.get(Clazz);
		// get the primary key
		ColumnInformation primaryKey = ti.getOnlyPrimaryKey();
		String Sql = "select * from " + ti.getTableName() + " where " + primaryKey.getName() + " = ?;";
		return queryUniqueRow(Sql, Clazz, new Object[] {ID});
	}
	
	/**
	 * paging query
	 * @param pageNum, the pageNum th pages
	 * @param Size, how many records on this page
	 * @return
	 */
	public abstract Object queryPagenate(int pageNum, int Size);
	
	// used with QueryFactory
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
