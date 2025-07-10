package com.pPayment.test;

import java.util.Scanner;

import com.payment.model.CreditCard;
import com.payment.model.NetBanking;
import com.payment.model.PaymentGateway;
import com.payment.model.UPI;

public class PaymentSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        System.out.println("🏪 Welcome to E-Commerce Payment System 🏪");

        while (!exit) {
            double amount = 0;
            while (true) {
                System.out.print("\n💰 Enter purchase amount: ₹");
                if (sc.hasNextDouble()) {
                    amount = sc.nextDouble();
                    sc.nextLine();
                    if (amount > 0) {
                        break;
                    } else {
                        System.out.println("❌ Amount must be greater than zero.");
                    }
                } else {
                    System.out.println("❌ Invalid input. Please enter a numeric value.");
                    sc.nextLine();
                }
            }

            System.out.println("\n💳 Choose Payment Method:");
            System.out.println("1. Credit Card");
            System.out.println("2. UPI");
            System.out.println("3. NetBanking");
            System.out.println("4. Exit");

            int choice = 0;
            while (true) {
                System.out.print("👉 Enter your choice: ");
                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine();
                    if (choice >= 1 && choice <= 4) {
                        break;
                    } else {
                        System.out.println("❌ Please choose a number between 1 and 4.");
                    }
                } else {
                    System.out.println("❌ Invalid input. Enter a number.");
                    sc.nextLine();
                }
            }

            if (choice == 4) {
                System.out.println("👋 Thank you for visiting. Goodbye!");
                exit = true;
                break;
            }

            PaymentGateway gateway = null;

            switch (choice) {
                case 1:
                    String cardNumber = "";
                    while (true) {
                        System.out.print("🔢 Enter 16-digit Credit Card Number: ");
                        cardNumber = sc.nextLine().trim();
                        if (cardNumber.matches("\\d{16}")) {
                            break;
                        } else {
                            System.out.println("❌ Invalid card number. Must be exactly 16 digits.");
                        }
                    }

                    String cvv = "";
                    while (true) {
                        System.out.print("🔒 Enter 3-digit CVV: ");
                        cvv = sc.nextLine().trim();
                        if (cvv.matches("\\d{3}")) {
                            break;
                        } else {
                            System.out.println("❌ Invalid CVV. Must be exactly 3 digits.");
                        }
                    }
                    gateway = new CreditCard(cardNumber, cvv);
                    break;

                case 2:
                    String upiId = "";
                    while (true) {
                        System.out.print("📲 Enter UPI ID (e.g. name@upi): ");
                        upiId = sc.nextLine().trim();
                        if (
                            upiId.length() >= 5 &&
                            upiId.length() <= 20 &&
                            upiId.matches("^[\\w.-]+@[\\w.-]+$")
                        ) {
                            break;
                        } else {
                            System.out.println("❌ Invalid UPI ID!");
                            System.out.println("👉 UPI ID must:");
                            System.out.println("- Be 5 to 20 characters long");
                            System.out.println("- Contain letters, numbers, dots, hyphens");
                            System.out.println("- Look like: name@bank");
                        }
                    }
                    gateway = new UPI(upiId);
                    break;

                case 3:
                    String[] bankList = {
                        "HDFC Bank",
                        "ICICI Bank",
                        "SBI",
                        "Axis Bank",
                        "Kotak Bank"
                    };

                    String bankName = "";
                    while (true) {
                        System.out.println("🏦 Available Banks:");
                        for (int i = 0; i < bankList.length; i++) {
                            System.out.println((i + 1) + ". " + bankList[i]);
                        }
                        System.out.print("👉 Choose your bank (1-" + bankList.length + "): ");

                        if (sc.hasNextInt()) {
                            int bankChoice = sc.nextInt();
                            sc.nextLine();
                            if (bankChoice >= 1 && bankChoice <= bankList.length) {
                                bankName = bankList[bankChoice - 1];
                                break;
                            } else {
                                System.out.println("❌ Invalid choice. Choose a number from the list.");
                            }
                        } else {
                            System.out.println("❌ Invalid input. Enter a number.");
                            sc.nextLine();
                        }
                    }

                    String userId = "";
                    while (true) {
                        System.out.print("🆔 Enter NetBanking User ID: ");
                        userId = sc.nextLine();
                        if (userId.length() >= 4 && userId.length() <= 12 &&userId.matches("^[\\w.-]+$")) {
                            break;
                        } else {
                            System.out.println("❌ Invalid User ID!");
                            System.out.println("👉 User ID must:");
                            System.out.println("- Be at least 4 characters long and it should be no longer than 12 characters");
                            System.out.println("- Contain letters, numbers, dots, hyphens or underscores");
                            System.out.println("- No spaces allowed");
                        }
                    }
                    gateway = new NetBanking(bankName, userId);
                    break;
            }

            gateway.pay(amount);

            System.out.print("\n❓ Did you make this payment by mistake? (y/n): ");
            String mistaken = sc.nextLine().trim().toLowerCase();
            while (!mistaken.equals("y") && !mistaken.equals("n")) {
                System.out.print("❌ Please enter 'y' or 'n': ");
                mistaken = sc.nextLine().toLowerCase();
            }

            if (mistaken.equals("y")) {
                gateway.refund(amount);
            } else {
                double cashback = amount * 0.05;
                System.out.println("🎁 You earned ₹" + String.format("%.2f", cashback) + " cashback!");
                System.out.println("✅ Thank you for shopping with us!");
            }

            System.out.println("\n🔁 Returning to main menu...");
        }

        sc.close();
    }
}