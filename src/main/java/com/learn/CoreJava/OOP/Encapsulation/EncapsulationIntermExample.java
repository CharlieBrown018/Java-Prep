package com.learn.CoreJava.OOP.Encapsulation;

class BankAccount {
    private double balance; // Private variable (Encapsulation)

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Setter for balance with validation
    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("Error: Negative balance is not allowed.");
        }
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method with validation
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
        } else if (amount > 0) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }
}

public class EncapsulationIntermExample {
    public static void main(String[] args) {
        BankAccount myAccount = new BankAccount();

        myAccount.setBalance(1000.00);
        System.out.println("Current Balance: " + myAccount.getBalance());

        myAccount.deposit(500.00);
        myAccount.withdraw(300.00);
        myAccount.withdraw(1500.00); // Should show insufficient funds
    }
}
