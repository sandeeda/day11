package com.sandeep.bank.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sandeep.bank.exceptions.InvalidDetailsException;
import com.sandeep.bank.exceptions.PasswordDetailsWrongException;
import com.sandeep.bank.exceptions.UserNotFoundException;
import com.sandeep.bank.model.Customer;
import com.sandeep.bank.service.CustomerService;
@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("loginCustomer")
	public String getLoginCustomerPage(Model model) {
		model.addAttribute("customer",new Customer());
		return "loginCustomer";
	}


	@RequestMapping("login.do")
	public String displayDetails(HttpSession session,HttpServletRequest request, @RequestParam int customerId,@RequestParam String password)throws UserNotFoundException {
		session=request.getSession();
		Customer customer = new Customer(null, customerId, password, null, null, null, null);
		Customer authenticatedCustomer=null;
		authenticatedCustomer=customerService.authenticate(customer);
		session.setAttribute("customer", authenticatedCustomer);
		return "displayDetails";
	}

	@RequestMapping("changePassword")
	public String getChangePasswordPage() {
		return "changePassword";
	}
	@RequestMapping("changePassword.do")
	public String changePassword(HttpSession session,HttpServletRequest request, @RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String confirmNewPassword) throws PasswordDetailsWrongException {
		session=request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		if(newPassword.equals(confirmNewPassword))
		{
			customerService.updatePassword(customer, oldPassword, confirmNewPassword);
		}
		return "passworSuccessfullyChanged";
	}
	
	@RequestMapping("editProfile")
	public String getEditProfilePage(HttpServletRequest request,HttpSession session,Model model) {
		model.addAttribute("customer",session.getAttribute("customer"));
		return "editProfile";
	}

	@RequestMapping("editProfile.do")
	public String editProfile(HttpSession session,HttpServletRequest request, @ModelAttribute Customer customer) throws InvalidDetailsException {
		session=request.getSession();
		Customer customerInSession = (Customer) session.getAttribute("customer");
		customer = customerService.updateProfile(customer);
		customer.setBankAccount(customerInSession.getBankAccount());
		session.setAttribute("customer", customer);
		return "profileUpdatedSuccessfully";
	}
	
	@RequestMapping("logout.do")
	public String logout(HttpSession session,HttpServletRequest request) {
		session=request.getSession();
		session.invalidate();
		return "index";
	}
	@RequestMapping("displayDetails")
	public String displayUser() {
		return "displayDetails";
	}
	
}
