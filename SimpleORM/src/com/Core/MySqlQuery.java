package com.Core;
import java.lang.reflect.*;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import com.Bean.*;
import com.Utils.*;

/**
 * Used to make a query based on MySql
 * @author Woo
 *
 */
@SuppressWarnings("all")
public class MySqlQuery extends Query {
	@Override
	public Object queryUniqueRow(String sql, Class clazz, Object[] params) {
		List list = new MySqlQuery().queryRows(sql, clazz, params);
		return (list.size() == 0 || list == null) ? null : list.get(0);
	}
	
	@Override
	public Object queryPagenate(int pageNum, int Size) {
		return null;
	}
}
