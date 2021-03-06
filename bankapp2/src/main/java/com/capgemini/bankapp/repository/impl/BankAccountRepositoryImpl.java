package com.capgemini.bankapp.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.repository.BankAccountRepository;
import com.capgemini.bankapp.util.DbUtil;


@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public double getBalance(long accountId) {
		return jdbcTemplate.queryForObject("select accountbalance from springbankdata where accountid=?", new Object[] {accountId},double.class);
		
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		if(jdbcTemplate.update("update springbankdata set accountbalance = ? where accountid = ?", new Object[] {newBalance,accountId})!=0)
		{
			return true;
		}
		return false;
		
	}

	@Override
	public boolean addBankAccount(BankAccount account) {
		int count = jdbcTemplate.update("insert into springbankdata values(?,?,?,?)", new Object[] {account.getAccountId(),account.getAccountHolderName(),account.getAccountType(),account.getAccountBalance()});
		return count!=0;
	}

	@Override
	public BankAccount findBankAccountById(long accountId) {
		return jdbcTemplate.queryForObject("select * from springbankdata where accountid=?",new Object[] {accountId},new BankAccountRowMapper());
		
	}

	@Override
	public List<BankAccount> findAllBankAccounts() {
		return jdbcTemplate.query("select * from springbankdata ", new Object[] {},new BankAccountRowMapper());
	}

	@Override
	public BankAccount updateBankAccount(BankAccount account) {
		int count=jdbcTemplate.update("update springbankdata set accounttype=?,accountholdername=? where accountid=?",new Object[] {account.getAccountType(),account.getAccountHolderName(),account.getAccountId()});
		if(count==1)
			return account;
		else
			return findBankAccountById(account.getAccountId());
	}

	@Override
	public boolean deleteBankAccount(long accountId) {
		int count = jdbcTemplate.update("delete from springbankdata where accountid=?",new Object[] {accountId});
		return count!=0;
	}
	
	
	private class BankAccountRowMapper implements RowMapper<BankAccount>{
		@Override
		public BankAccount mapRow (ResultSet rs , int rowNum) throws SQLException {
			BankAccount bankAccount = new BankAccount();
			bankAccount.setAccountBalance(rs.getLong(4));
			bankAccount.setAccountHolderName(rs.getString(2));
			bankAccount.setAccountId(rs.getLong(1));
			bankAccount.setAccountType(rs.getString(3));
			return bankAccount;
		}
	}
	
	
	
}
