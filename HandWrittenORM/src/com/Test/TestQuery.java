package com.Test;
import java.util.List;

import com.Core.*;
import com.Vo.EmployeeVo;

/**
 * Test the QueryFactory class
 * @author Woo
 *
 */
@SuppressWarnings("all")
public class TestQuery {
	public static void main(String[] args) {
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
}
