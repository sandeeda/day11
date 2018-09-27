package com.sandeep.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandeep.bank.dao.CustomerDao;
import com.sandeep.bank.model.Customer;
import com.sandeep.bank.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerDao customerDao;

	@Override
	public Customer authenticate(Customer customer) {
		// TODO Auto-generated method stub
		return customerDao.authenticate(customer);
	}

	@Override
	public Customer updateProfile(Customer customer) {
		// TODO Auto-generated method stub
		return customerDao.updateProfile(customer);
	}

	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		return customerDao.updatePassword(customer, oldPassword, newPassword);
	}

}
