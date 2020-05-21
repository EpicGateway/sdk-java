package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * WalletResponse
 */
public class WalletResponse {

    @SerializedName("status")
    public Status status;
    @SerializedName("result")
    public WalletResult result;
}