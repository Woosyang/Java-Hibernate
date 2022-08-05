package com.Core;
import java.sql.*;

/**
 * Used With Query.java
 * Use call-back mechanism to execute the select query method in Query 
 * @author Woo
 *
 */
public interface CallBack {
	public Object doExecute(Connection con, PreparedStatement ps, ResultSet rs);
}
