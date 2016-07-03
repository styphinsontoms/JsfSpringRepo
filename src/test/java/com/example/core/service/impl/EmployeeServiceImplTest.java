package com.example.core.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.core.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/applicationContext.xml")
public class EmployeeServiceImplTest {

	@Autowired
	EmployeeService service;

	@Test
	public void emptyTest() {
		System.out.println("Empty Test Run");
	}

	@Test
	public void getEmpSalaryChart() {
		System.out.println("getEmpSalaryChart" + service.getEmpSalaryChart("column"));
	}

	
	@Test
	public void getEmpJoinDateChart() {
		System.out.println("salaryJsonData" + service.getEmpJoinDateChart("column"));
	}
	
	
	@Test
	public void getEmpSalaryForExperienceChart() {
		System.out.println("getEmpSalaryForExperienceChart" + service.getEmpSalaryForExperienceChart("column"));
	}
}
