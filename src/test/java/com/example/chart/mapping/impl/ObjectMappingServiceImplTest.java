package com.example.chart.mapping.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.chart.mapping.ObjectMappingService;
import com.example.chart.pojo.Chart;
import com.example.chart.pojo.ChartDataSource;
import com.example.core.service.impl.JsonServiceImpl;
import com.example.dao.entity.Address;
import com.example.dao.entity.Employee;
import com.example.dao.entity.Salary;
import com.example.dozer.dest.pojo.EmployeeDest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/applicationContext.xml")
public class ObjectMappingServiceImplTest {

	@Autowired
	ObjectMappingService objectMappingService;

	@Test
	public void mapTsrcToDest() {
		Date now = Calendar.getInstance().getTime();
		Employee emp = new Employee();
		emp.setEmployeeId(200L);
		emp.setDateModified(now);
		emp.setEmployeeName("Styphinson Toms");
		emp.setEmployeeHireDate(now);
		emp.setEmpYrOfExperience(5);
		emp.setEmployeeDesignation("Software Engineer");

		Salary salaray = new Salary();
		salaray.setBasicSalarry(20000);
		salaray.setNetSalary(25000);
		emp.setSalary(salaray);
		EmployeeDest destEmp = objectMappingService.mapTsrcToDest(emp);
		System.out.println("<<<<<< Mapped Employee is >>>>>>>>> : " + destEmp);

		Address address = new Address();
		address.setCityName("City Name");
		address.setStreetName("Street Name");

		objectMappingService.mapTsrcToDest(address, destEmp);

		String xAxis = "No Product Name Reported";
		String yAxis = "14";

		Object[] item1 = new Object[2];
		item1[0] = xAxis;
		item1[1] = yAxis;

		String xAxis1 = "PARA";
		String yAxis1 = "1";

		Object[] item2 = new Object[2];
		item2[0] = xAxis1;
		item2[1] = yAxis1;

		String xAxis2 = "TDO'ONE";
		String yAxis2 = "4";

		Object[] item3 = new Object[2];
		item3[0] = xAxis2;
		item3[1] = yAxis2;

		List<Object[]> dataList = new ArrayList<Object[]>();
		dataList.add(item1);
		dataList.add(item2);
		dataList.add(item3);

		ChartDataSource chartDs = new ChartDataSource();
		chartDs.setSrcData(dataList);

		Chart chart = new Chart();
		objectMappingService.mapTsrcToDest(chartDs, chart);
		
		JsonServiceImpl jsonService=new JsonServiceImpl();
		String result=jsonService.convertToString(chart);
		System.out.println(result);
		
		

		System.out.println("<<<<<< Mapped Employee is >>>>>>>>> : " + chart);

	}
}
