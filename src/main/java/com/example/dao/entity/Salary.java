package com.example.dao.entity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Salary extends AbstractBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7508769277337764560L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SALARY_ID")
	private long slaryId;

	@Column(name = "BASIC_SALARY")
	private double basicSalarry;

	@Column(name = "NET_SALARY")
	private double netSalary;

	@Column(name = "GROSS_SALARY")
	private double grossSalary;

	@Column(name = "DEDUCTIONS")
	private double deductions;

	@Column(name = "TAX_ON_SALARY")
	private double taxOnSalary;

	@Column(name = "PF_OF_SALARY")
	private double pfOfSalary;

    @OneToOne(mappedBy = "salary")
	private Employee employee;
	
	public long getSlaryId() {
		return slaryId;
	}

	public void setSlaryId(long slaryId) {
		this.slaryId = slaryId;
	}

	public double getBasicSalarry() {
		return basicSalarry;
	}

	public void setBasicSalarry(double basicSalarry) {
		this.basicSalarry = basicSalarry;
	}

	public double getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(double netSalary) {
		this.netSalary = netSalary;
	}

	public double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(double grossSalary) {
		this.grossSalary = grossSalary;
	}

	public double getDeductions() {
		return deductions;
	}

	public void setDeductions(double deductions) {
		this.deductions = deductions;
	}

	public double getTaxOnSalary() {
		return taxOnSalary;
	}

	public void setTaxOnSalary(double taxOnSalary) {
		this.taxOnSalary = taxOnSalary;
	}

	public double getPfOfSalary() {
		return pfOfSalary;
	}

	public void setPfOfSalary(double pfOfSalary) {
		this.pfOfSalary = pfOfSalary;
	}

	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	@Override
	public String toString() {
		return "Salary [slaryId=" + slaryId + ", basicSalarry=" + basicSalarry
				+ ", netSalary=" + netSalary + ", grossSalary=" + grossSalary
				+ ", deductions=" + deductions + ", taxOnSalary=" + taxOnSalary
				+ ", pfOfSalary=" + pfOfSalary + ", employee=" + employee + "]";
	}

	@PostConstruct
	public void initBean()
	{
		System.out.println("Initializing Bean"+this.getClass().toString());
	}
	
	@PreDestroy
	public void destroyBean()
	{
		System.out.println("Destroying Bean"+this.getClass().toString());
	}
}
