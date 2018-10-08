package com.sandeep.bank.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.sandeep.bank.exceptions.AccountIdNotFoundException;
import com.sandeep.bank.model.Customer;
import com.sandeep.bank.service.BankAccountService;
import com.sandeep.bank.service.CustomerService;

@Controller
public class BankController {

	
	@Autowired
	BankAccountService bankAccountService;
	@Autowired
	CustomerService customerService;
	@RequestMapping("balanceEnquiry.do")
	public String getBalanceEnquiryPage()
	{
		return "balanceEnquiry";
	}
	
	@RequestMapping("fundTransfer")
	public String getfundTransferPage (Model model)
	{
		return "fundTransfer";
	}
	
	@RequestMapping("fundTransfer.do")
	public String fundTransfer (HttpServletRequest request,HttpSession session, @RequestParam long payeeAccountNumber,@RequestParam double amount) throws AccountIdNotFoundException
	{
		session = request.getSession();
		Customer customer= (Customer) session.getAttribute("customer");
		
		bankAccountService.fundTransfer(customer.getBankAccount().getAccountId(), payeeAccountNumber, amount);
		Customer dummy = new Customer(null, customer.getCustomerId(), customer.getPassword(), null, null, null,null);
		dummy = customerService.authenticate(dummy);
		session.setAttribute("customer", dummy);
		return "transferSuccess";
	}
	
}
