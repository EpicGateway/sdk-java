package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * JwtTokenResponse
 */
public class JwtTokenResponse {
    @SerializedName("status")
    public Status status;
    @SerializedName("result")
    public JwtTokenResult result;
}