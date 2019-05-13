package com.capgemini.bankapp.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.bankapp.exceptions.DebitLimitExceedsException;
import com.capgemini.bankapp.exceptions.InsufficientFund;
import com.capgemini.bankapp.model.CurrentAccount;

public class CurrentAccountTest
{
	CurrentAccount ca;

	@Before
	public void setUp() throws Exception
	{
		ca = new CurrentAccount(101, "kapil", "CURRENT", 20000, 20000);
	}

	// check for deposit if no borrowed amount
	@Test
	public void testCurrentAccountForDepositIfNoBorrowedAmount()
	{
		ca.setBorrowedAmount(0);
		assertEquals(70000, ca.deposit(50000), 0.01);
	}

	// check for deposit if borrowed amount is equal to current balance
	@Test
	public void testCurrentAccountForDepositIfBorrowedAmountIsEqualToAmount()
	{
		ca.setBorrowedAmount(5000);
		assertEquals(20000, ca.deposit(5000), 0.01);
		assertEquals(0, ca.getBorrowedAmount(), 0.01);
	}

	// check for deposit if borrowed amount is greater or equal to current balance
	@Test
	public void testCurrentAccountForDepositIfBorrowedAmountIsMoreThanAmount()
	{
		ca.setBorrowedAmount(7000);
		assertEquals(20000, ca.deposit(5000), 0.01);
		assertEquals(2000, ca.getBorrowedAmount(), 0.01);
	}

	// check for withdraw if sufficient funds and borrowed amount is zero
	@Test
	public void testCurrentAccountForWithdrawIfFundSufficient() throws InsufficientFund, DebitLimitExceedsException
	{
		ca.setBorrowedAmount(0);
		assertEquals(10000, ca.withdraw(10000), 0.01);
		assertEquals(0, ca.getBorrowedAmount(), 0.01);
	}

	// check for withdraw if sufficient funds and borrowed amount is zero
	@Test
	public void testCurrentAccountForWithdrawIfFundNotSufficient() throws InsufficientFund, DebitLimitExceedsException
	{
		ca.setBorrowedAmount(0);
		assertEquals(0, ca.withdraw(30000), 0.01);
		assertEquals(10000, ca.getBorrowedAmount(), 0.01);
	}

	// check for withdraw if not sufficient funds and having sufficient borrowed
	// amount
	@Test
	public void testCurrentAccountForWithdrawIfFundNotSufficientAndHavingSufficientBorowedAmount()
			throws InsufficientFund, DebitLimitExceedsException
	{
		ca = new CurrentAccount(101, "kapil", "CURRENT", 0, 20000);
		ca.setBorrowedAmount(10000);
		assertEquals(0, ca.withdraw(10000), 0.01);
		assertEquals(20000, ca.getBorrowedAmount(), 0.01);
	}

	// check for withdraw if not sufficient funds and having sufficient borrowed
	// amount
	@Test
	public void testCurrentAccountForWithdrawIfFundNotSufficientAndHavingSufficientBorowedAmountCheckAgain()
			throws InsufficientFund, DebitLimitExceedsException
	{
		ca = new CurrentAccount(101, "kapil", "CURRENT", 10000, 20000);
		ca.setBorrowedAmount(0);
		assertEquals(0, ca.withdraw(25000), 0.01);
		assertEquals(15000, ca.getBorrowedAmount(), 0.01);
	}

	// check for withdraw if not sufficient funds and also not having sufficient borrowed amount
	//check for throw exception
	@Test(expected = DebitLimitExceedsException.class)
	public void testCurrentAccountForWithdrawIfFundNotSufficientAndNotEnoughBorrowedAmount()
			throws InsufficientFund, DebitLimitExceedsException
	{
		ca = new CurrentAccount(101, "kapil", "CURRENT", 0, 20000);
		ca.setBorrowedAmount(10000);
		assertEquals(0, ca.withdraw(60000), 0.01);
		assertEquals(0, ca.getBorrowedAmount(), 0.01);
	}
}
