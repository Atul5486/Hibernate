package com.main;

import java.util.List;

import com.entity.Department;
import com.entity.Employee;

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
		/*
		Department d1=new Department();
		d1.setDeptName("CS");
		Employee emp1=new Employee();
		emp1.setEmpname("Peter parker");
		emp1.setDepartment(d1);
		d1.getEmployee().add(emp1);
		*/
		
		try {
			tx=em.getTransaction();
			tx.begin();
//			em.persist(emp1);
//			em.persist(d1);
//			String query="select e.empname, d.deptName from Employee e JOIN e.department d";
			
//			String query="select e.empname, d.deptName from Employee e INNER JOIN e.department d";
//			String query="select e.empname, d.deptName from Employee e LEFT JOIN e.department d";
			String query="select e.empname, d.deptName from Employee e RIGHT JOIN e.department d";
		
			
			TypedQuery<Object[]> que = em.createQuery(query, Object[].class);
			List<Object[]> list=que.getResultList();
			for(Object[] row:list) {
				System.out.println("Employee Details");
				System.out.println("Employee Name : "+row[0]+"\t Cricketer Name : "+row[1]);
			}
			tx.commit();
			
		}catch(Exception e) {
			System.out.println("Exception during transition"+e);
		}
	}
}