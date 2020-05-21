package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * SuspendSubscriptionResponse
 */
public class SuspendSubscriptionResponse {

    @SerializedName("result")
    public SuspendSubscriptionResult result;
    @SerializedName("status")
    public Status status;
    
}