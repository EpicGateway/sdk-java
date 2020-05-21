package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * OneTimeTokenRequest
 */
public class OneTimeTokenRequest {
    @SerializedName("method")
    public String method;
    @SerializedName("account_number")
    public String accountNumber;
    @SerializedName("bank_account_type")
    public String bankAccountType;
    @SerializedName("routing_number")
    public String routingNumber;

    public OneTimeTokenRequest method(String method) {
        this.method = method;
        return this;
    }

    public OneTimeTokenRequest accountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public OneTimeTokenRequest bankAccountType(String bankAccountType) {
        this.bankAccountType = bankAccountType;
        return this;
    }

    public OneTimeTokenRequest routingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
        return this;
    }

}