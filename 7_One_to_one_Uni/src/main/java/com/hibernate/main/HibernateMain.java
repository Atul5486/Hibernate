package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Passport;
import com.hibernate.model.Student;
import com.hibernate.utils.HibernateUtils;

public class HibernateMain{
	public static void main(String args[]) {
		Student student = new Student();
		student.setUsername("Peter Parker");
		student.setEmail("peter@gmail.com");
		student.setPassword("peter@123");
		student.setAddress("Indore Madhya Pradesh");
		
		Passport passport = new Passport();
		passport.setPassportNumber("PNO_124PETER");
		passport.setStudent(student);
		
		student.setPassport(passport);
		
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			 session.persist(passport);
			session.persist(student);
			tx.commit(); // session.flush(); is called automatically when tx.commit() called, or u can write explicitly session.flush() before tx.commit()

			
		}catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
				System.out.println("Rollback takes place");
			}
		}
//		Student stud = session.get(Student.class, 1);
//		System.out.println("Passport Object : "+stud.getPassport());
//		System.out.println("Passport Number : "+stud.getPassport().getPassportNumber());
		
	}

}