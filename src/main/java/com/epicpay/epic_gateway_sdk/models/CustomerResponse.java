package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * CustomerResponse
 */
public class CustomerResponse {

    @SerializedName("status")
    public Status status;
    @SerializedName("result")
    public CustomerResult result;
}