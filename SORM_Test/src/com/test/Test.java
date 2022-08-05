package com.test;
import java.sql.Date;
import java.util.List;
import com.Core.*;
import com.test.po.Employee;
import com.vo.EmployeeVo;

/**
 * Test the handwritten ORM framework
 * @author Woo
 *
 */
@SuppressWarnings("all")
public class Test {
	
	public static void add() {
		Employee e = new Employee();
		e.setAge(18);
		e.setBonus(80000);
		e.setCheckInDate(new Date(835797515L));
		e.setDeptID(3);
		e.setID(20);
		e.setName("Huang");
		e.setSalary(8.08);
		Query q = QueryFactory.createQuery02();
		q.insert(e);
	}
	
	public static void selectValue() {
		Query q = QueryFactory.createQuery02();
		Object value = q.queryValue("select * from employee where Salary > ?", new Object[] {10});
		System.out.println(value);
	}
	
	public static void remove() {
		Employee e = new Employee();
		e.setAge(18);
		e.setBonus(80000);
		e.setCheckInDate(new Date(835797515L));
		e.setDeptID(3);
		e.setID(20);
		e.setName("Huang");
		e.setSalary(8.08);
		Query q = QueryFactory.createQuery02();
		q.delete(e);
	}
	
	public static void modify() {
		Employee e = new Employee();
		e.setCheckInDate(new Date(835797515L));
		e.setID(2);
		Query q = QueryFactory.createQuery02();
		q.update(e, new String[] {"CheckInDate"});
	}
	
	public static void searchOne() {
		Query q = QueryFactory.createQuery02();
		Employee e = (Employee) q.queryUniqueRow("select * from employee where id = ?", Employee.class, new Object[] {2});
		System.out.println(e.getName());
	}
	
	public static void searchMulti() {
		Query q = QueryFactory.createQuery02();
		List<Employee> e = (List) q.queryRows("select * from employee where id > ? and id < ?", Employee.class, new Object[] {1, 4});
		for (Employee a : e) {
			System.out.println(a.getName());
		}
	}
	
	// query statement with joining another table
	public static void searchJoin() {
		String Sql = "select e.ID, e.Name, e.Age, Bonus + Salary 'Income', "
				   + "d.Name 'DeptName', d.Address 'DeptAddr' from Employee e "
				   + "join Department d on e.DeptID = d.ID;";
		Query q = QueryFactory.createQuery02();
		List<EmployeeVo> e = q.queryRows(Sql, EmployeeVo.class, null);
		for (EmployeeVo a : e) {
			System.out.println(a.getDeptName() + " " + a.getDeptAddr());
		}
	}
	
	public static void selectByID() {
		Query q = QueryFactory.createQuery02();
		Employee e = (Employee) q.queryByID(4, Employee.class);
		System.out.println(e.getName());
	}
	
	public static void main(String[] args) {
		// generate the po class
		// TableContext.updateJavaPOFile();
		// add();
		// selectValue();
		// remove();
		// modify();
		// searchOne();
		// searchMulti();
		// searchJoin();
		selectByID();
	}
}
