package com.jpa.main;

import com.jpa.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JPAMain{
	public static void main(String args[]) {
		
		User user=new User();
		
		user.setName("Peter Parket");
		user.setEmail("peter@gmail.com");
		user.setPassword("peter@123");
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("unit");
		EntityManager em=emf.createEntityManager();
		
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		
		try {
			
			em.persist(user);
			tx.commit();
			System.out.println("User created Successfully");
		}catch(Exception e) {
			if(tx!=null)
			System.out.println("Rollback Occurs");
		}
		em.close();
		emf.close();
	}
}