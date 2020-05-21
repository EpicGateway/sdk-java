package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * TokenResponse
 */
public class TokenResponse {
    @SerializedName("status")
    public Status status;
    @SerializedName("result")
    public TokenResult result;
}