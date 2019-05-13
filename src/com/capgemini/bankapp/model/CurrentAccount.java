package com.capgemini.bankapp.model;

import com.capgemini.bankapp.exceptions.DebitLimitExceedsException;

public class CurrentAccount extends BankAccount
{
	private double borrowedAmount;
	private double debitLimit;

	public CurrentAccount()
	{
		super();
	}

	public CurrentAccount(long accountId, String accountHolderName, String accountType, double accountBalance,
			double debitLimit)
	{
		super(accountId, accountHolderName, accountType, accountBalance);
		this.debitLimit = debitLimit;
	}

	public double getBorrowedAmount()
	{
		return borrowedAmount;
	}

	public void setBorrowedAmount(double borrowedAmount)
	{
		this.borrowedAmount = borrowedAmount;
	}

	public CurrentAccount(double debitLimit)
	{
		super();
		this.debitLimit = debitLimit;
	}

	// Override deposit method from BankAccount class
	// added new features
	@Override
	public double deposit(double amount)
	{
		if (borrowedAmount >= amount)
		{
			borrowedAmount -= amount;
			return getAccountBalance();
		} else if (borrowedAmount < amount && borrowedAmount > 0)
		{
			setAccountBalance(amount - borrowedAmount);
			return getAccountBalance();
		} else
			return super.deposit(amount);
	}

	@Override
	public double withdraw(double amount) throws DebitLimitExceedsException
	{
		double temp = getAccountBalance() - amount;
		if (temp >= 0)
			setAccountBalance(temp);
		else if (Math.abs(temp) <= (debitLimit - borrowedAmount))
		{
			setAccountBalance(0);
			borrowedAmount = borrowedAmount + Math.abs(temp);
		} else
			throw new DebitLimitExceedsException("debit limit exceeds");

		return getAccountBalance();

	}

}
