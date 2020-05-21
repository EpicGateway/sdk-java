package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * MultiUseTokenResponse
 */
public class MultiUseTokenResponse {

    @SerializedName("status")
    public Status status;
    @SerializedName("result")
    public TokenResult result;
    /*@SerializedName("token")
    public OneTimeToken token;*/
}