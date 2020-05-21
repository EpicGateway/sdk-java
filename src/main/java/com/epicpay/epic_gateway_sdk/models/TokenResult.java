package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * TokenResult
 */
public class TokenResult {

    @SerializedName("token")
    public OneTimeToken token;

    public TokenResult token(OneTimeToken token) {
        this.token = token;
        return this;
    }

}