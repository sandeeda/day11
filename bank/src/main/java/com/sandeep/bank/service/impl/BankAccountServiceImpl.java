package com.sandeep.bank.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandeep.bank.dao.BankAccountDao;
import com.sandeep.bank.exceptions.PayeeAccountNotFoundException;
import com.sandeep.bank.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService {
	
	@Autowired
	private BankAccountDao bankAccountDao;

	@Override
	public double getBalance(long accountId) {
		return bankAccountDao.getBalance(accountId);

	}

	@Override
	public double withdraw(long accountId, double amount) {
		if(bankAccountDao.updateBalance(accountId, -1*amount))
			return bankAccountDao.getBalance(accountId);
		return bankAccountDao.getBalance(accountId);
		
	}

	@Override
	public double deposit(long accountId, double amount) {
		if(bankAccountDao.updateBalance(accountId, amount))
			return bankAccountDao.getBalance(accountId);
		return bankAccountDao.getBalance(accountId);
	}

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws PayeeAccountNotFoundException {
		System.out.println("i am in fund transfer service impl"+toAcc);
		if(bankAccountDao.getBalance(toAcc)<0)
		{
			throw new PayeeAccountNotFoundException("payee account not found");
		}
		
		else if(bankAccountDao.updateBalance(fromAcc, -1*amount))
		{
			if(bankAccountDao.updateBalance(toAcc, amount))
			{
				return true;
			}
		}
		throw new PayeeAccountNotFoundException("Fund transfer unsuccessfull");
	}

}
