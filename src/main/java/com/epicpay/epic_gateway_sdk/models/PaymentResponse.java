package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Response
 */
public class PaymentResponse {
    @SerializedName("status")
    public Status status;
    @SerializedName("result")
    public AuthResult result;
}
