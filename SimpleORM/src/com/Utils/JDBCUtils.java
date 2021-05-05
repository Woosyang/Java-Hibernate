package com.Utils;
import java.sql.*;

/**
 * Encapsulate the common methods of the operations in JDBC(select, delete...)
 * @author Woo
 *
 */
public class JDBCUtils {
	/**
	 * set the parameters for the Sql query
	 * @param ps, prepared statement for the Sql query
	 * @param params, object arrays of the parameters
	 */
	public static void handleParameters(PreparedStatement ps, Object[] params) {
		if (params != null) {
			for (int i = 0; i < params.length; i ++) {
				try {
					ps.setObject(i + 1, params[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
