package com.project0.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.project0.dao.impl.ProjectDaoImpl;
import com.project0.exception.BankingException;

class ProjectDAOTest {
	
	private static ProjectDAO service;

	@BeforeAll
	public static void setup() {
		service = new ProjectDaoImpl();
	}

	
	@Test
	void testCreateAccount() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCustomerByEmailId() {
		try {
			assertEquals("Deepak@1234",service.getCustomerByEmailId("Deepak@1234"));
		} catch (BankingException e) {
		 fail("Account not Found");
		}	
	}

	@Test
	void testGetCustomerByPassword() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEmployeeByEmailId() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEmployeeByPassword() {
		fail("Not yet implemented");
	}

	@Test
	void testOpenAccount() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateCustomerId() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTransactionCredit() {
		try {
			assertEquals(1500f,service.getTransactionTotalAmount(2));
		} catch (BankingException e) {
		 fail("Account not Found");
		}	
	}

	@Test
	void testGetTransactionDebit() {
		try {
			assertEquals(1500f,service.getTransactionTotalAmount(2));
		} catch (BankingException e) {
		 fail("Account not Found");
		}	
	}

	@Test
	void testGetTransactionTransfer() {
		try {
			assertEquals(1500f,service.getTransactionTotalAmount(2));
		} catch (BankingException e) {
		 fail("Account not Found");
		}	
	}

	@Test
	void testGetTransactionTotalAmount() {
		try {
			assertEquals(1500f,service.getTransactionTotalAmount(2));
		} catch (BankingException e) {
		 fail("Account not Found");
		}	
	}
	

	@Test
	void testSetTransactionCredit() {
		fail("Not yet implemented");
	}

	@Test
	void testSetTransactionDebit() {
		fail("Not yet implemented");
	}

	@Test
	void testSetTransactionTransfer() {
		fail("Not yet implemented");
	}

	@Test
	void testGetViewStatement() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCustomerByCustomerIdString() {
		try {
			assertEquals(2,service.getCustomerByCustomerId("shukladhit@gmail.com"));
		} catch (BankingException e) {
		 fail("Account not Found");
		}	
	}

	@Test
	void testGetCustomerByCustomerIdInt() {
		try {
			assertEquals(2,service.getCustomerByCustomerId("shukladhit@gmail.com"));
		} catch (BankingException e) {
		 fail("Account not Found");
		}	
	}

	@Test
	void testCreditMoney() {
		try {
			assertEquals(1500f,service.getTransactionTotalAmount(2));
		} catch (BankingException e) {
		 fail("Account not Found");
		}	
	}

	@Test
	void testDeductMoney() {
		try {
			assertEquals(1500f,service.getTransactionTotalAmount(2));
		} catch (BankingException e) {
		 fail("Account not Found");
		}	
	}

	@Test
	void testTransferMoney() {
		try {
			assertEquals(1500f,service.getTransactionTotalAmount(2));
		} catch (BankingException e) {
		 fail("Account not Found");
		}	
	}

	@Test
	void testGetCheckForAccount() {
		try {
			assertEquals(1500f,service.getTransactionTotalAmount(2));
		} catch (BankingException e) {
		 fail("Account not Found");
		}	
	}

}
