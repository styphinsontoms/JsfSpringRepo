package com.example.core.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.chart.pojo.Chart;
import com.example.chart.pojo.ChartJson;
import com.example.chart.pojo.Gauge;
import com.example.chart.pojo.data.ChartData;
import com.example.chart.pojo.data.DataItem;
import com.example.chart.pojo.data.DataSeries;
import com.example.chart.pojo.style.ChartAxis;
import com.example.chart.pojo.style.Scale;
import com.example.core.service.EmployeeService;
import com.example.core.service.JsonService;
import com.example.dao.entity.Address;
import com.example.dao.entity.Employee;
import com.example.dao.entity.Salary;
import com.example.emp.core.dao.service.GenericCrudService;

@Component
public class EmployeeServiceImpl implements EmployeeService {

	private static Logger LOG = LoggerFactory
			.getLogger(EmployeeServiceImpl.class);
	@Autowired
	GenericCrudService genericCrudService;

	@Autowired
	JsonService jsonService;

	public static final int COUNT_QUERY = 1;
	public static final int LIST_QUERY = 2;

	public List findAllEmployee() {

		return null;

	}

	public Object[] getDataSet(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<Integer, Object> filters) {
		LOG.trace("Inside getDataSet method .. Loading data..");
		try {
			String countQuery = getSimpleSearchQuery(COUNT_QUERY, null, null,
					filters);
			Object[] returnVal = new Object[2];
			int count = genericCrudService.getCountByNativeQuery(countQuery,
					filters);
			returnVal[0] = count;
			if (count > 0) {
				String sortingOrder = "DESCENDING".equalsIgnoreCase(sortOrder
						.toString()) ? "DESC" : "ASC";
				String dataQuery = getSimpleSearchQuery(LIST_QUERY, sortField,
						sortingOrder, filters);
				List dataList = genericCrudService.findAllByNativeQueryPerPage(
						dataQuery, first, pageSize, filters);
				List<Employee> Employees = null;
				Employees = convertToVectorObject(dataList);
				returnVal[1] = Employees;

			} else {
				returnVal[1] = new ArrayList<Employee>();
			}
			return returnVal;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
	}

	private List<Employee> convertToVectorObject(List dataList) {
		LOG.info("Converting Employee objects to List" + dataList);
		List<Employee> listSorted = new ArrayList<Employee>();
		Object[] vecEmployee = null;

		if (null != dataList && !dataList.isEmpty()) {
			for (int i = 0; i < dataList.size(); i++) {
				Employee Employee = new Employee();
				Salary salary = new Salary();
				Employee.setSalary(salary);
				vecEmployee = (Object[]) dataList.get(i);

				if (null != vecEmployee[0]
						&& !"".equalsIgnoreCase(vecEmployee[0].toString())) {
					Employee.setEmployeeId(Long.valueOf(vecEmployee[0]
							.toString()));
				}
				if (null != vecEmployee[1]
						&& !"".equalsIgnoreCase(vecEmployee[1].toString())) {
					Employee.setEmployeeName(vecEmployee[1].toString());
				}
				if (null != vecEmployee[2]
						&& !"".equalsIgnoreCase(vecEmployee[2].toString())) {
					Employee.setEmployeeHireDate(getEmpDateFromString(vecEmployee[2]
							.toString()));
				}
				if (null != vecEmployee[3]
						&& !"".equalsIgnoreCase(vecEmployee[3].toString())) {
					Employee.getSalary().setBasicSalarry(
							Double.valueOf(vecEmployee[3].toString()));
				}
				if (null != vecEmployee[4]
						&& !"".equalsIgnoreCase(vecEmployee[4].toString())) {
					Employee.setEmpYrOfExperience(Integer
							.valueOf(vecEmployee[4].toString()));
				}
				if (null != vecEmployee[5]
						&& !"".equalsIgnoreCase(vecEmployee[5].toString())) {
					Employee.setEmployeeDesignation(vecEmployee[5].toString());
				}
				listSorted.add(Employee);
			}

		}
		System.out.println("retrieved List Has :" + listSorted);
		return listSorted;
	}

	private Date getEmpDateFromString(String date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",
				Locale.ENGLISH);

		Date dateNow = new Date();

		try {
			dateNow = format.parse(date);
		} catch (ParseException e) {
			LOG.error("Error while parsing Date", e);
		}
		System.out.println(date);
		return dateNow;
	}

	private String getSimpleSearchQuery(int queryType, String sortField,
			String sortOrder, Map<Integer, Object> filters) {
		StringBuffer buffer = new StringBuffer();
		switch (queryType) {
		case EmployeeServiceImpl.COUNT_QUERY:
			buffer.append(" SELECT COUNT(1) FROM Employee E  ");
			break;
		case EmployeeServiceImpl.LIST_QUERY:
			buffer.append(" SELECT E.EMP_ID,E.EMP_NAME,E.EMP_HIRE_DATE,S.BASIC_SALARY,E.EMP_YR_OF_EXP,E.EMP_DESIGNATION FROM EMPLOYEE E,SALARY S where E.FK_SALARY_ID=S.SALARY_ID");
			break;
		default:
			break;
		}

		if (null != filters && null != filters.get(1)
				&& !filters.get(1).toString().trim().isEmpty()) {
			buffer.append(" WHERE E.EMP_NAME LIKE ?1 ESCAPE '\\' ");
			buffer.append(" OR ");
			buffer.append(" E.EMP_DESIGNATION LIKE ?1 ESCAPE '\\' ");

			if (queryType != EmployeeServiceImpl.COUNT_QUERY) {
				buffer.append(" ORDER BY ");
				if (null == sortField) {
					buffer.append("EMP_NAME ");
				}
				buffer.append(sortOrder);
			}
		}
		return buffer.toString();
	}

	@Transactional
	public void register(Employee emp) throws Exception {
		// Save employee
		// this.em.persist(emp);

		genericCrudService.saveEntity(emp);

	}

	public List<Employee> getEmpReport() {
		List<Employee> allEmployees = new ArrayList<Employee>();
		try {
			allEmployees = genericCrudService.findAll(Employee.class);
		} catch (Exception e) {
			allEmployees.clear();
			LOG.error("Error in retriving Employee Report", e);
		}

		return allEmployees;
	}

	@Transactional
	public void registerWithAddress(Employee emp, Address address)
			throws Exception {
		genericCrudService.saveEntity(address);
		genericCrudService.saveEntity(emp);

	}

	@PostConstruct
	public void initBean() {
		System.out.println("Initializing Bean" + this.getClass().toString());
	}

	@PreDestroy
	public void destroyBean() {
		System.out.println("Destroying Bean" + this.getClass().toString());
	}

	public String getEmpSalaryChart(String seletedChartType) {
		JSONArray salarayDataArray = new JSONArray();
		JSONObject salarrayChartData = new JSONObject();
		JSONObject salaryChartJson = new JSONObject();
		JSONObject salaryChartObj = new JSONObject();
		JSONObject scaleObject = new JSONObject();

		try {
			List<Employee> allEmployees = getEmpReport();
			List<Double> salaryList = new ArrayList<Double>();
			
			Double maxValue = 50000d;
			for (Employee employee : allEmployees) {
				if (employee.getSalary().getBasicSalarry() > maxValue)
					maxValue = employee.getSalary().getBasicSalarry();
				salaryList.add(employee.getSalary().getBasicSalarry());
			}
			scaleObject.put("minimum", 1000);
			scaleObject.put("maximum", maxValue);

			salaryChartObj.put("yScale", scaleObject);
			

			salaryChartObj.put("type", seletedChartType);
			
			salarrayChartData.put("data", salaryList);
			salarayDataArray.put(salarrayChartData);
			salarrayChartData.put("color", "#64b5f6");
			salarrayChartData.put("clip", true);
			salaryChartObj.put("series", salarayDataArray);

			// salaryChartObj.put("container", "chartContainer");
			salaryChartJson.put("chart", salaryChartObj);

			System.out.println(salaryChartJson.toString());
		} catch (JSONException e) {
			LOG.error("Error in JSON Creation", e);
		}
		return salaryChartJson.toString();

	}

	public String getEmpSalaryForExperienceChart(String seletedChartType) {
		JSONArray salarayDataArray = new JSONArray();
		JSONObject salarrayChartData = new JSONObject();
		JSONObject salaryChartJson = new JSONObject();
		JSONObject salaryChartObj = new JSONObject();
		JSONObject scaleObject = new JSONObject();
		List<Employee> allEmployees = getEmpReport();
		List<Double> salaryList = new ArrayList<Double>();
		Map<Integer, List<Double>> seriesMap = new HashMap<Integer, List<Double>>();
		Double maxValue = 50000d;
		for (Employee employee : allEmployees) {
			if (employee.getSalary().getBasicSalarry() > maxValue)
				maxValue = employee.getSalary().getBasicSalarry();
			Integer experience = employee.getEmpYrOfExperience();
			if (seriesMap.containsKey(experience)) {
				seriesMap.get(experience).add(
						employee.getSalary().getBasicSalarry());
			} else {
				salaryList = new ArrayList<Double>();
				salaryList.add(employee.getSalary().getBasicSalarry());
				seriesMap.put(experience, salaryList);
			}

			try {
				scaleObject.put("minimum", 1000);
				scaleObject.put("maximum", maxValue);

				salaryChartObj.put("yScale", scaleObject);

				salaryChartObj.put("type", seletedChartType);

				for (Integer yrOfExperience : seriesMap.keySet()) {
					salarrayChartData
							.put("data", seriesMap.get(yrOfExperience));
					salarrayChartData.put("data", salaryList);
					salarrayChartData.put("color", "#64b5f6");
					salarrayChartData.put("clip", true);
					salarayDataArray.put(salarrayChartData);
				}

				salaryChartJson.put("chart", salaryChartObj);
				System.out.println(salaryChartJson.toString());

			} catch (JSONException e) {
				LOG.error("Error in JSON ", e);
			}

		}
		return salaryChartJson.toString();

	}

	public String getEmpJoinDateChart(String seletedChartType) {
		String container = "chartContainer";
		String jsonString = "";
		ChartJson chartJson = new ChartJson();
		Chart chart = new Chart();
		Gauge gauge = new Gauge();

		List<Employee> allEmployees = getEmpReport();
		List<Double> salaryList = new ArrayList<Double>();
		Double maxValue = 50000d;
		List<DataItem> dataItems1=new ArrayList<DataItem>();
		DataItem item=new DataItem();
		for (Employee employee : allEmployees) {
			if (employee.getSalary().getBasicSalarry() > maxValue)
				maxValue = employee.getSalary().getBasicSalarry();
			salaryList.add(employee.getSalary().getBasicSalarry());
			item=new DataItem();
			item.setX(employee.getEmployeeName());
			item.setValue(String.valueOf(employee.getSalary().getBasicSalarry()));
			dataItems1.add(item);
		}
		ChartData data1=new ChartData();
		data1.setData(dataItems1);
		
		List<DataItem> dataItems2=new ArrayList<DataItem>();
		for (Employee employee : allEmployees) {
			if (employee.getSalary().getBasicSalarry() > maxValue)
				maxValue = employee.getSalary().getBasicSalarry();
			salaryList.add(employee.getSalary().getBasicSalarry());
			item=new DataItem();
			item.setX(employee.getEmployeeName());
			item.setValue(String.valueOf(employee.getEmpYrOfExperience()));
			dataItems2.add(item);
		}
		
		
		ChartData data2=new ChartData();
		data2.setData(dataItems2);
		Object[] series=new Object[2];
		series[0]=data1;
		series[1]=data2;
		
		
		Object[] xAxis=new Object[1];
		ChartAxis obj1=new ChartAxis();
		obj1.setOrientation("bottom");
		xAxis[0]=obj1;
		Object[] yAxis=new Object[1];
		ChartAxis obj2=new ChartAxis();
		obj2.setOrientation("left");
		yAxis[0]=obj2;
		
		
		Object[] scaleArray=new Object[1];
		Scale scale=new Scale();
		scale.setType("dateTime");
		scaleArray[0]=scale;
		
		// JSON Schema Objects
		chart.setContainer(container);
		chartJson.setChart(chart);
		chart.setAdditionalProperty("type", seletedChartType);
		chart.setAdditionalProperty("series", series);
		chart.setAdditionalProperty("fill", "#fff");
		chart.setAdditionalProperty("stroke", "#726E6D");
		chart.setAdditionalProperty("xAxes", xAxis);
		chart.setAdditionalProperty("yAxes", yAxis);
		chart.setAdditionalProperty("scales", scaleArray);

		jsonString = jsonService.convertToString(chartJson);
		
		System.out.println(jsonString);
		return jsonString;

	}
}
