package com.jpa.main;

import java.util.List;
import java.util.Scanner;

import com.jpa.entity.Department;
import com.jpa.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class JpaMain{
	public static void main(String args[]) {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("unit");
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		Scanner inp=new Scanner(System.in);
		Employee emp=new Employee();
		select(inp,emp,tx,em);
		tx.begin();
		
	}
	
	static void select(Scanner inp,Employee emp,EntityTransaction tx,EntityManager em){
		boolean set=true;
		while(set) {
			System.out.println("Enter a number to perform operaiton");
			System.out.println(
					"1.Create Employee \n2.Update Employee \n3.Delete Employee \n4.Search Employee by email \n" +
					"5.List of all Employee \n6.Search Employee in department \n7.List All department \n" +
					"8.Filter by Name \n9.Filter by Age \n10.Filter by Salary \n11.Filter by Department \n12.Exit"
					);
int choice=inp.nextInt();
			switch(choice) {
			case 1:createEmployee(inp,emp,tx,em);break;
			case 2:updateEmployee(inp,em,tx);break;
			case 3:deleteEmployee(inp,em,tx);break;
			case 4:searchEmployee(inp,em,tx);break;
			case 5:readEmployee(inp,em,tx);break;
			case 6:searchByDeptName(inp,em);break;
			case 7:getDept(inp,em);break;
			case 8: filterByName(inp, em); break;
			case 9: filterByAge(inp, em); break;
			case 10: filterBySalary(inp, em); break;
			case 11: filterByDepartment(inp, em); break;
			case 12: System.out.println("Exiting...!!!"); set=false; break;
			default:
				System.out.println("Invalid Choice");
			}
		}
	}
	
	static void createEmployee(Scanner inp, Employee emp, EntityTransaction tx, EntityManager em) {
    inp.nextLine();

    System.out.println("Enter Name");
    String name = inp.nextLine();
    if (!isValidName(name)) {
        System.out.println("Invalid name (min 3 characters)");
        return;
    }

    System.out.print("Enter emp email : ");
    String email = inp.nextLine();
    if (!isValidEmail(email)) {
        System.out.println("❌ Invalid email format");
        return;
    }

    // check duplicate email
    Long count = em.createQuery(
            "select count(e) from Employee e where e.email=:email", Long.class)
            .setParameter("email", email)
            .getSingleResult();

    if (count > 0) {
        System.out.println("Email already exists");
        return;
    }

    System.out.println("Enter Department name");
    String deptName = inp.nextLine();
    if (deptName.trim().isEmpty()) {
        System.out.println("Department cannot be empty");
        return;
    }

    System.out.println("Enter age");
    int age = inp.nextInt();
    if (!isValidAge(age)) {
        System.out.println("Age must be between 18 and 60");
        return;
    }

    System.out.println("Enter Salary");
    int salary = inp.nextInt();
    if (!isValidSalary(salary)) {
        System.out.println("Salary must be greater than 0");
        return;
    }

    try {
        tx.begin();

        Department department = new Department();
        department.setDeptName(deptName);

        emp = new Employee();
        emp.setEmpName(name);
        emp.setEmail(email);
        emp.setAge(age);
        emp.setSalary(salary);
        emp.setDepartment(department);

        em.persist(department);
        em.persist(emp);

        tx.commit();
        System.out.println("Employee created successfully");

    } catch (Exception e) {
        tx.rollback();
        System.out.println("Error: " + e.getMessage());
    }
}

	static void updateEmployee(Scanner inp, EntityManager em, EntityTransaction tx) {
    inp.nextLine();

    System.out.print("Enter employee email : ");
    String email = inp.nextLine();

    if (!isValidEmail(email)) {
        System.out.println("Invalid email");
        return;
    }

    Employee emp = em.createQuery(
            "select e from Employee e where e.email=:email", Employee.class)
            .setParameter("email", email)
            .getResultStream()
            .findFirst()
            .orElse(null);

    if (emp == null) {
        System.out.println("Employee not found");
        return;
    }

    System.out.print("Enter employee name : ");
    String name = inp.nextLine();
    if (!isValidName(name)) return;

    System.out.print("Enter employee age : ");
    int age = inp.nextInt();
    if (!isValidAge(age)) return;

    System.out.print("Enter employee salary : ");
    int salary = inp.nextInt();
    if (!isValidSalary(salary)) return;

    try {
        tx.begin();
        emp.setEmpName(name);
        emp.setAge(age);
        emp.setSalary(salary);
        tx.commit();
        System.out.println("Employee updated successfully");
    } catch (Exception e) {
        tx.rollback();
        System.out.println("Update failed");
    }
}

	static void readEmployee(Scanner inp,EntityManager em,EntityTransaction tx){
		try {
			String query = "select e from Employee e";
		 	TypedQuery<Employee> q = em.createQuery(query,Employee.class);
		 	List<Employee> emps = q.getResultList();
		 	
		 	System.out.println("\n----------------------- ALL EMPLOYEE DETAILS ----------------------");
		 	System.out.println("\n==============================================================================================");
		 	
		 	for(Employee emp : emps) {
		 		System.out.println(emp.getEmpName()+"\t" +emp.getEmail()+"\t" +emp.getAge()+"\t"+ emp.getDepartment().getDeptName());
		 	}
		 	System.out.println("================================================================================================\n");
		 	inp.nextLine();
			 System.out.println("\nPress ENTER to move further...");
		     inp.nextLine(); 
		}catch(Exception e) {
			System.out.println("Exception during reading employee data : "+e);
		}
	}
	static void deleteEmployee(Scanner inp, EntityManager em, EntityTransaction tx) {
    inp.nextLine();
    System.out.println("Enter employee Email");
    String email = inp.nextLine();

    if (!isValidEmail(email)) {
        System.out.println("Invalid email");
        return;
    }

    try {
        tx.begin();
        int rows = em.createQuery("delete from Employee e where e.email=:email")
                .setParameter("email", email)
                .executeUpdate();
        tx.commit();

        if (rows == 0)
            System.out.println("Employee not found");
        else
            System.out.println("Employee deleted");

    } catch (Exception e) {
        tx.rollback();
        System.out.println("Delete failed");
    }
}

	static void searchEmployee(Scanner inp, EntityManager em, EntityTransaction tx) {
    inp.nextLine();
    System.out.println("Enter Email ");
    String email = inp.nextLine();

    if (!isValidEmail(email)) {
        System.out.println("Invalid email");
        return;
    }

    try {
        TypedQuery<Employee> query =
                em.createNamedQuery("User1.findByEmail", Employee.class);
        query.setParameter("email", email);
        Employee emp = query.getSingleResult();

        System.out.println("Name : " + emp.getEmpName());
        System.out.println("Email : " + emp.getEmail());
        System.out.println("Salary : " + emp.getSalary());
        
        inp.nextLine();
		 System.out.println("\nPress ENTER to move further...");
	     inp.nextLine(); 

    } catch (jakarta.persistence.NoResultException e) {
        System.out.println("Employee not found");
    }
}

	static void searchByDeptName(Scanner inp,EntityManager em) {
		try {
		String query="select e.department from Employee e";
		TypedQuery<Department> q=em.createQuery(query,Department.class);
		List<Department> deptList=q.getResultList();
		System.out.println("List of departments");
		for(Department dept:deptList) {
			System.out.println(dept.getDeptName());
		}
		inp.nextLine();
		System.out.println("Enter Department Name");
		String deptName=inp.nextLine();
		String search = "select e.empName, d.deptName from Employee e LEFT JOIN e.department d where d.deptName=:deptname";
		TypedQuery<Object[]> que = em.createQuery(search, Object[].class);
		que.setParameter("deptname", deptName);
		List<Object[]> list = que.getResultList();
		if(list.size()==0) {
			System.out.println("\nNo Employee found\n");
			return;
		}
		System.out.println("\nList of employee in "+deptName);
		for(Object[] row :  list) {
			System.out.println("EmployeeName : "+row[0]+" DepartmentName : "+row[1]);
		}
		 inp.nextLine();
		 System.out.println("\nPress ENTER to move further...");
	     inp.nextLine(); 
		}catch(Exception e) {
			System.out.println("Exception during search in dept"+e);
		}
		
	}
	static void getDept(Scanner inp,EntityManager em) {
		try {
			String query="select e.department from Employee e";
			TypedQuery<Department> q=em.createQuery(query,Department.class);
			List<Department> deptList=q.getResultList();
			System.out.println("\nList of departments");
			int i=1;
			for(Department dept:deptList) {
				System.out.println(i+"\t"+dept.getDeptName());
				i++;
			}
			inp.nextLine();
			 System.out.println("\nPress ENTER to move further...");
		     inp.nextLine();   // waits for Enter key
	}catch(Exception e) {
		System.out.println("Exception occur During search department : "+e);
	}
	}
	
	static boolean isValidEmail(String email) {
	    return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
	}

	static boolean isValidName(String name) {
	    return name != null && name.trim().length() >= 3;
	}

	static boolean isValidAge(int age) {
	    return age >= 18 && age <= 60;
	}

	static boolean isValidSalary(int salary) {
	    return salary > 0;
	}
	static void filterByName(Scanner inp, EntityManager em) {
	    inp.nextLine();
	    System.out.println("Enter employee name");
	    String name = inp.nextLine();

	    TypedQuery<Employee> q = em.createQuery(
	        "select e from Employee e where e.empName like :name",
	        Employee.class
	    );
	    q.setParameter("name", "%" + name + "%");

	    List<Employee> list = q.getResultList();

	    if (list.isEmpty()) {
	        System.out.println("No employee found");
	        return;
	    }

	    for (Employee e : list) {
	        System.out.println(e.getEmpName() + " | " + e.getEmail());
	    }
	    inp.nextLine();
		 System.out.println("\nPress ENTER to move further...");
	     inp.nextLine(); 
	}
	
	static void filterByDepartment(Scanner inp, EntityManager em) {
	    inp.nextLine();
	    System.out.println("Enter department name");
	    String deptName = inp.nextLine();

	    TypedQuery<Employee> q = em.createQuery(
	        "select e from Employee e join e.department d where d.deptName = :dept",
	        Employee.class
	    );
	    q.setParameter("dept", deptName);

	    List<Employee> list = q.getResultList();

	    if (list.isEmpty()) {
	        System.out.println("No employee found in this department");
	        return;
	    }

	    for (Employee e : list) {
	        System.out.println(e.getEmpName() + " | " + e.getDepartment().getDeptName());
	    }
	    inp.nextLine();
		 System.out.println("\nPress ENTER to move further...");
	     inp.nextLine(); 
	}
	static void filterByAge(Scanner inp, EntityManager em) {
	    System.out.println("Enter minimum age");
	    int minAge = inp.nextInt();

	    System.out.println("Enter maximum age");
	    int maxAge = inp.nextInt();

	    TypedQuery<Employee> q = em.createQuery(
	        "select e from Employee e where e.age between :min and :max",
	        Employee.class
	    );
	    q.setParameter("min", minAge);
	    q.setParameter("max", maxAge);

	    List<Employee> list = q.getResultList();

	    if (list.isEmpty()) {
	        System.out.println("No employee found");
	        return;
	    }

	    for (Employee e : list) {
	        System.out.println(e.getEmpName() + " | Age: " + e.getAge());
	    }
	    inp.nextLine();
		 System.out.println("\nPress ENTER to move further...");
	     inp.nextLine(); 
	}
	static void filterBySalary(Scanner inp, EntityManager em) {
	    System.out.println("Enter minimum salary");
	    int minSalary = inp.nextInt();

	    System.out.println("Enter maximum salary");
	    int maxSalary = inp.nextInt();

	    TypedQuery<Employee> q = em.createQuery(
	        "select e from Employee e where e.salary between :min and :max",
	        Employee.class
	    );
	    q.setParameter("min", minSalary);
	    q.setParameter("max", maxSalary);

	    List<Employee> list = q.getResultList();

	    if (list.isEmpty()) {
	        System.out.println("No employee found");
	        return;
	    }

	    for (Employee e : list) {
	        System.out.println(e.getEmpName() + " | Salary: " + e.getSalary());
	    }
	    
	    inp.nextLine();
		 System.out.println("\nPress ENTER to move further...");
	     inp.nextLine(); 
	}


}