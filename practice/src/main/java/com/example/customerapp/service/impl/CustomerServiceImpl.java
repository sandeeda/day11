package com.example.customerapp.service.impl;

import java.util.List;
import java.util.Optional;

import javax.naming.OperationNotSupportedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.customerapp.entity.Customer;
import com.example.customerapp.exception.CustomerNotFoundException;
import com.example.customerapp.repository.CustomerRepository;
import com.example.customerapp.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {
	
	private static final Optional<Customer> Customer = null;
	@Autowired
	private CustomerRepository customerRepository;
	@Override
	public void deleteCustomerByid(int customerId) throws CustomerNotFoundException {
		try {
			customerRepository.deleteById(customerId);

		} catch (Exception e) {
			CustomerNotFoundException customerNotFoundException=new CustomerNotFoundException("customer not found!!!delete operation unsuccessfull");
			customerNotFoundException.initCause(e);
			throw customerNotFoundException;
		}
	}

	@Override
	public Customer findCustomerByid(int customerId) throws CustomerNotFoundException {
		
			Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
			if(optionalCustomer.isPresent())
				return optionalCustomer.get();
		
			CustomerNotFoundException customerNotFoundException=new CustomerNotFoundException("customer not found!!!delete operation unsuccessfull");
			throw customerNotFoundException;
		
	}

	@Override
	public List<Customer> findAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer addCustomer(Customer customer) {
	
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		try {
			return customerRepository.save(customer);
		} catch (Exception e) {
			CustomerNotFoundException customerNotFoundException=new CustomerNotFoundException("customer not found!!!delete operation unsuccessfull");
			customerNotFoundException.initCause(e);
			throw customerNotFoundException;
		}
	}

	@Override
	public Customer authenticateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
		Optional<Customer> optionalCustomer = customerRepository.findById(customer.getCustomerId());
		if(optionalCustomer.isPresent())
		{
			if(customer.getCustomerPassword().equals(optionalCustomer.get().getCustomerPassword()))
			return optionalCustomer.get();
		}
		return null;
	}

	

}
