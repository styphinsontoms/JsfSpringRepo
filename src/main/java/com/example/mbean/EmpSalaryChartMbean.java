package com.example.mbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.core.service.EmployeeService;

@Component
@Scope("session")
public class EmpSalaryChartMbean implements Serializable {


	@Autowired
	EmployeeService employeeService;
	
	private String chartString;

	
	Map<Integer, Object> criteriaMap = null;

	private String searchText;

	private List<String> chartTypeList;
	
	private String seletedChartType="column";

	// Load Section for One Time Data Load

	public void initOnLoadData() {
		loadEmpSalarayChart(seletedChartType);
		loadChartTypes();
	}
	
	
	public void update()
	{
		
	}
	
	public void drillDown()
	{
		
	}
	
	public void loadEmpSalarayChart(String selectedChartType) {
		chartString= employeeService.getEmpJoinDateChart(seletedChartType);
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
	
	@PostConstruct
	public void initBean() {
		System.out.println("Initializing Bean" + this.getClass().toString());
	}

	@PreDestroy
	public void destroyBean() {
		System.out.println("Destroying Bean" + this.getClass().toString());
	}
}
