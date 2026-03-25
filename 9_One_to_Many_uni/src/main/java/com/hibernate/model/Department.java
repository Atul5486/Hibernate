package com.hibernate.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="department_bi")
public class Department{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int did;
	
	@Column(name="dept_name")
	String deptName;
	
	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

//	@OneToMany(cascade = CascadeType.ALL) //Unidirectional
//	@JoinColumn(name="dept_id")  // Use in unidirectional
	
	@OneToMany(mappedBy = "department")  //Bidirectional
	
	Set<Employee> employees=new HashSet<Employee>();
	
	public void addDeparment(Employee e) {
		e.setDepartment(this);
		employees.add(e);
		this.setEmployees(employees);;
		
	}
	
	
	
}