package com.main;

import java.util.List;
import java.util.Scanner;

import com.entity.Cricketer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class Main{
	public static void main(String args[]){
		EntityManagerFactory emf =  Persistence.createEntityManagerFactory("unit");
	 	EntityManager em = emf.createEntityManager();
	 	EntityTransaction tx = null;
	 	try {
		 	tx = em.getTransaction();
		 	tx.begin();

		 	CriteriaBuilder criteriaBuilder =  em.getCriteriaBuilder();
		 	CriteriaQuery<Cricketer> query = criteriaBuilder.createQuery(Cricketer.class);
		 	Root<Cricketer> root =  query.from(Cricketer.class);
		 	query.select(root);
		 	

		 	TypedQuery<Cricketer> q =  em.createQuery(query);
		 	List<Cricketer> list = q.getResultList();
		 	for(Cricketer cir : list) {
		 		System.out.println("\nUid : "+cir.getName());
		 		System.out.println("Username : "+cir.getName());
		 		System.out.println("Email : "+cir.getWicket());
		 		System.out.println("Password : "+cir.getRuns());
		 		System.out.println("Salary : "+cir.getAge());
		 		System.out.println("Address : "+cir.getCid());
		 	}
		 	
		 	tx.commit();
	 	}catch(Exception e) {
	 		if(tx!=null)
	 			tx.rollback();
	 		System.out.println("Rollback takes place..!!");
	 		System.out.println("Exception : "+e);
	 	}
	 	em.close();
	 	emf.close();
	}
}