package com.hibernate.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.model.Department;
import com.hibernate.model.Employee;
import com.hibernate.utils.HibernateUtils;

public class Main{
	public static void main(String args[]) {
		
		Department dept = new Department();
		dept.setDeptName("CS");
		
		Department dept1=new Department();
		dept1.setDeptName("IT");
		
		Employee e1 = new Employee();
		e1.setEmpName("Andrew Anderson");
		
		Employee e2 = new Employee();
		e2.setEmpName("Peter Parker");
		
		dept.addDeparment(e2);
		dept1.addDeparment(e1);
		
		Session session=HibernateUtils.getSession().openSession();
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			session.persist(dept);
			session.persist(dept1);
			session.persist(e1);
			session.persist(e2);
			tx.commit(); // session.flush(); is called automatically when tx.commit() called, or u can write explicitly session.flush() before tx.commit()			
		}catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
				System.out.println("Rollback takes place");
			}
		}
	}
}