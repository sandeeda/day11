package com.capgemini.bankapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.repository.BankAccountRepository;
import com.capgemini.bankapp.service.BankAccountService;


@Service
public class BankAccountServiceImpl implements BankAccountService {

	private BankAccountRepository bankAccountRepository;

	@Override
	public double getBalance(long accountId) {
		// TODO Auto-generated method stub
		return bankAccountRepository.getBalance(accountId);
	}

	@Autowired
	private BankAccountServiceImpl(BankAccountRepository bankAccountRepository) {
		super();
		this.bankAccountRepository = bankAccountRepository;
	}


	/*	public void setBankAccountRepository(BankAccountRepository bankRepository) {
		this.bankAccountRepository = bankRepository;
	}*/
	@Override
	public double withdraw(long accountId, double amount)throws LowBalanceException {
		// TODO Auto-generated method stub
		if(bankAccountRepository.getBalance(accountId)-amount>=0)
		{
			amount=bankAccountRepository.getBalance(accountId)-amount;
			if(bankAccountRepository.updateBalance(accountId, amount))
			{
				return bankAccountRepository.getBalance(accountId);
			}
			else
			{
				return bankAccountRepository.getBalance(accountId);
			}
		}
		else
		{
			throw new LowBalanceException("paisa khatam");
		}
	}

	@Override
	public double deposit(long accountId, double amount) {
		// TODO Auto-generated method stub
		if(bankAccountRepository.getBalance(accountId)>=0)
		{
			amount=bankAccountRepository.getBalance(accountId)+amount;
			if(bankAccountRepository.updateBalance(accountId, amount))
			{
				return bankAccountRepository.getBalance(accountId);
			}
			else
			{
				return bankAccountRepository.getBalance(accountId);
			}
		}
		return bankAccountRepository.getBalance(accountId);	}

	@Override
	public boolean fundTransfer(long fromAccount, long toAccount, double amount)throws LowBalanceException {
		// TODO Auto-generated method stub
		if(bankAccountRepository.getBalance(fromAccount)>=amount)
		{
			if(bankAccountRepository.getBalance(toAccount)>=0)
			{
				withdraw(fromAccount, amount);
				deposit(toAccount, amount);
				return true;

			}
		}
		return false;
	}

	@Override
	public BankAccount findBankAccountById(long accountId) {
		// TODO Auto-generated method stub
		return bankAccountRepository.findBankAccountById(accountId);
	}

	@Override
	public List<BankAccount> findAllBankAccounts() {
		// TODO Auto-generated method stub
		return bankAccountRepository.findAllBankAccounts();
	}

	@Override
	public BankAccount updateBankAccount(BankAccount account) {
		// TODO Auto-generated method stub
		return bankAccountRepository.updateBankAccount(account);
	}

	@Override
	public boolean deleteBankAccount(long accountId) {
		// TODO Auto-generated method stub
		return bankAccountRepository.deleteBankAccount(accountId);
	}

	@Override
	public boolean addBankAccount(BankAccount account) {
		// TODO Auto-generated method stub
		return bankAccountRepository.addBankAccount(account);
	}

}
