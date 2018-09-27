package com.sandeep.bank.service;

import com.sandeep.bank.exceptions.PayeeAccountNotFoundException;

public interface BankAccountService {

	
	public double getBalance(long accountId);
	public double withdraw(long accountId, double amount);
	public double deposit(long accountId, double amount);
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws PayeeAccountNotFoundException;

}
