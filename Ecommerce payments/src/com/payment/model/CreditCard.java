package com.payment.model;

public class CreditCard implements PaymentGateway {
    private String cardNumber;
    private String cvv;

    public CreditCard(String cardNumber, String cvv) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    @Override
    public void pay(double amount) {
        System.out.println("💳 Processing payment of ₹" + amount + " via Credit Card ending with "
                + cardNumber.substring(cardNumber.length() - 4) + "...");
        System.out.println("✅ Credit Card Payment Successful!");
    }

    @Override
    public void refund(double amount) {
        System.out.println("↩️ Refunding ₹" + amount + " via Credit Card...");
        System.out.println("✅ Refund Successful!");
    }
}