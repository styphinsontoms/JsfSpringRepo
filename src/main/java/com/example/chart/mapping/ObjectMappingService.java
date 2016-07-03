package com.example.chart.mapping;

import com.example.chart.pojo.Chart;
import com.example.chart.pojo.ChartDataSource;
import com.example.dao.entity.Address;
import com.example.dao.entity.Employee;
import com.example.dozer.dest.pojo.EmployeeDest;

public interface ObjectMappingService {

	public EmployeeDest mapTsrcToDest(Employee employee);

	public void mapTsrcToDest(Address address, EmployeeDest dest);

	public void mapTsrcToDest(ChartDataSource ds, Chart dest);
}
