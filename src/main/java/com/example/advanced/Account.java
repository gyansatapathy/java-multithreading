package com.example.advanced;

public class Account {
    private final int accountId;
    public int getAccountId() {
        return accountId;
    }

    private int balance;

    public Account(int accountId, int balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public void transfer(Account to, int amount) throws Exception{
        this.withdraw(amount);
        to.deposit(amount);
    }

    public void withdraw(int amount) throws Exception{
        if(balance < amount){
            throw new Exception("Not Sufficient Balance");
        }
        this.balance -= amount;
    }

    public void deposit(int amount){
        this.balance += amount;
    }
    
    public int getBalance() {
        return this.balance;
    }
}
