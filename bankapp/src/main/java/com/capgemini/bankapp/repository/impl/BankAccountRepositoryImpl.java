package com.capgemini.bankapp.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapp.entities.BankAccount;
import com.capgemini.bankapp.repository.BankAccountRepository;
import com.capgemini.bankapp.util.DbUtil;


@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {

	@Autowired
	DbUtil dbUtil;
	private HashSet<BankAccount> accounts;
	
	/*	public BankAccountRepositoryImpl() {
		super();
		accounts = new HashSet<>();
		accounts.add(new BankAccount(1234, "John Doe", "SAVING", 1000));
		accounts.add(new BankAccount(5678, "Jane Doe", "SAVING", 2000));
		accounts.add(new BankAccount(9101, "whitman", "CURRENT", 3000));
		accounts.add(new BankAccount(2345, "Walter White", "SAVING", 4000));
		accounts.add(new BankAccount(1234, "Jessie Pinkman", "CURRENT", 5000));
	}
	 */
	@Override
	public double getBalance(long accountId) {
		String getAccountBalance="select accountbalance from springbankdata where accountid=?";
		try(Connection connection=dbUtil.getConnection();
				PreparedStatement getAccountBalanceStatement = connection.prepareStatement(getAccountBalance); )
		{
			getAccountBalanceStatement.setInt(1, (int) accountId);
			ResultSet result = getAccountBalanceStatement.executeQuery();
			if(result.next())
			{
				return (double)result.getLong(1);
				
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			
			
			e.printStackTrace();
			return -1;
		}

		return -1;
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {

		String updateAccountBalance="update springbankdata set accountbalance=? where accountid=?";
		try(Connection connection=dbUtil.getConnection();
				PreparedStatement updateAccountBalanceStatement = connection.prepareStatement(updateAccountBalance); )
		{
			updateAccountBalanceStatement.setLong(1, (long) newBalance);
			updateAccountBalanceStatement.setInt(2, (int) accountId);
			if(updateAccountBalanceStatement.executeUpdate()==1)
				return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			
			
			e.printStackTrace();
			return false;
		}

		return false;
	}

	@Override
	public boolean addBankAccount(BankAccount account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BankAccount findBankAccountById(long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankAccount> findAllBankAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount updateBankAccount(BankAccount account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteBankAccount(long accountId) {
		// TODO Auto-generated method stub
		return false;
	}

}
