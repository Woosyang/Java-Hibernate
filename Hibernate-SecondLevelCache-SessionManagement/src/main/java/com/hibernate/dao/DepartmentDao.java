package com.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hibernate.entity.Department;
import com.hibernate.util.HibernateUtils;

// used with Session Management
public class DepartmentDao {
	
	private SessionFactory sessionFactory;
	
	// if we want to pass the session object to save() as parameter, it means we need
	// to get the session from upper layer(Service level). It can not decouple the service
	// class from Hibernate API. Not recommended
	public void save(Session session, Department dept) {
		session.save(dept);
	}
	
	// this will make the session management easier
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory; 
	}
	
	public void save(Department dept) { // used with testManageSession()
		// get the session object internally
		// get the session which is bound to current thread
		// advantage: 1. no need to import Session from outside 
		//            2. several DAO method can use the same Transaction
		Session session = HibernateUtils.getInstance().getSession();
		System.out.println(session.hashCode());
		session.save(dept); // need to open transaction
	}
}
