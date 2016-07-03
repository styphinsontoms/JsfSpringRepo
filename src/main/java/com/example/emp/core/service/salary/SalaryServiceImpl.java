package com.example.emp.core.service.salary;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

import com.example.dao.entity.Salary;

@Component
public class SalaryServiceImpl implements SalaryService {

	@Override
	public Salary calculateSalary(double basicSalary, long empId,
			String designation, long empYrOfExperience) {
		double pf = 0.0;
		double netSalary = 0.0;
		double gradeSalary = 0.0;
		double deductions = 0.0;
		double tax = 0.0;
		double gross = 0.0;

		pf = basicSalary * SalaryDetailsConstants.PFPERCENTAGE;
		if (designation.equalsIgnoreCase("PROGRAMMER"))
			gradeSalary = SalaryDetailsConstants.CLASS1INC;
		else if (designation.equalsIgnoreCase("LEAD"))
			gradeSalary = SalaryDetailsConstants.CLASS2INC;
		else if (designation.equalsIgnoreCase("MANAGER"))
			gradeSalary = SalaryDetailsConstants.CLASS3INC;
		netSalary = basicSalary + pf + gradeSalary;
		deductions = pf + tax;
		gross = netSalary - deductions;
		Salary salary = new Salary();
		salary.setBasicSalarry(basicSalary);
		salary.setPfOfSalary(pf);
		salary.setNetSalary(netSalary);
		salary.setGrossSalary(gross);
		return salary;
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
