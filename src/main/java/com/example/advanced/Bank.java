package com.example.advanced;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<Integer, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountId(), account);
    }

    public boolean transfer(int fromId, int toId, int amount) {
        Account from = accounts.get(fromId);
        Account to = accounts.get(toId);
        
        // Ordered locking: Always lock smaller ID first to prevent deadlock
        Account first = fromId < toId ? from : to;
        Account second = fromId < toId ? to : from;
        
        synchronized (first) {
            synchronized (second) {
                try {
                    from.withdraw(amount);
                    to.deposit(amount);
                    System.out.println("Transfer: Account " + fromId + " → Account " + toId + ": $" + amount);
                    return true;
                } catch (Exception e) {
                    System.out.println("Transfer failed: " + e.getMessage());
                    return false;
                }
            }
        }
    }
    
    public int getTotalBalance() {
        int total = 0;
        for (Account account : accounts.values()) {
            total += account.getBalance();
        }
        return total;
    }
    
    public void printAccountBalances() {
        for (Account account : accounts.values()) {
            System.out.println("Account " + account.getAccountId() + ": $" + account.getBalance());
        }
    }

}
