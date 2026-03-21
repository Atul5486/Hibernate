package com.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.User;

public class HibernateUtils{
	public static SessionFactory getSession() {
		SessionFactory sessionFactory=null;
		try {
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass(User.class);
			sessionFactory =  configuration.configure("hibernate.cfg.xml").buildSessionFactory();
			System.out.println("sessionfactory : "+sessionFactory);
		}catch(Exception e) {
			System.out.println("Exception : "+e);
			e.printStackTrace();	}
		return sessionFactory;
	}
}