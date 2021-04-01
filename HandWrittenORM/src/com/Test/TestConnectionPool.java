package com.Test;
import java.util.*;
import com.Core.*;
import com.Vo.EmployeeVo;

/**
 * Test the performance of using DBConnectionPool
 * @author Woo
 *
 */
@SuppressWarnings("all")
public class TestConnectionPool {
	
	public static void testQuery() {
		Query Q = QueryFactory.createQuery02();
		// test code in MySqlQuery()
		String Sql = "select e.ID, e.Name, e.Age, Bonus + Salary 'Income', "
				   + "d.name 'DeptName', d.address 'DeptAddr' from Employee e join "
				   + "Department d on e.DeptID = d.ID;";
		Class c = EmployeeVo.class;
		// no need to new MySqlQuery() anymore
		List<EmployeeVo> list = Q.queryRows(Sql, c, null);
		for (EmployeeVo e : list) {
			System.out.println(e.getName() +", " + e.getIncome() + ", " + e.getDeptAddr());
		}
	}
	
	public static void main(String[] args) {
		TableContext.updateJavaPOFile();
		/*
		// test how long will it take to build a connection
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000; i ++) {
			testQuery(); // right now: 4160, former test: 27630 ms
		}
		long end = System.currentTimeMillis();
		// the duration of building connection without connection pool
		// based on the method original getConnection() and Close() in DBManager:
		// 27630 ms
		System.out.println("building the connection without connection pool costs: " + (end - start) + "ms");
		*/
	}
}
