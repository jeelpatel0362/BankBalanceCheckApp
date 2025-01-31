package com.example.bankbalancecheckapp;

import java.io.Serializable;
import java.util.ArrayList;

public class UserData implements Serializable {
    private String name;
    private String accountNumber;
    private double balance;
    private ArrayList<String> transactions;

    private String mobileNumber;

    public UserData(String name, String accountNumber, double balance, String mobileNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactions = new ArrayList<>();
        this.mobileNumber = mobileNumber;
    }




    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }



    public ArrayList<String> getTransaction() {
        return transactions;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void addTransaction(String transaction) {
        transactions.add(transaction);
    }

}
