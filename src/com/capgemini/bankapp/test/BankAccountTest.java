package com.capgemini.bankapp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.bankapp.exceptions.DebitLimitExceedsException;
import com.capgemini.bankapp.exceptions.InsufficientFund;
import com.capgemini.bankapp.model.BankAccount;

public class BankAccountTest 
{
	private BankAccount account;
	
	@Before
	public void setUp() {
		account = new BankAccount(101,"kapil","SAVING", 45000.0);
	}
	
	@Test
	public void testBankAccountObjectIsCreatedWithDefaultConstructor()
	{
		BankAccount account = new BankAccount();
		assertNotNull(account);
	}
	
	@Test
	public void testBankAccountObjectIsCreatedWithParameterizedConstructor()
	{
		BankAccount account = new BankAccount(101,"kapil","SAVING", 45000.0);
		assertNotNull(account);
		assertEquals(101, account.getAccountId());
		assertEquals("kapil", account.getAccountHolderName());
		assertEquals("SAVING", account.getAccountType());
		assertEquals(45000, account.getAccountBalance(),0.01);
	}
	
	@Test
	public void testBankAccountCurrentBalance()
	{
		assertEquals(45000, account.getAccountBalance(),0.01);
	}
	
	@Test
	public void testWithdrawWithSufficientFund() throws InsufficientFund, DebitLimitExceedsException
	{
		account.withdraw(5000);
		assertEquals(40000, account.getAccountBalance(),0.01);
	}
	
	@Test(expected = InsufficientFund.class)
	public void testWithdrawWithInSufficientFund() throws InsufficientFund, DebitLimitExceedsException
	{
		account.withdraw(55000);
		assertEquals(45000, account.getAccountBalance(),0.01);
	}
	
	@Test
	public void testDeposit()
	{
		account.deposit(5000);
		assertEquals(50000, account.getAccountBalance(),0.01);
	}
}

