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
@SuppressWarnings("all")
public abstract class Query implements Cloneable {
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
	
	/**
	 * directly execute a DML statement
	 * @param Sql, Sql query
	 * @param Params, parameters
	 * @return how many SQL lines recorded will be changed after executing this SQL statement
	 */
	public int executeDML(String Sql, Object[] Params) {
		Connection con = DBManager.getConnection();
		int count = 0; 
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(Sql);
			JDBCUtils.handleParameters(ps, Params);
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
		Class c = Obj.getClass();
		TableInformation tableInformation = TableContext.poClassTableMap.get(c);
		StringBuilder Sql = new StringBuilder("insert into " + tableInformation.getTableName() + " ("); 
		Field[] fs = c.getDeclaredFields();
		List<Object> params = new ArrayList<>(); 
		int countNotNullField = 0; // count the number of the not null fields
		for (Field f : fs) {
			String fieldName = f.getName();
			Object fieldValue = ReflectionUtils.invokeGet(fieldName, Obj);
			if (fieldValue != null) {
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
		executeDML(Sql.toString(), params.toArray());
	}
	
	/**
	 * delete the data(one row) of the corresponding object belongs to the class in the 
	 * table(the record of the designated primary key ID) 
	 * @param Clazz, the Class Object of the class corresponding to the table
	 * @param ID, primary key
	 */
	public void delete(Class Clazz, Object ID) {
		TableInformation tableInformation = TableContext.poClassTableMap.get(Clazz);
		ColumnInformation onlyPrimaryKey = tableInformation.getOnlyPrimaryKey();
				
		String Sql = "delete from " + tableInformation.getTableName() + " where " + 
					  onlyPrimaryKey.getName() + " = ?;";
		executeDML(Sql, new Object[] {ID});
	}
	
	/**
	 * delete the data of the corresponding object(the class of the object maps to the table,
	 * the primary key value of the object maps to the data(one row) in the table)
	 * @param Obj, the object needs to be deleted
	 */
	public void delete(Object Obj) {
		Class c = Obj.getClass();
		TableInformation tableInformation = TableContext.poClassTableMap.get(c);
		ColumnInformation onlyPrimaryKey = tableInformation.getOnlyPrimaryKey();
		String fieldName = onlyPrimaryKey.getName();
		Object id = ReflectionUtils.invokeGet(fieldName, Obj);
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
		Class c = Obj.getClass();
		TableInformation tableInformation = TableContext.poClassTableMap.get(c);
		List<Object> params = new ArrayList<>(); 
		StringBuilder Sql = new StringBuilder("update " + tableInformation.getTableName());
		Sql.append(" set ");
		for (String f : fieldName) {
			Sql.append(f + " = ?, ");
			Object value = ReflectionUtils.invokeGet(f, Obj); 
			params.add(value); 
		}
		Sql.setCharAt(Sql.length() - 2, ' ');
		ColumnInformation onlyPrimaryKey = tableInformation.getOnlyPrimaryKey();
		Object primaryKeyValue = ReflectionUtils.invokeGet(onlyPrimaryKey.getName(), Obj);
		params.add(primaryKeyValue); 
		Sql.append("where " + onlyPrimaryKey.getName() + " = ?");
		return executeDML(Sql.toString(), params.toArray()); 
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
		return (List) executeQueryTemplate(Sql, Params, Clazz, new CallBack() {
				@Override
				public Object doExecute(Connection con, PreparedStatement ps, ResultSet rs) {
					List list = null;
					try {
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
	 * @param id, primary key
	 * @param clazz, given Class object
	 * @return object of the class
	 */
	public Object queryByID(Object id, Class clazz) {
		TableInformation ti = TableContext.poClassTableMap.get(clazz);
		ColumnInformation primaryKey = ti.getOnlyPrimaryKey();
		String Sql = "select * from " + ti.getTableName() + " where " + primaryKey.getName() + " = ?";
		return queryUniqueRow(Sql, clazz, new Object[] {id});
	}
	
	/**
	 * paging query
	 * @param pageNum, the pageNum th pages
	 * @param Size, how many records on this page
	 * @return a list of queried Objects
	 */
	public abstract Object queryPagenate(int pageNum, int Size);

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
