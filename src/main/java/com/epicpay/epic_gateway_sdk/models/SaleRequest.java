package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

public class SaleRequest {
    // Required Information
    @SerializedName("amount")
    public int amount;
    @SerializedName("currency")
    public String currency;
    @SerializedName("method")
    public String method;
    @SerializedName("transaction_type")
    public final String transactionType = "sale";
    // FIXME: Does this belong here?
    @SerializedName("secondary_amount")
    public int secondaryAmount;
    // Conditionally Required based on method
    @SerializedName("sec_code")
    public String secCode;
    @SerializedName("bank_account")
    public BankAccount bankAccount;
    @SerializedName("credit_card")
    public CreditCard creditCard;
    @SerializedName("wallet")
    public Wallet wallet;
    @SerializedName("token")
    public Token token;
    @SerializedName("epic_token")
    public EpicToken epicToken;
    @SerializedName("billing_address")
    public Address billingAddress;
    
    // Optional
    @SerializedName("client_transaction_id")
    public String clientTransactionId;
    @SerializedName("client_order_id")
    public String clientOrderId;
    @SerializedName("client_customer_id")
    public String clientCustomerId;
    @SerializedName("entry_description")
    public String entryDescription;

    public SaleRequest method(String method) {
        this.method = method;
        return this;
    }

    public SaleRequest amount(int amount) {
        this.amount = amount;
        return this;
    }

    public SaleRequest secondaryAmount(int secondaryAmount) {
        this.secondaryAmount = secondaryAmount;
        return this;
    }

    public SaleRequest currency(String currency) {
        this.currency = currency;
        return this;
    }

    public SaleRequest clientTransactionId(String clientTransactionId) {
        this.clientTransactionId = clientTransactionId;
        return this;
    }

    public SaleRequest clientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
        return this;
    }

    public SaleRequest clientCustomerId(String clientCustomerId) {
        this.clientCustomerId = clientCustomerId;
        return this;
    }

    public SaleRequest wallet(Wallet wallet) {
        this.wallet = wallet;
        return this;
    }

    public SaleRequest billingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    public SaleRequest bankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public SaleRequest token(Token token) {
        this.token = token;
        return this;
    }

    public SaleRequest secCode(String secCode) {
        this.secCode = secCode;
        return this;
    }

    public SaleRequest creditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
        return this;
    }

}