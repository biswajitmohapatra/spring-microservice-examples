package com.biswajit.micro.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.biswajit.micro.model.Employee;

/**
 * @author Biswajit Mohapatra
 * 
 */

@Repository
public class EmployeeDaoImpl implements EmployeeDao{
	private static int index = 0;
	private static Map<Integer,Employee> employeeMap = new HashMap<Integer,Employee>();

	public List<Employee> getEmployees() throws Exception {
		
		List<Employee> employees = new ArrayList<Employee>();
		
		for(Map.Entry<Integer, Employee> entry : employeeMap.entrySet()){
			employees.add(entry.getValue());
		}
		return employees;
	}

	public Employee getEmployee(int empId) throws Exception {
		if(index != -1 && empId <= index)
			return employeeMap.get(empId);
		else
			return null;
	}

	public void createEmployee(Employee employee) throws Exception {
		employee.setId(index+1);
		employeeMap.put(index+1, employee);
		index = index+1;
	}

	public String deleteEmployee(int empId) throws Exception {
		
		if(employeeMap.get(empId) == null)
			return "Employee with empid "+ empId + " does not exist in our Database." ;
		else{
			employeeMap.remove(empId);
			return "Employee with empid "+ empId + " deleted successfully from our database." ;
		}
	}

	public void updateEmployee(Employee employee) throws Exception {
		employeeMap.put(employee.getId(), employee);
		
	}

}
