package com.capgemini.employeeapp.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.DispatcherServlet;

import com.capgemini.employeeapp.entities.Employee;
import com.capgemini.employeeapp.repository.EmployeeRepository;


@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Employee addEmployee(Employee employee) {
		int count=jdbcTemplate.update("insert into employees values(?,?,?,?)", new Object[] {employee.getEmployeeid(),employee.getEmployeeName(),employee.getEmployeeDepartment(),employee.getEmployeeSalary()});
		if(count==1)
		{
			return employee;
		}
		else
		{
			return findEmployeeById(employee.getEmployeeid());
		}
	}

	
	@Override
	public Employee updateEmployee(Employee employee) {
		int count=jdbcTemplate.update("update employees set employee_name=?,employee_department=?,employee_salary=? where employee_id=?", new Object[] {employee.getEmployeeName(),employee.getEmployeeDepartment(),employee.getEmployeeSalary(),employee.getEmployeeid()});
		if(count==1)
			return employee;
		else 
			return findEmployeeById(employee.getEmployeeid());
	}

	
	
	@Override
	public boolean deleteEmployee(int employeeId) {
		int count = jdbcTemplate.update("delete from employees where employee_id=?",new Object[] {employeeId});
		if(count==1)
			return true;
		return false;
	}
	
	
	@Override
	public Employee findEmployeeById(int employeeId) {
		return jdbcTemplate.queryForObject("select * from employees where employee_id=?", new Object[] {employeeId},new EmployeesRowMapper());
	}
	
	
	@Override
	public List<Employee> findAllEmployees() {
		return jdbcTemplate.query("select * from employees", new Object[] {},new EmployeesRowMapper());
	}

	private class EmployeesRowMapper implements RowMapper<Employee>{
		@Override
		public Employee mapRow(ResultSet rs , int rowNum) throws SQLException {
			Employee employee = new Employee();
			employee.setEmployeeid(rs.getInt(1));
			employee.setEmployeeName(rs.getString(2));
			employee.setEmployeeDepartment(rs.getString(3));
			employee.setEmployeeSalary(rs.getDouble(4));
			return employee;
			
		}
	}

}
