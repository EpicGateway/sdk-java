package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * BankAccount
 */
public class BankAccount {
    @SerializedName("account_type")
    public String accountType;
    @SerializedName("routing_number")
    public String routingNumber;
    @SerializedName("account_number")
    public String accountNumber;
    @SerializedName("account_holder_name")
    public String accountHolderName;

    public BankAccount accountType(String account_type) {
        this.accountType = account_type;
        return this;
    }

    public BankAccount routingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
        return this;
    }

    public BankAccount accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public BankAccount accountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        return this;
    }

    
}