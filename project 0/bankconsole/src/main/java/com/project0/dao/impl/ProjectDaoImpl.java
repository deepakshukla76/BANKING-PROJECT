package com.project0.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.project0.dao.ProjectDAO;
import com.project0.dbutil.PostgresConnection;
import com.project0.exception.BankingException;
import com.project0.model.Project;
import com.project0.model.Transaction;

public class ProjectDaoImpl implements ProjectDAO {
	private static Logger log = Logger.getLogger(ProjectDaoImpl.class);
	@Override
	public Project createAccount(Project project) throws BankingException {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into project_schema.customer(customer_name,contact_no,email_id,city,state,country,pan_number,password) values(?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, project.getName());
			preparedStatement.setInt(2, project.getContactNumber());
			preparedStatement.setString(3, project.getEmailId());
			preparedStatement.setString(4, project.getCity());
			preparedStatement.setString(5, project.getState());
			preparedStatement.setString(6, project.getCountry());
			preparedStatement.setString(7, project.getPanNumber());
			preparedStatement.setString(8, project.getPassword());

			int c = preparedStatement.executeUpdate();
			if (c == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					project.setCustomerId(resultSet.getInt(1));
				}
			} else {
				throw new BankingException("User Registration Failure Please Retry!!!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}

		return project;
	}
	@Override
	public String getCustomerByEmailId(String userEmail) throws BankingException {
		String string=null;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql  = "select email_id from project_schema.customer where email_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,userEmail);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				string+=resultSet.getString("email_Id");
			} else {
				throw new BankingException("No Account with id " + userEmail);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return string;
	}
	@Override
	public String getCustomerByPassword(String userPassword) throws BankingException {
		String string=null;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql  = "select password from project_schema.customer where password=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,userPassword);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				string+=resultSet.getString("password");
			} else {
				throw new BankingException("Invalid Password " + userPassword);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return string;
	}
	@Override
	public String getEmployeeByEmailId(String empEmail) throws BankingException {
		String string=null;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql  = "select emp_email_id from project_schema.employee where emp_email_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,empEmail);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				string+=resultSet.getString("emp_email_Id");
			} else {
				throw new BankingException("No Account found with id " + empEmail);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return string;
	}
	@Override
	public String getEmployeeByPassword(String empPassword) throws BankingException {
		String string=null;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql  = "select emp_password from project_schema.employee where emp_password=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,empPassword);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				string+=resultSet.getString("emp_password");
			} else {
				throw new BankingException("Invalid Password " + empPassword);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return string;
	}
	@Override
	public Transaction openAccount(Transaction transaction) throws BankingException {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into project_schema.transaction(transfer,withdraw,deposit,total_amount,accountid) values(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setFloat(1, transaction.getTransfer());
			preparedStatement.setFloat(2, transaction.getWithdraw());
			preparedStatement.setFloat(3, transaction.getDeposit());
			preparedStatement.setFloat(4, transaction.getTotalAmount());
			preparedStatement.setInt(5, transaction.getCustomerid());

			int c = preparedStatement.executeUpdate();
			if (c == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					transaction.setTransactionId(resultSet.getInt(1));
				}
			} else {
				throw new BankingException("User Account Opening Failure Please Retry!!!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}

		return transaction;
	}

	@Override
	public int getCustomerByCustomerId(String userEmail) throws BankingException {
	    int id=0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql  = "select customerid from project_schema.customer where email_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,userEmail);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
			  id=resultSet.getInt("customerid");
			} else {
				throw new BankingException("No Account found with id " + userEmail);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return id;
	}
	@Override
	public Transaction getCustomerByCustomerId(int id) throws BankingException {
		Transaction transaction=null;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select transaction,transfer,withdraw,deposit from project_schema.transaction where accountid=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				transaction=new Transaction();
				transaction.setTransactionId(resultSet.getInt("transactionid"));
				transaction.setTransfer(resultSet.getFloat("transfer"));
				transaction.setWithdraw(resultSet.getFloat("withdraw"));
				transaction.setDeposit(resultSet.getFloat("deposit"));
			}else {
				throw new BankingException("No Account with  id "+id);
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);;//logger
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return transaction;
	}
	@Override
	public Transaction creditMoney(Transaction transaction) throws BankingException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Transaction deductMoney(Transaction transaction) throws BankingException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Transaction transferMoney(Transaction transaction) throws BankingException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean getCheckForAccount(int id) throws BankingException {
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select accountid from project_schema.transaction where accountid=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}else {
				return false;
				
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);;//logger
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		//return transaction;
	}
	@Override
	public void updateCustomerId(int userId, int accountId) throws BankingException {
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="update project_schema.customer set accountid=? where customerid=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setFloat(1,accountId);
			preparedStatement.setInt(2,userId);
			int i=preparedStatement.executeUpdate(); 
			if(i==0) {
				throw new BankingException("Updation Error...");
			}
			else log.info("Account Linked");
		}catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);//logger
			throw new BankingException("Internal error occured...Kindly conatct SYSADMIN.......");
		}
		
	}
	
	
	@Override
	public float getTransactionCredit(int accountId) throws BankingException {
		
		float cr=0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql  = "select deposit from project_schema.transaction where accountid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
			  cr=resultSet.getFloat("deposit");
			} else {
				throw new BankingException("No Account found with id " + accountId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return cr;
	}
	
	@Override
	public float getTransactionDebit(int accountId) throws BankingException {
		float db=0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql  = "select withdraw from project_schema.transaction where accountid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
			  db=resultSet.getFloat("withdraw");
			} else {
				throw new BankingException("No Account found with id " + accountId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return db;
	}
	
	@Override
	public float getTransactionTransfer(int accountId) throws BankingException {
		float tf=0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql  = "select total_amount from project_schema.transaction where accountid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
			  tf=resultSet.getFloat("total_amount");
			} else {
				throw new BankingException("No Account found with id " + accountId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return tf;
		
	}
	@Override
	public float setTransactionCredit(int accountId) throws BankingException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float setTransactionDebit(int accountId) throws BankingException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void setTransactionTransfer(int accountId) throws BankingException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public float getTransactionTotalAmount(int accountId) throws BankingException {
		float ta=0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql  = "select total_amount from project_schema.transaction where accountid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
			  ta=resultSet.getFloat("total_amount");
			} else {
				throw new BankingException("No Account found with id " + accountId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return ta;
	}
	@Override
	public List<Transaction> getViewStatement(int accountId) throws BankingException {
		List<Transaction> transactionList=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select transactionid,transfer,withdraw,deposit,total_amount from project_schema.transaction where accountid=?";
			//String sql="select * from project0.transaction where accountid=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountId);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
			    Transaction transaction=new Transaction();
				transaction.setTransactionId(resultSet.getInt("transactionid"));
				transaction.setTransfer(resultSet.getFloat("transfer"));
				transaction.setWithdraw(resultSet.getFloat("withdraw"));
				transaction.setDeposit(resultSet.getFloat("deposit"));
				transaction.setTotalAmount(resultSet.getFloat("total_amount"));
				transactionList.add(transaction);
			}if(transactionList.size()==0) {
				throw new BankingException("No Account exists as of now.. go ahead create one...");
			}
		}
			catch (ClassNotFoundException | SQLException e) {
			log.error(e);;//logger
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		
		return transactionList;
	}
	
	

}
