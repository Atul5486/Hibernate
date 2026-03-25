package com.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.Department;
import com.hibernate.model.Employee;

public class HibernateUtils{
	public static SessionFactory getSession() {
		SessionFactory sessionFactory=null;
		try {
			Configuration cfg=new Configuration();
			cfg.addAnnotatedClass(Employee.class);
			cfg.addAnnotatedClass(Department.class);
			sessionFactory=cfg.configure("hibernate.cfg.xml").buildSessionFactory();
		}catch(Exception e) {
			System.out.println("Exception during creating session factory object "+e);
		}
		
		return sessionFactory;
	}
}