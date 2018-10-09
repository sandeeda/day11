package com.example.customerapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.customerapp.exception.CustomerNotFoundException;


@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(value=CustomerNotFoundException.class)
	public  void handleCustomerNotFoundException(HttpServletRequest request,CustomerNotFoundException exception) {
		
		exception.getMessage();
	}
}
