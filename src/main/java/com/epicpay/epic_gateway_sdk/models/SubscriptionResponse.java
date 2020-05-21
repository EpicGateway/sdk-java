package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * SubscriptionResponse
 */
public class SubscriptionResponse {

    @SerializedName("status")
    public Status status;
    @SerializedName("result")
    public SubscriptionResult result;
}