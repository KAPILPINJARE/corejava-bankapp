package com.capgemini.bankapp.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.bankapp.exceptions.DebitLimitExceedsException;
import com.capgemini.bankapp.exceptions.InsufficientFund;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.model.SavingAccount;

public class SavingAccountTest
{
	// back account references
	BankAccount account1;
	BankAccount account2;

	@Before
	public void setUp() throws Exception
	{
		// using references of parent class bank account objects of saving account i.e.
		// child class created

		// this object shows salary Account is true
		account1 = new SavingAccount(101, "kapil", "SAVING", 5000, true);

		// this object shows salary Account is false
		account2 = new SavingAccount(101, "kapil", "SAVING", 5000, false);
	}

	@Test
	public void testSavingAccountDeposite()
	{
		assertEquals(5500, account1.deposit(500), 0.01);
	}

	@Test
	public void testSavingAccountIfSalaryAccountAndFundIsSufficient()
			throws DebitLimitExceedsException, InsufficientFund
	{
		assertEquals(500, account1.withdraw(4500), 0.01);
	}

	@Test(expected = InsufficientFund.class)
	public void testSavingAccountIfSalaryAccountAndFundIsNotSufficient()
			throws DebitLimitExceedsException, InsufficientFund
	{
		assertEquals(5000, account1.withdraw(5500), 0.01);
	}

	// using account2 object bcoz passing false for salary account
	@Test
	public void testSavingAccountIfNotSalaryAccountAndFundIsSufficient()
			throws InsufficientFund, DebitLimitExceedsException
	{
		assertEquals(4500, account2.withdraw(500), 0.01);
	}

	@Test(expected = InsufficientFund.class)
	public void testSavingAccountIfNotSalaryAccountAndFundIsNotSufficient()
			throws InsufficientFund, DebitLimitExceedsException
	{
		assertEquals(5000, account2.withdraw(5500), 0.01);
	}
}
