package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.User;
import com.hibernate.utils.HibernateUtils;

public class HibernateMain{
	public static void main(String args[]) {
		
		User user=new User();
		user.setName("Andrew Anderson");
		user.setEmail("Andrew@gmail.com");
		user.setAge(20);
		user.setPassword("Airtel@123");
		
		Session session=HibernateUtils.getSession().openSession();
		Transaction tx=null;
		try {
			tx=session.beginTransaction();
			session.persist(user);
			tx.commit();
			System.out.println("User created successfully");
		}catch(Exception e) {
			if(tx!=null) tx.rollback();
		}
		
	}
}