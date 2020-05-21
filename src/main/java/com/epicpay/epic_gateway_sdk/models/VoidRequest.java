package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * VoidRequest
 */
public class VoidRequest {
    @SerializedName("transaction_type")
    public final String transactionType = "void";
    @SerializedName("amount")
    public int amount = 0;
    @SerializedName("client_transaction_id")
    public String clientTransactionId;
    //@SerializedName("transaction_id")
    public transient String transactionId;

    public VoidRequest amount(int amount) {
        this.amount = amount;
        return this;
    }

    public VoidRequest clientTransactionId(String clientTransactionId) {
        this.clientTransactionId = clientTransactionId;
        return this;
    }

    public VoidRequest transactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }
}