package com.sandeep.bank.service;

import com.sandeep.bank.exceptions.InvalidDetailsException;
import com.sandeep.bank.exceptions.PasswordDetailsWrongException;
import com.sandeep.bank.exceptions.UserNotFoundException;
import com.sandeep.bank.model.Customer;

public interface CustomerService {
	public Customer authenticate(Customer customer) throws UserNotFoundException;
	public Customer updateProfile(Customer customer) throws InvalidDetailsException;
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) throws PasswordDetailsWrongException;

}
