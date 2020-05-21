package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * RefundRequest
 */
public class RefundRequest {
    @SerializedName("transaction_type")
    public final String transactionType = "credit";
    @SerializedName("amount")
    public int amount = 0;
    @SerializedName("client_transaction_id")
    public String clientTransactionId;
    @SerializedName("transaction_id")
    public String transactionId;

    public RefundRequest amount(int amount) {
        this.amount = amount;
        return this;
    }

    public RefundRequest clientTransactionId(String clientTransactionId) {
        this.clientTransactionId = clientTransactionId;
        return this;
    }

    public RefundRequest transactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }
    
    
}