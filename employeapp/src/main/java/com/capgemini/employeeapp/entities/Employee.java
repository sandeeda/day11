package com.capgemini.employeeapp.entities;

public class Employee {
	private int employeeid;
	private String employeeName;
	private String employeeDepartment;
	private double employeeSalary;
	public Employee(int employeeid, String employeeName, String employeeDepartment, double employeeSalary) {
		super();
		this.employeeid = employeeid;
		this.employeeName = employeeName;
		this.employeeDepartment = employeeDepartment;
		this.employeeSalary = employeeSalary;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(int employeeid) {
		this.employeeid = employeeid;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeDepartment() {
		return employeeDepartment;
	}
	public void setEmployeeDepartment(String employeeDepartment) {
		this.employeeDepartment = employeeDepartment;
	}
	public double getEmployeeSalary() {
		return employeeSalary;
	}
	public void setEmployeeSalary(double employeeSalary) {
		this.employeeSalary = employeeSalary;
	}
	@Override
	public String toString() {
		return "Employee [employeeid=" + employeeid + ", employeeName=" + employeeName + ", employeeDepartment="
				+ employeeDepartment + ", employeeSalary=" + employeeSalary + "]";
	}
}
