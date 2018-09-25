package com.capgemini.employeeapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.employeeapp.entities.Employee;
import com.capgemini.employeeapp.repository.EmployeeRepository;
import com.capgemini.employeeapp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public Employee addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.addEmployee(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.updateEmployee(employee);
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		return employeeRepository.deleteEmployee(employeeId);
	}

	@Override
	public Employee findEmployeeById(int employeeId) {
		// TODO Auto-generated method stub
		return employeeRepository.findEmployeeById(employeeId);
	}

	@Override
	public List<Employee> findAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAllEmployees();
	}

}
