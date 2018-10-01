package com.sandeep.bank.dao;

import org.springframework.dao.DataAccessException;

public interface BankAccountDao {
	public double getBalance(long accountId) throws DataAccessException;
	public boolean updateBalance(long accountId, double newBalance)throws DataAccessException;

}
