package com.learn.CoreJava.OOP.Inheritance;

// Parent class (Base Account)
class BankAccount {
    protected double balance; // Protected allows subclass access

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

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

    public double getBalance() {
        return balance;
    }
}

// Child class (Savings Account)
class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(double interestRate) {
        this.interestRate = interestRate;
    }

    public void addInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest added: " + interest);
    }
}

public class InheritanceIntermExample {
    public static void main(String[] args) {
        SavingsAccount mySavings = new SavingsAccount(5.0);
        mySavings.deposit(1000.00);
        mySavings.addInterest();
        System.out.println("Final Balance: " + mySavings.getBalance());
    }
}
