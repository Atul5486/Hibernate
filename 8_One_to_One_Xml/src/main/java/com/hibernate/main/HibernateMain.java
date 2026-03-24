package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Passport;
import com.hibernate.model.Student;
import com.hibernate.utils.HibernateUtils;

public class HibernateMain{
	public static void main(String args[]) {
		
		Session session= HibernateUtils.getSessionFactory().openSession();
		Transaction tx=null;
		
		Student std=new Student();
		
		std.setName("Gourav Kothari");
		std.setEmail("gourav@gmail.com");
		
		Passport ps=new Passport();
		ps.setPno("gourav1234");
		
		std.setPs(ps);	
		
		try {
			tx=session.beginTransaction();
			session.persist(std);
			session.persist(ps);
			tx.commit();
			}catch(Exception e) {
				if(tx!=null) {
					tx.rollback();
				}
			System.out.println("Exception : "+e);
			e.printStackTrace();
		}
	}
}