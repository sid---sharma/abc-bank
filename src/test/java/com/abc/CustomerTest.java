package com.abc;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CustomerTest {
	private Bank bank;
	private Account checkingAccount;
	private Account savingsAccount;

	@Before
	public void initialize()
	{
		bank = new Bank();
		checkingAccount = new Account(Account.CHECKING);
		savingsAccount = new Account(Account.SAVINGS);
	}
	
	
/**Story 1 : John wants to open an account 
 * Scenario 1.1 : a) John customer object is created b) John specifies what type of account(s) he wants (Checking and Saving) c) John is 
 * added to the list of customers */
	
	
	@Test
	public void testOpenAccount(){
		Customer john = new Customer("John");
		john.openAccount(checkingAccount);
		john.openAccount(savingsAccount);
		bank.addCustomer(john);
		assertEquals(1, bank.getNumberOfCustomers());
		assertEquals(2, john.getNumberOfAccounts());
		assertEquals("John", bank.getCustomer("John").getName());
			
	}
	
	@Test
	public void testOpenAccountCreateMultiple()
	{
		for(int i=0;i<5;i++)
		{
			Customer john = new Customer("John");
			john.openAccount(checkingAccount);
			john.openAccount(savingsAccount);
			bank.addCustomer(john);
		}
		assertEquals(5, bank.getNumberOfCustomers());
	}
	
	


	
	
/**
 * Story 3 : Henry wants to deposit 100 dollars in his Checking account
 * Scenario 3.1 : The Deposit is successful. Return a statement for the account.
 * 
 * */	
	
	@Test
	public void testDeposit()
	{
		Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        
        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "Total $4,000.00\n" +
                "\n" +
                "Total In All Accounts $4,100.00", henry.getStatement());
    }
	
 /** Story 4 : A user wants to withdraw from a saving account.
  * Scenario 4.1 : The withdrawal is feasable
  * Sceanrio 4.2 : User doesn't have the amount to perform the withdrawal
  * */ 
	
	@Test
	public void testWithdrawal()
	{
		Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200);
        
        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
        checkingAccount.withdraw(200);
        assertEquals("Not sufficient funds to perform transaction", henry.getStatement());
        
        
        
    }
	
	/**
	 *Redundant==> covered in Story 4
	 * Story 5 : The customer wants to see transactions and the totals of each of their accounts
	 * Scenario 5.1 : If the transactions are feasible return the statement
	 * Scenario 5.2 : If the transactions are not feasible i.e. return the error
	 * 
	 * */
	
	@Test //Test customer statement generation
    public void testApp(){

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);
        

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new Account(Account.SAVINGS));
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new Account(Account.SAVINGS));
        oscar.openAccount(new Account(Account.CHECKING));
        oscar.openAccount(new Account(Account.MAXI_SAVINGS));
        assertEquals(3, oscar.getNumberOfAccounts());
    }
}
