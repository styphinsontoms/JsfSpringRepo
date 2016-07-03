package com.example.dao.entity;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Employee extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -641295672106004591L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMP_ID")
	private long employeeId;

	@Column(name = "EMP_NAME")
	private String employeeName;

	@Column(name = "EMP_HIRE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date employeeHireDate;

	@OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "FK_SALARY_ID", referencedColumnName = "SALARY_ID")
	private Salary salary;

	@Column(name = "EMP_YR_OF_EXP")
	private int empYrOfExperience;

	@Column(name = "EMP_DESIGNATION")
	private String employeeDesignation;

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Date getEmployeeHireDate() {
		return employeeHireDate;
	}

	public void setEmployeeHireDate(Date employeeHireDate) {
		this.employeeHireDate = employeeHireDate;
	}

	public Salary getSalary() {
		return salary;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}


	public int getEmpYrOfExperience() {
		return empYrOfExperience;
	}

	public void setEmpYrOfExperience(int empYrOfExperience) {
		this.empYrOfExperience = empYrOfExperience;
	}

	public String getEmployeeDesignation() {
		return employeeDesignation;
	}

	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}

	@PostConstruct
	public void initBean() {
		System.out.println("Initializing Bean" + this.getClass().toString());
	}

	@PreDestroy
	public void destroyBean() {
		System.out.println("Destroying Bean" + this.getClass().toString());

	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName="
				+ employeeName + ", employeeHireDate=" + employeeHireDate
				+ ", salary=" + salary + ", empYrOfExperience="
				+ empYrOfExperience + ", employeeDesignation="
				+ employeeDesignation + ", getDateModified()="
				+ getDateModified() + ", getUserModified()="
				+ getUserModified() + "]";
	}

	

}
