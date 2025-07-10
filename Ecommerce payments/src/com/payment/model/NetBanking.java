package com.payment.model;

public class NetBanking implements PaymentGateway {
    private String bankName;
    private String userId;

    public NetBanking(String bankName, String userId) {
        this.bankName = bankName;
        this.userId = userId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("🏦 Processing payment of ₹" + amount + " via NetBanking (Bank: "
                + bankName + ", User ID: " + userId + ")...");
        System.out.println("✅ NetBanking Payment Successful!");
    }

    @Override
    public void refund(double amount) {
        System.out.println("↩️ Refunding ₹" + amount + " via NetBanking...");
        System.out.println("✅ Refund Successful!");
    }
}