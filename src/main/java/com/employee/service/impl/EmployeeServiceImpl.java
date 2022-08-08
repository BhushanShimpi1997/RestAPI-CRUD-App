package com.employee.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.employee.exception.ResourceNotFoundException;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import com.employee.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
		
	}

	@Override
	public Employee getEmployeeById(long id) {
		
		//Check whether given id is present or not in db
/*		
		Optional<Employee> emp=employeeRepository.findById(id);
		if(emp.isPresent())
		{
			return emp.get();
		}else {
			throw new ResourceNotFoundException("Employee", "ID", id);
		}
				*/
		
		return employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "Id", id));
		
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
	}

	@Override
	public Employee updateEmployee(Employee employee,long id) {
		//check whether emp with id is present or not
		
		Employee existingEmployee=employeeRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Employee", "id", id));
		
		//if record is present then set new data
		
		existingEmployee.setName(employee.getName());
		existingEmployee.setSalary(employee.getSalary());
		
		//save emp obj with new data
		employeeRepository.save(existingEmployee);
		
		return existingEmployee;
	}


	//Delete Employee By Id
	
	public void deleteEmployeeById(long id) {
		
		//check whether records are available with given id
		Employee existingEmployee=employeeRepository.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Employee", "Id", id));
		
		//delete emp if found with id
		 employeeRepository.deleteById(id);
		
	}
	
	

}
