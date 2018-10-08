package com.sandeep.bank;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sandeep.bank.controller.CustomerController;
import com.sandeep.bank.exceptions.UserNotFoundException;
import com.sandeep.bank.model.Customer;
import com.sandeep.bank.service.CustomerService;
import static org.hamcrest.Matchers.*;

public class BankControllerTest {

	@Mock // Mockito mock object
	private CustomerService customerService;

	@InjectMocks
	private CustomerController customerController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);// initializes controller and mocks
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testLogin() throws Exception {
		Customer customer1 = new Customer(null, 1234, "1234", null, null, null, null);
		Customer customer2 = new Customer(null, 1234, "1234", null, null, null, null);

		when(customerService.authenticate(customer1)).thenReturn(customer2);

		mockMvc.perform(post("/login.do").flashAttr("customer", customer1)).andExpect(status().isOk())
				.andExpect(view().name("displayDetails"));

	}

}
