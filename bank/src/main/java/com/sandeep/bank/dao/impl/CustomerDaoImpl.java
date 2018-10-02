package com.sandeep.bank.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sandeep.bank.dao.CustomerDao;
import com.sandeep.bank.exceptions.PasswordDetailsWrongException;
import com.sandeep.bank.model.BankAccount;
import com.sandeep.bank.model.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate; 
	@SuppressWarnings("serial")
	@Override
	public Customer authenticate(Customer customer) throws DataAccessException{
		// TODO Auto-generated method stub
		try {
			return jdbcTemplate.queryForObject("select * from customerdata where customerId=? and password=?",new Object[] {customer.getCustomerId(),customer.getPassword()}, new CustomerRowMapper());
		}
		catch(DataAccessException dx)
		{
			throw dx;
		}
	}

	@Override
	public Customer updateProfile(Customer customer)throws DataAccessException {
		// TODO Auto-generated method stub
		try {
			jdbcTemplate.update("UPDATE customerdata SET address=?,email=?  WHERE customerId=?",new Object[] {customer.getAddress(),customer.getEmail(),customer.getCustomerId()});
			return customer;
		} catch (DataAccessException dx) {
			// TODO: handle exception
			throw dx;
		}

	}

	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword)throws DataAccessException{
		// TODO Auto-generated method stub
		try {
			jdbcTemplate.update("update customerdata set password = ? where customerid=? and password=?",new Object[] {newPassword,customer.getCustomerId(),oldPassword});
			return true;
		} catch (DataAccessException dx) {
			// TODO: handle exception
			throw dx;
		}
	}


	private class CustomerRowMapper implements RowMapper<Customer>{
		@Override
		public Customer mapRow (ResultSet rs , int rowNum) throws SQLException {
			Customer customer = new Customer();
			BankAccount bankAccount= new BankAccount();
			customer.setAddress(rs.getString(5));
			customer.setPassword(rs.getString(3));
			customer.setCustomerId(rs.getInt(2));
			customer.setCustomerName(rs.getString(1));
			customer.setDateOfBirth(rs.getDate(6).toLocalDate());
			customer.setEmail(rs.getString(4));
			bankAccount= jdbcTemplate.queryForObject("select * from bankdata where accountid=?", new Object[] {rs.getInt(7)},new BankAccountRowMapper());
			customer.setBankAccount(bankAccount);
			return customer;
		}
	}


	private class BankAccountRowMapper implements RowMapper<BankAccount>
	{
		@Override
		public BankAccount mapRow (ResultSet rs , int rowNum) throws SQLException{
			BankAccount bankAccount = new BankAccount();
			bankAccount.setAccountId(rs.getInt(1));
			bankAccount.setAccountType(rs.getString(3));
			bankAccount.setBalance(rs.getInt(2));
			return bankAccount;

		}

	}

}
