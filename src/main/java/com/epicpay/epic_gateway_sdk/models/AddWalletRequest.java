package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * AddWalletRequest
 */
public class AddWalletRequest {
    // Required
    @SerializedName("method")
    public String method;
    // Conditionally Required
    @SerializedName("customer_id")
    public String customerId;
    @SerializedName("customer_address")
    public Address customerAddress;
    @SerializedName("credit_card")
    public CreditCard creditCard;
    @SerializedName("bank_account")
    public BankAccount bankAccount;
    @SerializedName("token")
    public CardToken token;
    // Optional
    @SerializedName("client_customer_id")
    public String clientCustomerId;
    @SerializedName("billing_address")
    public Address billingAddress;

    public AddWalletRequest method(String method) {
        this.method = method;
        return this;
    }

    public AddWalletRequest creditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
        return this;
    }

    public AddWalletRequest customerAddress(Address customerAddress) {
        this.customerAddress = customerAddress;
        return this;
    }

    public AddWalletRequest clientCustomerId(String clientCustomerId) {
        this.clientCustomerId = clientCustomerId;
        return this;
    }

    public AddWalletRequest customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public AddWalletRequest bankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public AddWalletRequest token(CardToken token) {
        this.token = token;
        return this;
    }

    public AddWalletRequest billingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

}