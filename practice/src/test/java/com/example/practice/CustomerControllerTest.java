package com.example.practice;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.customerapp.controller.CustomerController;
import com.example.customerapp.entity.Customer;
import com.example.customerapp.exception.CustomerNotFoundException;
import com.example.customerapp.service.CustomerService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

	@Mock
	private CustomerService customerService;

	@InjectMocks
	private CustomerController customerController;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
	}

	@Test
	public void testAddCustomer() throws Exception {

		when(customerService.addCustomer(Mockito.isA(Customer.class))).thenReturn(new Customer(1234, "sandeep", "sdsts28@gmail.com", "1234", "ranchi"));
		mockMvc.perform(post("/customer").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\r\n" + 
						"  \"customerName\": \"sandeep\",\r\n" + 
						"  \"customerEmail\": \"sdsts28@gmail.com\",\r\n" + 
						"  \"customerPassword\": \"1234\",\r\n" + 
						"  \"customerAddress\": \"ranchi\",\r\n" + 
						"  \"customerId\": \"1234\"\r\n" + 
						"}")
				.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
		.andExpect(jsonPath("$.customerName").value("sandeep")).andExpect(jsonPath("$.customerId").value(1234))
		.andDo(print());
	}


	@Test
	public void testUpdateCustomer() throws Exception {
		when(customerService.updateCustomer(Mockito.isA(Customer.class))).thenReturn(new Customer(1234, "changedName", "changedEmail", "changedPassword", "changedAddress"));
		mockMvc.perform(put("/customer").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\r\n" + 
						"  \"customerName\": \"changedName\",\r\n" + 
						"  \"customerEmail\": \"changedEmail\",\r\n" + 
						"  \"customerPassword\": \"changedPassword\",\r\n" + 
						"  \"customerAddress\": \"changedAddress\",\r\n" + 
						"  \"customerId\": \"1234\"\r\n" + 
						"}")
				.accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.customerName").value("changedName"))
		.andDo(print());

	}

	@Test
	public void testFindCustomerByIdWhenCustomerExists() throws CustomerNotFoundException,Exception
	{
		when(customerService.findCustomerByid(1234)).thenReturn(new Customer(1234, "sandeep", "changedEmail", "changedPassword", "changedAddress"));
		mockMvc.perform(get("/customer/1234"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.customerName").value("sandeep"))
		.andDo(print());
	}


	@Test
	public void testDeleteCustomerById() throws Exception
	{
		mockMvc.perform(delete("/customer/1234"))
		.andExpect(status().isOk())
		.andDo(print());
		
	}
	
}
