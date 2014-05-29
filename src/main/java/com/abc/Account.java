package com.abc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Account {

    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    private final int accountType;
    public List<Transaction> transactions;
    private Date maxiWithdrawalDate;
    public DateProvider dateProvider;

    public Account(int accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
        maxiWithdrawalDate = new Date();
        dateProvider = new DateProvider();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

public Date maxiSavingsWithdrawalDate()
{
	return maxiWithdrawalDate;
}
    
public void withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } else {
        transactions.add(new Transaction(-amount));
        if(accountType == MAXI_SAVINGS)
        {
        	maxiWithdrawalDate = dateProvider.now();
        }
    }
}

public void withdraw_testDouble(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } else {
        transactions.add(new Transaction(-amount));
        if(accountType == MAXI_SAVINGS)
        {
        	maxiWithdrawalDate = dateProvider.subtract15Days();
        }
    }
}

    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;
            case MAXI_SAVINGS:
            {
                System.out.println(dateProvider.now().getTime() - maxiWithdrawalDate.getTime());	
            	if((dateProvider.now().getTime() - maxiWithdrawalDate.getTime()) > 10 * 24 * 3600 * 1000)
                		return amount * 0.05;
                	else
                		return amount * 0.001;
            }
            default:
                return amount * 0.001;
        }
    }

    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    public int getAccountType() {
        return accountType;
    }
    
    public void transfer(Account b, double amount)
    {
    	this.withdraw(amount);
    	b.deposit(amount);
    }
    

}
