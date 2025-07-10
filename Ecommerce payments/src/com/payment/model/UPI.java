package com.payment.model;

public class UPI implements PaymentGateway {
    private String upiId;

    public UPI(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("📲 Processing payment of ₹" + amount + " via UPI ID: " + upiId + "...");
        System.out.println("✅ UPI Payment Successful!");
    }

    @Override
    public void refund(double amount) {
        System.out.println("↩️ Refunding ₹" + amount + " via UPI...");
        System.out.println("✅ Refund Successful!");
    }
}