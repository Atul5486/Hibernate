package com.jpa.main;


import java.util.List;

import org.hibernate.Transaction;

import com.jpa.entity.Department;
import com.jpa.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Main{
	public static void main(String args[]) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("unit");
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=null;
		
		try {
			tx = em.getTransaction();
			tx.begin();
//			String query = "select e.empName, d.deptName from Employee e JOIN e.department d";	
			String query = "select e.empName, d.deptName from Employee e INNER JOIN e.department d";	
			//String query = "select e.empName, d.deptName from Employee e LEFT JOIN e.department d";	
			//String query = "select e.empName, d.deptName from Employee e Right JOIN e.department d";	
//			String query = "select e.empName, d.deptName from Department d Right JOIN d.employee e";	
			
			TypedQuery<Object[]> que = em.createQuery(query, Object[].class);
			List<Object[]> list = que.getResultList();
			for(Object[] row :  list) {
				System.out.println("EmployeeName : "+row[0]+" DepartmentName : "+row[1]);
			}
			
			tx.commit();
		}catch(Exception e) {
			if(tx!=null) {
				tx.rollback();
				System.out.println("Rollback takes place"+e);
			}
		}
		/*
		
		Department dept = new Department();
		dept.setDeptName("CS");
		
		Employee e1 = new Employee();
		e1.setEmpName("Andrew Anderson");
		
		Employee e2 = new Employee();
		e2.setEmpName("Peter Parker");
		
		dept.addDepartment(e1);
		dept.addDepartment(e2);
		
		try {
			tx=em.getTransaction();
			tx.begin();
			em.persist(dept);
			em.persist(e1);
			em.persist(e2);
			tx.commit();
			System.out.println("Data Inserted Successfully");
		}catch(Exception e) {
			System.out.println("Exception a inserting stage");
		}
		*/
		
	}
}