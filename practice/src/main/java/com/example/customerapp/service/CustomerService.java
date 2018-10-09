package com.example.customerapp.service;

import java.util.List;

import com.example.customerapp.entity.Customer;
import com.example.customerapp.exception.CustomerNotFoundException;

public interface CustomerService {

	public void deleteCustomerByid(int customerId) throws CustomerNotFoundException;
	public Customer findCustomerByid(int customerId)throws CustomerNotFoundException;
	public List<Customer> findAllCustomers();
	public Customer addCustomer(Customer customer);
	public Customer updateCustomer(Customer customer)throws CustomerNotFoundException;
	public Customer authenticateCustomer(Customer customer);
	
}
