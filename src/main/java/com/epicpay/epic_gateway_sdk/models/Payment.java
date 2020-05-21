package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
* Payment
*/
public class Payment {
    
    @SerializedName("transaction_type")
    public String transactionType;
    
    @SerializedName("amount")
    public int amount;
    
    @SerializedName("secondary_amount")
    public int secondaryAmount;
    
    @SerializedName("currency")
    public String currency;
    
    @SerializedName("client_transaction_id")
    public String clientTransactionId;
    
    @SerializedName("transaction_id")
    public String transactionId;
    
    @SerializedName("method")
    public String method;
    
    @SerializedName("reference_number")
    public String referenceNumber;
    
    @SerializedName("authorization_number")
    public String authNumber;
    
    @SerializedName("avs_code")
    public String aVSCode;
    
    @SerializedName("cvv_code")
    public String cvvCode;
    
    @SerializedName("credit_card")
    public CreditCard creditCard;
    
    @SerializedName("billing_address")
    public Address billingAddress;
    
    @SerializedName("bank_account")
    public BankAccount bankAccount;
    
    @SerializedName("token")
    public Token token;
    
    @SerializedName("wallet")
    public Wallet wallet;
    
}
