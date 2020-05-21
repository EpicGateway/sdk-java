package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

public class Wallet {
    @SerializedName("wallet_id")
    public String walletId;

    @SerializedName("cvv")
    public String cvv;

    @SerializedName("client_customer_id")
    public String clientCustomerId;

    @SerializedName("customer_id")
    public int customerId;

    public Wallet walletId(String walletID) {
        this.walletId = walletID;
        return this;
    }

    public Wallet cvv(String cvv) {
        this.cvv = cvv;
        return this;
    }

    public Wallet clientCustomerId(String clientCustomerId) {
        this.clientCustomerId = clientCustomerId;
        return this;
    }

    public Wallet customerId(int customerId) {
        this.customerId = customerId;
        return this;
    }

}