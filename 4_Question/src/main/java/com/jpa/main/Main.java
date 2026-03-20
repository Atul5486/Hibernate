package com.jpa.main;

import com.jpa.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class Main{
	public static void main(String args[]) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("unit");
		
		EntityManager em=emf.createEntityManager();
		CriteriaBuilder cb=em.getCriteriaBuilder();

		//Minimum Salary
		try {
			CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
			Root<Employee> empl = query.from(Employee.class);
			query.select(cb.min(empl.get("salary")));
			TypedQuery<Integer> q=em.createQuery(query);
		 	Integer minSalary = q.getSingleResult();
		 	System.out.println("Minimum salary : "+minSalary);
		 	
		}catch(Exception e) {
			System.out.println("Exception in aggregate");
		}
		//Maximum salary
		try {
			CriteriaQuery<Integer> query=cb.createQuery(Integer.class);
			Root<Employee> empl=query.from(Employee.class);
			query.select(cb.max(empl.get("salary")));
			TypedQuery<Integer> q=em.createQuery(query);
			Integer maxSalary=q.getSingleResult();
			System.out.println("Maximum Salary is : "+maxSalary);
			
		}catch(Exception e) {
			System.out.println("Exception Occurs at max");
		}
		
		//Sum
		
		try {
			CriteriaQuery<Integer> query=cb.createQuery(Integer.class);
			Root<Employee> empl=query.from(Employee.class);
			query.select(cb.sum(empl.get("salary")));
			
			TypedQuery<Integer> q=em.createQuery(query);
			Integer total=q.getSingleResult();
			System.out.println("Sum Salary is : "+total);
			
		}catch(Exception e) {
			System.out.println("Exception Occurs at sum");
		}
		
		//count
		try {
			CriteriaQuery<Long> query=cb.createQuery(Long.class);
			Root<Employee> empl=query.from(Employee.class);
			query.select(cb.count(empl.get("salary")));
			
			TypedQuery<Long> q=em.createQuery(query);
			Long count=q.getSingleResult();
			System.out.println("Number of employee: "+count);
			
		}catch(Exception e) {
			System.out.println("Exception Occurs at count");
		}
		
		// avg
		try {
			CriteriaQuery<Double> query = cb.createQuery(Double.class);
		 	Root<Employee> root =  query.from(Employee.class);
		 	query.select(cb.avg(root.get("salary")));
		 	
		 	TypedQuery<Double> q =  em.createQuery(query);
		 	Double avgSalary = q.getSingleResult();
		 	System.out.println("Average salary : "+avgSalary);
		}catch(Exception e) {
			System.out.println("Exception Occurs at avg");
		}
	}
}