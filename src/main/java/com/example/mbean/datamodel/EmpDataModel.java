package com.example.mbean.datamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.core.service.EmployeeService;
import com.example.dao.entity.Employee;

public class EmpDataModel extends LazyDataModel<Employee> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5728948909671969796L;

	private static Logger LOG = LoggerFactory.getLogger(EmpDataModel.class);

	EmployeeService employeeService;

	Map<Integer, Object> criteria;

	public EmpDataModel(EmployeeService employeeService,
			Map<Integer, Object> criteria) {
		super();
		LOG.trace("Inside EmpDataModel Constructor");
		this.employeeService = employeeService;
		this.criteria = criteria;
	}

	public EmpDataModel(Map<Integer, Object> criteria) {
		super();
		LOG.trace("Inside EmpDataModel Constructor");
		this.criteria = criteria;
	}

	public List<Employee> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		Object[] obj = employeeService.getDataSet(first, first + pageSize,
				sortField, sortOrder, criteria);
		List<Employee> empList = (List<Employee>) obj[1];
		int dataSize = (Integer) obj[0];
		this.setRowCount(dataSize);
		return empList;
	}

	@Override
	public Employee getRowData(String rowKey) {
		List<Employee> list = (List<Employee>) getWrappedData();
		for (Employee therapeutic : list) {
			if (therapeutic.getRecordId().toString().equals(rowKey)) {
				return therapeutic;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Employee employee) {
		return employee != null ? employee.getRecordId() : null;
	}

	/*
	 * @Override public void setRowIndex(int rowIndex) { if (rowIndex == -1 ||
	 * getPageSize() == 0) { super.setRowIndex(-1); } else
	 * super.setRowIndex(rowIndex % getPageSize()); }
	 * 
	 * 
	 * @Override public Object getRowKey(Therapeutic therapeutic){ return
	 * therapeutic != null ? therapeutic.getRecordId() : null; }
	 */

	/*
	 * @Override public Therapeutic getRowData(String rowKey) {
	 * List<Therapeutic> list = (List<Therapeutic>) getWrappedData(); for
	 * (Therapeutic therapeutic : list) { if
	 * (therapeutic.getRecordId().toString().equals(rowKey)) { return
	 * therapeutic; } } return null; }
	 */
}
