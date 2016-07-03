package com.example.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.core.service.EmployeeService;
import com.example.dao.entity.Employee;
import com.example.mbean.datamodel.EmpDataModel;

@Component
@Scope("session")
public class EmpListingView implements Serializable {

	private static Logger LOG = LoggerFactory.getLogger(EmpListingView.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 3222071476973339080L;

	private LazyDataModel<Employee> emplazyModel;

	@Autowired
	EmployeeService employeeService;

	private Employee selectedEmployee;

	Map<Integer, Object> criteriaMap = null;

	private String searchText;

	private List<String> chartTypeList;

	private String empSalaryChart;
	
	private String empSalaryWithExpChart;
	
	private String seletedChartType="column";

	// Load Section for One Time Data Load

	public void initOnLoadData() {
		loadEmployees();
		loadEmpSalarayChart(seletedChartType);
		loadChartTypes();
	}

	public void loadEmployees() {
		emplazyModel = new EmpDataModel(employeeService, criteriaMap);
	}

	public void loadEmpSalarayChart(String selectedChartType) {
		empSalaryChart = employeeService.getEmpSalaryChart(seletedChartType);
		//empSalaryChart=empSalaryChart.replaceAll("'", "");
		empSalaryWithExpChart= employeeService.getEmpJoinDateChart(seletedChartType);
	}
	
	
	public void loadXXX(String selectedChartType,String chartId) {
		empSalaryChart = employeeService.getEmpSalaryChart(seletedChartType);
		empSalaryWithExpChart= employeeService.getEmpJoinDateChart(seletedChartType);
	}
	
	
	
	public String getEmpSalaryWithExpChart() {
		return empSalaryWithExpChart;
	}

	public void setEmpSalaryWithExpChart(String empSalaryWithExpChart) {
		this.empSalaryWithExpChart = empSalaryWithExpChart;
	}

	public void loadChartTypes() {
		chartTypeList=new ArrayList<String>();
		chartTypeList.add("column");
		chartTypeList.add("line");
		chartTypeList.add("box");
		chartTypeList.add("area");
		chartTypeList.add("bubble");
		chartTypeList.add("bar");
		chartTypeList.add("scatter");
		chartTypeList.add("radarChart");
		
		
	}
	
	// Ending of One Time Load Section

	public LazyDataModel<Employee> getEmplazyModel() {
		return emplazyModel;
	}

	public void setEmplazyModel(LazyDataModel<Employee> emplazyModel) {
		this.emplazyModel = emplazyModel;
	}

	public Employee getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getEmpSalaryChart() {
		System.out.println("in Getter method");
		return empSalaryChart;
	}

	public void setEmpSalaryChart(String empSalaryChart) {
		this.empSalaryChart = empSalaryChart;
	}

	public List<String> getChartTypeList() {
		return chartTypeList;
	}

	public void setChartTypeList(List<String> chartTypeList) {
		this.chartTypeList = chartTypeList;
	}

	
	
	public String getSeletedChartType() {
		return seletedChartType;
	}

	public void setSeletedChartType(String seletedChartType) {
		this.seletedChartType = seletedChartType;
		loadEmpSalarayChart(seletedChartType);
	}

	public void performSearch() {
		LOG.trace("Entered performSearch method in  TherapeuticListingMangaedBean");
		criteriaMap = new HashMap<Integer, Object>();
		criteriaMap.put(1, searchText);
		emplazyModel = new EmpDataModel(criteriaMap);
		LOG.trace("End of performSearch method in  TherapeuticListingMangaedBean");

	}

}
