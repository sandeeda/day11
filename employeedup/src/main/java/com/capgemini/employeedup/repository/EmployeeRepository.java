package com.capgemini.employeedup.repository;

import java.util.List;

import com.capgemini.employeedup.entities.Employee;

public interface EmployeeRepository {
	public Employee addEmployee(Employee employee);
	public Employee updateEmployee(Employee employee);
	public boolean deleteEmployee(int employeeId);
	public Employee findEmployeeById(int employeeId);
	public List<Employee> findAllEmployees();
}
