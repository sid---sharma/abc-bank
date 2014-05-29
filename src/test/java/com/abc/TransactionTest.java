package com.abc;

import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TransactionTest {
    @Test
    public void transaction() {
        Transaction t = new Transaction(5);
        assertTrue(t instanceof Transaction);
    }
    
    /**
     * Story 1 : Maxi_Savings interest rate after 15 days of last withdrawal
     * */
    
    @Test
    public void maxiSavingsInterest()
    {
    	Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        john.openAccount(new Account(Account.MAXI_SAVINGS));
        bank.addCustomer(john);
        List<Account> johnAccounts = john.getAccounts();
        for(Account a : johnAccounts)
        {
        	a.deposit(1200);
        }
        //Use test double and fake out the withdrawal date (15 days past)
        johnAccounts.get(1).withdraw_testDouble(300);
        johnAccounts.get(0).transfer(johnAccounts.get(1),200);
        
        System.out.println(john.getStatement());
        System.out.println(bank.totalInterestPaid());
    }
    
}
