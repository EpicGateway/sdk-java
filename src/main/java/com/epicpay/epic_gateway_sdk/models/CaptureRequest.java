package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * CaptureRequest
 */
public class CaptureRequest {
    
    @SerializedName("transaction_type")
    public final String TransactionType = "capture";

    @SerializedName("amount")
    public int amount = 0;

    //Not serialized: used in URL
    public transient String transactionId;

    //[DataMember(Name = "secondary_amount", IsRequired = false)]
    //[DefaultValue(0)]
    //public int SecondaryAmount;

    @SerializedName("currency")
    public String currency = "usd";

    @SerializedName("client_transaction_id")
    public String clientTransactionId = null;


    public CaptureRequest transactionId(String TransactionID) {
        this.transactionId = TransactionID;
        return this;
    }

    public CaptureRequest amount(int amount) {
        this.amount = amount;
        return this;
    }

    public CaptureRequest currency(String currency) {
        this.currency = currency;
        return this;
    }

    public CaptureRequest clientTransactionId(String clientTransactionID) {
        this.clientTransactionId = clientTransactionID;
        return this;
    }

}

