package com.sandeep.bank;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sandeep.bank.controller.CustomerController;
import com.sandeep.bank.dao.CustomerDao;
import com.sandeep.bank.model.BankAccount;
import com.sandeep.bank.model.Customer;

public class TestClass {
	@InjectMocks
	CustomerController customerController;

	@Mock
	CustomerDao customerDao;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAuthenticate() {
		Customer customer = new Customer();
		customer.setCustomerId(12345);
		customer.setPassword("12");

		Customer customer1 = new Customer("Sandeep Das", 1234, "1234", "sdsts28@gmail.com", "lalpur,rnc,jharkhand",
				LocalDate.of(1996, 06, 25), new BankAccount(221700, 808, "SAVINGS"));

		when(customerDao.authenticate(customer)).thenReturn(customer1);

//		CustomerController sc= customerController.checking(null, null, null, 0, null);
		
		assertEquals(customer1, customerDao.authenticate(customer));

	}
}
