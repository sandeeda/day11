package com.sandeep.bank.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sandeep.bank.dao.BankAccountDao;

@Repository
public class BankAccountDaoImpl implements BankAccountDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public double getBalance(long accountId)throws DataAccessException {
		try {
			return jdbcTemplate.queryForObject("select balance from bankdata where accountid=?", new Object[] {accountId},double.class);

		} catch (DataAccessException dx) {
			// TODO: handle exception
			throw dx;
		}

	}

	@Override
	public boolean updateBalance(long accountId, double newBalance)  throws DataAccessException{
		try {
			jdbcTemplate.update("update bankdata set balance=? where accountid=?",new Object[] {newBalance,accountId});
			return true;
		} catch (DataAccessException dx) {
			// TODO: handle exception
			throw dx;
		}
	}

}
