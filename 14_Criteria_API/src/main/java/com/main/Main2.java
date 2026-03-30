package com.main;

import java.util.List;
import com.entity.Cricketer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class Main2{
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
//		 	query.select(root).where(criteriaBuilder.gt(root.get("runs"),500));
//		 	query.select(root).where(criteriaBuilder.lt(root.get("runs"),500));
//		 	query.select(root).where(criteriaBuilder.like(root.get("name"),"v%"));
//		 	query.select(root).orderBy(criteriaBuilder.asc(root.get("runs")));
//		 	query.select(root).orderBy(criteriaBuilder.desc(root.get("age")));
		 	
		 	
		 	Predicate p1 =  criteriaBuilder.like(root.get("name"), "v%");
		 	Predicate p2 =  criteriaBuilder.greaterThan(root.get("runs"), 500);
		 	
		 	query.where(criteriaBuilder.or(p1,p2));
		 	TypedQuery<Cricketer> q =  em.createQuery(query);
		 	List<Cricketer> list = q.getResultList();
		 	
		 	
		 	for(Cricketer cir : list) {
		 		System.out.println("\nID : "+cir.getCid());
		 		System.out.println("Name : "+cir.getName());
		 		System.out.println("Wickets : "+cir.getWicket());
		 		System.out.println("Runs : "+cir.getRuns());
		 		System.out.println("Age : "+cir.getAge());
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