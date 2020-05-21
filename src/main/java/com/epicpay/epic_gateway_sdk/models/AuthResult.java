package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

public class AuthResult {
    @SerializedName("payment")
    public Payment payment;
}