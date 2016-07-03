package com.example.chart.mapping.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.chart.mapping.ObjectMappingService;
import com.example.chart.pojo.Chart;
import com.example.chart.pojo.ChartDataSource;
import com.example.dao.entity.Address;
import com.example.dao.entity.Employee;
import com.example.dozer.dest.pojo.EmployeeDest;

@Service
public class ObjectMappingServiceImpl implements ObjectMappingService {

	@Autowired
	Mapper dozerBeanMapper;

	public EmployeeDest mapTsrcToDest(Employee employee) {
		EmployeeDest empDest = null;
		empDest = dozerBeanMapper.map(employee, EmployeeDest.class);

		return empDest;

	}

	public void mapTsrcToDest(Address address,EmployeeDest dest) {
		EmployeeDest empDest = null;
		dozerBeanMapper.map(address, dest);

	}

	
	
	public void mapTsrcToDest(ChartDataSource ds, Chart dest) {
		Chart empDest = null;
		dozerBeanMapper.map(ds, dest);

	}
}
