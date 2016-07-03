package com.example.mbean;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.core.service.EmployeeService;
import com.example.core.service.impl.EmployeeServiceImpl;
import com.example.dao.entity.Address;
import com.example.dao.entity.Employee;
import com.example.dao.entity.Salary;

@Component
@Scope("session")
public class EmpRegisterView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5824359998647275348L;

	@Autowired
	private EmployeeService employeeService;

	private Employee employee = new Employee();

	Address address = new Address();

	private Salary salary = new Salary();

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public Employee getEmployee() {
		employee.setSalary(salary);
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String prepareLogin() {
		return "index";
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String registerNew() {
		salary = new Salary();
		employee = new Employee();
		address=new Address();
		return "index?faces-redirect=true";
	}

	public String register() {
		// Calling Business Service
		try {
			employee.setUserModified(Thread.currentThread().getName());
			employee.setDateModified(new Date());
			employee.setModuleId(employee.getEmployeeName());

			employee.getSalary().setUserModified(Thread.currentThread().getName());
			employee.getSalary().setDateModified(new Date());
			employee.getSalary().setModuleId(employee.getEmployeeName());
			
			address.setUserModified(Thread.currentThread().getName());
			address.setDateModified(new Date());
			address.setModuleId(employee.getEmployeeName());
			
			employeeService.registerWithAddress(employee, address);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage("The Employee "
							+ this.employee.getEmployeeName()
							+ " Is Registered Successfully"));
			return "listEmployees?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage("The Employee "
							+ this.employee.getEmployeeName()
							+ " Is Failed To Register"));
			return "listEmployees?faces-redirect=true";
		}

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
