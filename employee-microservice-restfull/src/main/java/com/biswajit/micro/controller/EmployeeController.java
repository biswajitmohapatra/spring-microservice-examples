package com.biswajit.micro.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.biswajit.micro.model.Employee;
import com.biswajit.micro.service.EmployeeService;

/**
 * @author Biswajit Mohapatra
 * 
 */
@RestController
@RequestMapping("/EmployeeService")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeServiceImpl;

	@RequestMapping(value = "/heartbeat", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String heartbeat() {
		return "OK";
	}

	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String addEmployee(@RequestBody Employee employee) {
		String message = null;

		if (employee == null)
			return "Bad Request";

		try {
			if (employeeServiceImpl.isExist(employee))
				return "Employee with name " + employee.getName() + " is already exist.";

			employeeServiceImpl.createEmployee(employee);
			message = "Employee added successfully";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@RequestMapping(value = "/getEmployees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList<Employee>();

		try {
			employees.addAll(employeeServiceImpl.getEmployees());

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return employees;

	}

	@RequestMapping(value = "/getEmployee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private Employee getEmployee(@PathVariable("id") Integer id) throws Exception {
		return employeeServiceImpl.getEmployee(id);
	}

	@RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String deleteEmployee(@PathVariable("id") Integer id) throws Exception {
		return employeeServiceImpl.deleteEmployee(id);
	}
	
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String updateEmployee(@RequestBody Employee employee) throws Exception {
		String message = null;

		if (employee == null)
			return "Bad Request";
		
		if(employee.getId() == null || employee.getId().intValue() <= 0 )
			return "Employee ID is null";
		
		try {
			if (employeeServiceImpl.isExistExcept(employee))
				return "Employee with name " + employee.getName() + " is already exist.So System could not update the details";
			
			employeeServiceImpl.updateEmployee(employee);
			message = "Employee updated successfully";
		} catch (Exception e) {
			e.printStackTrace();
			message = "System could not update the details due some error :"+e.getMessage();
		}
		return message;
	}
	

}
