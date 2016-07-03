package com.example.dozer.dest.pojo;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class EmployeeDest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long employeeId;

	private String employeeName;

	private Date employeeHireDate;

	private int empYrOfExperience;

	private String employeeDesignation;

	
	private long slaryId;
	
	private double netSalary;
	
	private String street;
	
	private String city;
	
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

			
	public long getSlaryId() {
		return slaryId;
	}

	public void setSlaryId(long slaryId) {
		this.slaryId = slaryId;
	}

	public double getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(double netSalary) {
		this.netSalary = netSalary;
	}
	
	

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "EmployeeDest [employeeId=" + employeeId + ", employeeName="
				+ employeeName + ", employeeHireDate=" + employeeHireDate
				+ ", empYrOfExperience=" + empYrOfExperience
				+ ", employeeDesignation=" + employeeDesignation + ", slaryId="
				+ slaryId + ", netSalary=" + netSalary + ", street=" + street
				+ ", city=" + city + "]";
	}



	
	

}
