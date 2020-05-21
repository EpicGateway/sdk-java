package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

public class CardSaleRequest extends SaleRequest {
    @SerializedName("credit_card")
    public CreditCard creditCard;
    @SerializedName("token")
    public CardToken token;

    public CardSaleRequest() {
        method = "credit_card";
    }
    
    public CardSaleRequest creditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
        return this;
    }

    public CardSaleRequest token(CardToken token) {
        this.token = token;
        return this;
    }
    
    // PASTED FROM PARENT
/*
    public CardSaleRequest method(String method) {
        this.method = method;
        return this;
    }
*/
    public CardSaleRequest amount(int amount) {
        this.amount = amount;
        return this;
    }

    public CardSaleRequest secondaryAmount(int secondaryAmount) {
        this.secondaryAmount = secondaryAmount;
        return this;
    }

    public CardSaleRequest currency(String currency) {
        this.currency = currency;
        return this;
    }

    public CardSaleRequest clientTransactionId(String clientTransactionId) {
        this.clientTransactionId = clientTransactionId;
        return this;
    }

    public CardSaleRequest clientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
        return this;
    }

    public CardSaleRequest clientCustomerId(String clientCustomerId) {
        this.clientCustomerId = clientCustomerId;
        return this;
    }

    public CardSaleRequest wallet(Wallet wallet) {
        this.wallet = wallet;
        return this;
    }

    public CardSaleRequest billingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    public CardSaleRequest method(String method) {
        this.method = method;
        return this;
    }
}