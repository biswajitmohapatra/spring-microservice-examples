package com.biswajit.micro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biswajit.micro.dao.EmployeeDao;
import com.biswajit.micro.model.Employee;

/**
 * @author Biswajit Mohapatra
 * 
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeDao employeeDaoImpl;

	public List<Employee> getEmployees() throws Exception {
		return employeeDaoImpl.getEmployees();
	}

	public Employee getEmployee(int empId) throws Exception {
		return employeeDaoImpl.getEmployee(empId);
	}

	public void createEmployee(Employee employee) throws Exception {
		employeeDaoImpl.createEmployee(employee);
		
	}
	
	public void updateEmployee(Employee employee) throws Exception {
		Employee e = getEmployee(employee.getId());
		e.setName(employee.getName());
		e.setAge(employee.getAge());
		e.setSex(employee.getSex());
		e.setDesignation(employee.getDesignation());
		
		employeeDaoImpl.updateEmployee(e);
	}
	
	public String deleteEmployee(int empId) throws Exception {
		return employeeDaoImpl.deleteEmployee(empId);
	}
	
	public boolean isExist(Employee employee) throws Exception {
		List<Employee> employees= getEmployees();
		for(Employee e : employees){
			if(e.getName().equalsIgnoreCase(employee.getName()))
				return true;
		}
		return false;
	}

	public boolean isExistExcept(Employee employee) throws Exception {
		List<Employee> employees= getEmployees();
		for(Employee e : employees){
			if(e.getId() != employee.getId() && e.getName().equalsIgnoreCase(employee.getName()))
				return true;
		}
		return false;
	}
	

	
}
