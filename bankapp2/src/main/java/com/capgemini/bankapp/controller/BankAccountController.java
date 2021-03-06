package com.capgemini.bankapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.service.BankAccountService;

@Controller
public class BankAccountController {
	
	private BankAccountService bankAccountService;
/*	public void setBankAccountService(BankAccountService bankAccountService) {
		this.bankAccountService = bankAccountService;
	}*/
	
	public double getBalance(long accountId) {
		return bankAccountService.getBalance(accountId);
	}
	
	@Autowired
	private BankAccountController(BankAccountService bankAccountService) {
		super();
		this.bankAccountService = bankAccountService;
	}

	public double withdraw(long accountId, double amount)throws LowBalanceException{
		return bankAccountService.withdraw(accountId, amount);
	}
	
	
	public double deposit(long accountId, double amount) {
		return bankAccountService.deposit(accountId, amount);
	}
	
	public boolean fundTransfer(long fromAccount, long toAccount, double amount) throws LowBalanceException{
		return bankAccountService.fundTransfer(fromAccount, toAccount, amount);
	}
	public BankAccount findBankAccountById(long accountId) {
		// TODO Auto-generated method stub
		return bankAccountService.findBankAccountById(accountId);
	}

	public List<BankAccount> findAllBankAccounts() {
		// TODO Auto-generated method stub
		return bankAccountService.findAllBankAccounts();
	}

	public BankAccount updateBankAccount(BankAccount account) {
		// TODO Auto-generated method stub
		return bankAccountService.updateBankAccount(account);
	}

	public boolean deleteBankAccount(long accountId) {
		// TODO Auto-generated method stub
		return bankAccountService.deleteBankAccount(accountId);
	}

	public boolean addBankAccount(BankAccount account) {
		// TODO Auto-generated method stub
		return bankAccountService.addBankAccount(account);
	}
}
