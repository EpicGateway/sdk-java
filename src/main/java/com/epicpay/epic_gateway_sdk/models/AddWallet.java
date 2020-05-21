package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * AddWallet
 */
public class AddWallet {

    @SerializedName("wallet_id")
    public String walletId;
    @SerializedName("client_customer_id")
    public String clientCustomerId;
    @SerializedName("customer_id")
    public String customerId;
}