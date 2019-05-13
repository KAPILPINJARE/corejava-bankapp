package com.capgemini.bankapp.model;

import com.capgemini.bankapp.exceptions.DebitLimitExceedsException;
import com.capgemini.bankapp.exceptions.InsufficientFund;

public class SavingAccount extends BankAccount
{
	private static final int MIN_BALANCE = 500;
	private boolean isSalaryAccount;
	public SavingAccount()
	{
		super();
	}

	public SavingAccount(long accountId, String accountHolderName, String accountType, double accountBalance, boolean isSalaryAccount)
	{
		super(accountId, accountHolderName, accountType, accountBalance);
		this.isSalaryAccount = isSalaryAccount;
	}
	
	//override method from bank account
	@Override
	public double deposit(double amount)
	{
		return super.deposit(amount);
	}
	
	//override method from bank account 
	//here checking for if salary account or not
	//changes are made here according to salary account or not
	@Override
	public double withdraw(double amount) throws InsufficientFund, DebitLimitExceedsException
	{
		if(isSalaryAccount)
			return super.withdraw(amount);
		else
			if(getAccountBalance() - amount >= MIN_BALANCE)
				return getAccountBalance()-amount;
			else
				throw new InsufficientFund("insuffiecient funds");
	}
}
