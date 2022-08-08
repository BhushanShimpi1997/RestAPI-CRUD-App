package com.employee.service;

import java.util.List;

import com.employee.model.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);
	Employee getEmployeeById(long id);
	List<Employee> getAllEmployees();
	Employee updateEmployee(Employee employee , long id);
	void deleteEmployeeById(long id);
}
