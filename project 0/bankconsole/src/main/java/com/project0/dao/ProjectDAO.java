package com.project0.dao;

import java.util.List;

import com.project0.exception.BankingException;
import com.project0.model.Project;
import com.project0.model.Transaction;

public interface ProjectDAO {
	public Project createAccount(Project project) throws BankingException;

	public String getCustomerByEmailId(String userEmail) throws BankingException;

	public String getCustomerByPassword(String userPassword) throws BankingException;

	public String getEmployeeByEmailId(String userEmail) throws BankingException;

	public String getEmployeeByPassword(String userPassword) throws BankingException;

	public Transaction openAccount(Transaction transaction) throws BankingException;

	public void updateCustomerId(int userId, int transactionId) throws BankingException;

	public float getTransactionCredit(int accountId) throws BankingException;

	public float getTransactionDebit(int accountId) throws BankingException;

	public float getTransactionTransfer(int accountId) throws BankingException;

	public float getTransactionTotalAmount(int accountId) throws BankingException;

	public float setTransactionCredit(int accountId) throws BankingException;

	public float setTransactionDebit(int accountId) throws BankingException;

	public void setTransactionTransfer(int accountId) throws BankingException;

	public List<Transaction> getViewStatement(int accountId) throws BankingException;

	public int getCustomerByCustomerId(String userEmail) throws BankingException;

	public Transaction getCustomerByCustomerId(int id) throws BankingException;

	public Transaction creditMoney(Transaction transaction) throws BankingException;

	public Transaction deductMoney(Transaction transaction) throws BankingException;

	public Transaction transferMoney(Transaction transaction) throws BankingException;

	public boolean getCheckForAccount(int id) throws BankingException;
}
