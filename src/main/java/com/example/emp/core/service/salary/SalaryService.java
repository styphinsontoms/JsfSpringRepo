package com.example.emp.core.service.salary;

import com.example.dao.entity.Salary;

public interface SalaryService {

	public Salary calculateSalary(double basicSalary, long empId,
			String designation, long empYrOfExperience);

}
