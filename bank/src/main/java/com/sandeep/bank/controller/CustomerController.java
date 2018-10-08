package com.sandeep.bank.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.sandeep.bank.exceptions.InvalidDetailsException;
import com.sandeep.bank.exceptions.PasswordDetailsWrongException;
import com.sandeep.bank.exceptions.UserNotFoundException;
import com.sandeep.bank.model.Customer;
import com.sandeep.bank.service.CustomerService;
@Controller
@SessionAttributes("customer")
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
	public String displayDetails(@ModelAttribute Customer customer)throws UserNotFoundException {
		/*session=request.getSession();*/
		
		Customer authenticatedCustomer=null;
		authenticatedCustomer=customerService.authenticate(customer);
		customer.setAddress(authenticatedCustomer.getAddress());
		customer.setBankAccount(authenticatedCustomer.getBankAccount());
		customer.setDateOfBirth(authenticatedCustomer.getDateOfBirth());
		customer.setCustomerName(authenticatedCustomer.getCustomerName());
		customer.setEmail(authenticatedCustomer.getEmail());
//		session.setAttribute("customer", authenticatedCustomer);
		return "displayDetails";
		
	}

	@RequestMapping("changePassword")
	public String getChangePasswordPage() {
		return "changePassword";
	}
	@RequestMapping("changePassword.do")
	public String changePassword(@RequestParam String oldPassword, @RequestParam String newPassword, @RequestParam String confirmNewPassword,@ModelAttribute Customer customer) throws PasswordDetailsWrongException {
		/*session=request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");*/
		if(newPassword.equals(confirmNewPassword))
		{
			if(customerService.updatePassword(customer, oldPassword, confirmNewPassword))
				return "passworSuccessfullyChanged";
		}
		return "passwordFail";
	}

	@RequestMapping("editProfile")
	public String getEditProfilePage(@ModelAttribute Customer customer,Model model) {
		model.addAttribute("customer",customer);
		return "editProfile";
	}

	@RequestMapping("editProfile.do")
	public String editProfile(/*HttpSession session,HttpServletRequest request, */@ModelAttribute Customer customer) throws InvalidDetailsException {
//		Customer customerInSession = (Customer) session.getAttribute("customer");
		Customer customerInSession = customer;
		customer = customerService.updateProfile(customer);
		customer.setBankAccount(customerInSession.getBankAccount());
		return "profileUpdatedSuccessfully";
	}

	@RequestMapping("logout.do")
	public String logout(@ModelAttribute Customer customer, HttpSession session, SessionStatus status) {
		status.setComplete();
        session.removeAttribute("customer");
        return "redirect:/";
	}
	@RequestMapping("displayDetails")
	public String displayUser() {
		return "displayDetails";
	}

}
