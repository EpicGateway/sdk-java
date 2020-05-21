package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * MultiUseTokenRequest
 */
public class MultiUseTokenRequest {

    @SerializedName("method")
    public String method;
    @SerializedName("credit_card")
    public CreditCard creditCard;
    @SerializedName("bank_account")
    public BankAccount bankAccount;
    @SerializedName("token")
    public Token token;

    public MultiUseTokenRequest method(String method) {
        this.method = method;
        return this;
    }

    public MultiUseTokenRequest creditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
        return this;
    }

    public MultiUseTokenRequest bankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public MultiUseTokenRequest token(Token token) {
        this.token = token;
        return this;
    }

}