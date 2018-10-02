package com.sandeep.bank.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.sandeep.bank.dao.BankAccountDao;
import com.sandeep.bank.exceptions.AccountIdNotFoundException;
import com.sandeep.bank.exceptions.InsufficientFundException;
import com.sandeep.bank.service.BankAccountService;

@Service
public class BankAccountServiceImpl implements BankAccountService {
	
	@Autowired
	private BankAccountDao bankAccountDao;

	@Override
	public double getBalance(long accountId) throws AccountIdNotFoundException  {
		
		try {
			return bankAccountDao.getBalance(accountId);

		} catch (DataAccessException dx) {
			// TODO: handle exception
			AccountIdNotFoundException ax = new AccountIdNotFoundException("account not found");
			ax.initCause(dx);
			throw ax;
		}

	}

	@Override
	public double withdraw(long accountId, double amount) throws InsufficientFundException {
	
		
		try {
			double balance=bankAccountDao.getBalance(accountId);
			if(amount<=balance)
			{
				balance-=amount;
				bankAccountDao.updateBalance(accountId, balance);
			}
			return balance;
		} catch (DataAccessException dx) {
			// TODO: handle exception
			InsufficientFundException ix = new InsufficientFundException("insufficient funds");
			ix.initCause(dx);
			throw ix;
		}
		
	}

	@Override
	public double deposit(long accountId, double amount) throws AccountIdNotFoundException {
		try {
			double balance= bankAccountDao.getBalance(accountId);
			balance+=amount;
			bankAccountDao.updateBalance(accountId, balance);
			return balance;
		} catch (DataAccessException dx) {
			// TODO: handle exception
			AccountIdNotFoundException ax = new AccountIdNotFoundException("account not found");
			ax.initCause(dx);
			throw ax;
			
		}
	}

	@Override
	public boolean fundTransfer(long fromAcc, long toAcc, double amount) throws AccountIdNotFoundException {
		
		try {
			double payeeBalance=bankAccountDao.getBalance(toAcc);
			double payerBalance = bankAccountDao.getBalance(fromAcc);
			if(payerBalance>=amount)
			{
				payerBalance-=amount;
				bankAccountDao.updateBalance(fromAcc, payerBalance);
				bankAccountDao.updateBalance(toAcc, payeeBalance+amount);
				return true;
			}
			return false;
		} catch (DataAccessException dx) {
			// TODO: handle exception
			AccountIdNotFoundException ax=new AccountIdNotFoundException("payee not found or low balance");
			ax.initCause(dx);
			throw ax;
		}
		
		
		
	}

}
