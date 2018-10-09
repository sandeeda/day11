package com.example.customerapp.client;

import org.springframework.web.client.RestTemplate;

import com.example.customerapp.entity.Customer;

public class CustomerControllerRestTemplateClient {

	private static final RestTemplate REST_TEMPLATE = new RestTemplate();
	private static final String baseUrl = "http://localhost:9090/";
	
	public static void main(String[] args) {
		
		//Adding a new customer into database.
		String url = baseUrl + "customer";
		Customer customer = new Customer(1234, "sandeep", "sdsts28@gmail.com", "1234", "ranchi");
		customer = addCustomer(url, customer);
		System.out.println("After saving customer into dababase : " + customer);
		
		customer = new Customer(1235, "lemur", "sdsts28@gmail.com", "1235", "zoo");
		customer = addCustomer(url, customer);
		System.out.println("After saving customer into dababase : " + customer);
		
		//Getting customer from database
		url = baseUrl + "/customer/1234";
		customer = findCustomerById(url);
		System.out.println("Customer from DB : " + customer);
		
		
		//Updating customer into database
		url = baseUrl + "customer";
		customer = new Customer(1234, "sandeep", "sdsts28@gmail.com", "1234", "ranchi,jharkhand");
		updateCustomer(url, customer);
		customer = findCustomerById(baseUrl + "customer/1234");
		System.out.println(customer);
		
		
		// Deleting customer from database
				url = baseUrl + "customer/1234";		
				deleteCustomer(url);
				
	}

	public static void updateCustomer(String url, Customer customer) {
		 REST_TEMPLATE.put(url, customer);
		 System.out.println("--success--");
	}

	public static void deleteCustomer(String url) {
		REST_TEMPLATE.delete(url);
		System.out.println("--success--");
	}

	public static Customer findCustomerById(String url) {
		return REST_TEMPLATE.getForObject(url, Customer.class);
	}

	public static Customer addCustomer(String url, Customer customer) {
		Customer customerAfterSavingIntoDb =  REST_TEMPLATE.postForObject(url, customer, Customer.class);
		System.out.println("--success--");
		return customerAfterSavingIntoDb;
				
	}
}
