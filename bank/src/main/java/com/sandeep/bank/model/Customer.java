package com.sandeep.bank.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Customer {


	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(customerId,email);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (Address == null) {
			if (other.Address != null)
				return false;
		} else if (!Address.equals(other.Address))
			return false;
		if (bankAccount == null) {
			if (other.bankAccount != null)
				return false;
		} else if (!bankAccount.equals(other.bankAccount))
			return false;
		if (customerId != other.customerId)
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	public Customer(String customerName, int customerId, String password, String email, String address,
			LocalDate dateOfBirth, BankAccount bankAccount) {
		super();
		this.customerName = customerName;
		this.customerId = customerId;
		this.password = password;
		this.email = email;
		Address = address;
		this.dateOfBirth = dateOfBirth;
		this.bankAccount = bankAccount;
	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	private String customerName;
	@NotNull @Min(1000) @Max(9999)
	private int customerId;
	@NotNull
	@Size(min=3,max=20,message = "Please enter between {min} and {max} characters.")
	private String password;
	@Email
	private String email;
	private String Address;
	private LocalDate dateOfBirth;
	@NotNull @Min(1000) @Max(9999)
	private BankAccount bankAccount;
	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", customerId=" + customerId + ", password=" + password
				+ ", email=" + email + ", Address=" + Address + ", dateOfBirth=" + dateOfBirth + ", bankAccount="
				+ bankAccount + "]";
	}
	

	

}
