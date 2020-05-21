package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * OneTimeToken
 */
public class OneTimeToken {

    @SerializedName("token_type")
    public String tokenType;
    @SerializedName("token_number")
    public String tokenNumber;
}