package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * JwtTokenRequest
 */
public class JwtTokenRequest {

    @SerializedName("ip")
    public String ip;

    public JwtTokenRequest ip(String ip) {
        this.ip = ip;
        return this;
    }

}