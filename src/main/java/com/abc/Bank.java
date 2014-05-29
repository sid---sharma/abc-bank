package com.abc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Bank {
	//Provide a unique ID for every customer like an account ID of 9 digits
	//private static final int ID_CONSTANT = 100000000;
    private Map<Integer, Customer> customers;

    public Bank() {
        customers = new LinkedHashMap<Integer, Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.put(customer.hashCode(), customer);
    }
    
    public void removeCustomer(Customer customer){
    	int total = 0;
    	List<Account> accounts = customer.getAccounts();
    	{
    		for(Account a : accounts)
    		{
    			total += a.sumTransactions();
    			System.out.println(total);
    		}
    	}
    	if (total == 0)
    		customers.remove(customers.get(customer.hashCode()));
    }
    
    public int getNumberOfCustomers(){
    	return customers.size();
    }
    
    public Customer getCustomer(String str)
    {
    	for(Integer key : customers.keySet())
    		if(customers.get(key).getName().equals(str))
    			return customers.get(key);
    	return null;
    }
    
    public Customer getCustomerByAccountID(Integer id)
    {
    	return customers.get(id);
    }
    
    
    

    public String customerSummary() {
        String summary = "Customer Summary";
        for(Integer key : customers.keySet())
            summary += "\n - " + customers.get(key).getName() + " (" + format(customers.get(key).getNumberOfAccounts(), "account") + ")";
        return summary;
    }

    //Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    private String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    public double totalInterestPaid() {
        double total = 0;
        for(Integer key : customers.keySet())
            total += customers.get(key).totalInterestEarned();
        return total;
    }

    public String getFirstCustomer() {
        if(getNumberOfCustomers()>0)
            return customers.get(0).getName();
        else
            return "Error";
    }
}
