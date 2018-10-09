package com.example.customerapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.customerapp.entity.Customer;
import com.example.customerapp.exception.CustomerNotFoundException;
import com.example.customerapp.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	
	@PostMapping("/customer/authenticate")
	public ResponseEntity<Customer> authenticateCustomer(@RequestBody Customer customer) 
	{
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(customerService.authenticateCustomer(customer),HttpStatus.OK);  
		return responseEntity;
	}
	@PostMapping("/customer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer)
	{
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(customerService.addCustomer(customer),HttpStatus.OK);
		return responseEntity;
	}
	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) throws CustomerNotFoundException
	{
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(customerService.updateCustomer(customer),HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable int customerId) throws CustomerNotFoundException
	{
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(customerService.findCustomerByid(customerId),HttpStatus.OK);  
		return responseEntity;
	}
	
	@DeleteMapping("/customer/{customerId}")
	public void deleteCustomerById(@PathVariable int customerId) throws CustomerNotFoundException
	{
		customerService.deleteCustomerByid(customerId);
	}
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> findAllEmployees()
	{
		ResponseEntity<List<Customer>> responseEntity = new ResponseEntity<List<Customer>>(customerService.findAllCustomers(),HttpStatus.OK);
		return responseEntity;
	}
}
