package com.abc;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;
/**Story 1: Bank Manager want's John's account summary 
*
 * */
    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        john.openAccount(new Account(Account.MAXI_SAVINGS));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (2 accounts)", bank.customerSummary());
    }
/**
 * Story 2: The Bank Manager wants a list of all customer and the number of accounts they have
 * Story 3: The Bank Manager also wants to find out the total interest paid
 * 
 * */
    @Test
    public void customersSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.CHECKING));
        john.openAccount(new Account(Account.MAXI_SAVINGS));
        bank.addCustomer(john);
        Customer henry = new Customer("Henry");
        henry.openAccount(new Account(Account.CHECKING));
        henry.openAccount(new Account(Account.SAVINGS));
        bank.addCustomer(henry);
        Customer mike = new Customer("Mike");
        mike.openAccount(new Account(Account.CHECKING));
        bank.addCustomer(mike);
        Customer olga = new Customer("Olga");
        olga.openAccount(new Account(Account.CHECKING));
        olga.openAccount(new Account(Account.SAVINGS));
        olga.openAccount(new Account(Account.MAXI_SAVINGS));
        bank.addCustomer(olga);
        System.out.println(bank.customerSummary());
        assertEquals("Customer Summary\n" + 
        		" - John (2 accounts)\n" + 
        		" - Henry (2 accounts)\n" + 
        		" - Mike (1 account)\n" + 
        		" - Olga (3 accounts)", bank.customerSummary());
        
        List<Account> johnAccounts = john.getAccounts();
        for(Account a : johnAccounts)
        {
        	a.deposit(1200);
        }
        johnAccounts.get(0).withdraw(300);
        List<Account> henryAccounts = henry.getAccounts();
        henryAccounts.get(1).deposit(1000);
        henryAccounts.get(0).deposit(200);
        henryAccounts.get(1).withdraw(100);
        
        assertEquals(3.2, bank.totalInterestPaid(), DOUBLE_DELTA);
        
        
    }
    
    
    /**
     * Story 4: John wants to transfer 200 dollars from his MAXI_SAVINGS account to his CHECKING account
     * Story 5: John hasn't performed withdrawal on MAXI_SAVINGS in the last in the last 10 days
     * */
    @Test
    
    public void transferAccount()
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
         johnAccounts.get(0).withdraw(300);
         johnAccounts.get(0).transfer(johnAccounts.get(1),200);
         
         
         assertEquals("Statement for John\n" + 
         		"\n" + 
         		"Checking Account\n" + 
         		"  deposit $1,200.00\n" + 
         		"  withdrawal $300.00\n" + 
         		"  withdrawal $200.00\n" + 
         		"Total $700.00\n" + 
         		"\n" + 
         		"Maxi Savings Account\n" + 
         		"  deposit $1,200.00\n" + 
         		"  deposit $200.00\n" + 
         		"Total $1,400.00\n" + 
         		"\n" + 
         		"Total In All Accounts $2,100.00", john.getStatement());
    }
    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);

        assertEquals(3.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
