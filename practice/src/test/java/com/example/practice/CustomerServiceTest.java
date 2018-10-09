package com.example.practice;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.example.customerapp.entity.Customer;
import com.example.customerapp.exception.CustomerNotFoundException;
import com.example.customerapp.repository.CustomerRepository;
import com.example.customerapp.service.impl.CustomerServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerServiceTest {

	@Mock
	CustomerRepository customerRepository;

	@InjectMocks
	CustomerServiceImpl customerService;

	
	
	@Before
	public void setup()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFindCustomerById()
	{
		Customer customer = new Customer(1234, "sandeep", "dsts28@gmail.com", "1234", "ranchi");
		when(customerRepository.findById(1234)).thenReturn(Optional.of(customer));
		Customer customerResult;
		try {
			customerResult = customerService.findCustomerByid(1234);
			assertEquals("sandeep", customerResult.getCustomerName());
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void testDeleteCustomerById()
	{
//		Customer customer = new Customer(1234, "sandeep", "dsts28@gmail.com", "1234", "ranchi");
//		when(customerRepository.deleteById(1234)).thenReturn(Optional.of(customer));
		Customer customerResult;
		try {
			customerService.deleteCustomerByid(1234);
			verify(customerRepository,times(1)).deleteById(1234);
		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testAddCustomer()
	{
		Customer customer = new Customer(1234, "sandeep", "dsts28@gmail.com", "1234", "ranchi");
		when(customerRepository.save(customer)).thenReturn(customer);
		
		
		Customer ResultCustomer=customerService.addCustomer(customer);
		assertEquals("sandeep", ResultCustomer.getCustomerName());
		
	}
	@Test
	public void testUpdateCustomer()
	{
		Customer customer = new Customer(1234, "sandeep", "dsts28@gmail.com", "1234", "ranchi");
		when(customerRepository.save(customer)).thenReturn(customer);
		
		
		Customer ResultCustomer;
		try {
			ResultCustomer = customerService.updateCustomer(customer);
			assertEquals("sandeep", ResultCustomer.getCustomerName());

		} catch (CustomerNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void testFindAllCustomers()
	{
		Customer cust1 = new Customer(1234, "sandeep", "sdsts28@gmail.com", "1234", "ranchi");
		Customer cust2 = new Customer(1235, "lemur", "sdsts28@gmail.com", "1235", "zoo");
		Customer cust3 = new Customer(1236, "switty", "sdsts28@gmail.com", "1236", "parlour");
		List<Customer> customers = new ArrayList<Customer>() ;
		customers.add(cust1);
		customers.add(cust2);
		customers.add(cust3);
		when(customerRepository.findAll()).thenReturn(customers);
		List<Customer> resultCustomers = customerService.findAllCustomers();
		assertEquals(3, resultCustomers.size());
		
	}
	
}
