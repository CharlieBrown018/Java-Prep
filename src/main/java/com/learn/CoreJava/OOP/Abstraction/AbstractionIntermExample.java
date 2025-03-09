package com.learn.CoreJava.OOP.Abstraction;

// Interface
interface Payment {
    void processPayment(double amount);
}

// Concrete class implementing the interface
class CreditCardPayment implements Payment {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount + " using card: " + cardNumber);
    }
}

// Another concrete class implementing the interface
class PayPalPayment implements Payment {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount + " using email: " + email);
    }
}

public class AbstractionIntermExample {
    public static void main(String[] args) {
        Payment creditCard = new CreditCardPayment("1234-5678-9876-5432");
        creditCard.processPayment(150.75);

        Payment paypal = new PayPalPayment("user@example.com");
        paypal.processPayment(89.99);
    }
}
