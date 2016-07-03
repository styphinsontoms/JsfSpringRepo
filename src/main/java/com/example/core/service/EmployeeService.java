package com.example.core.service;

import java.util.Map;

import org.primefaces.model.SortOrder;

import com.example.dao.entity.Address;
import com.example.dao.entity.Employee;

public interface EmployeeService {

	public void register(Employee emp) throws Exception;

	public void registerWithAddress(Employee emp, Address address)
			throws Exception;

	public Object[] getDataSet(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<Integer, Object> filters);

	public String getEmpSalaryForExperienceChart(String seletedChartType);

	public String getEmpSalaryChart(String seletedChartType);

	public String getEmpJoinDateChart(String seletedChartType);
}
