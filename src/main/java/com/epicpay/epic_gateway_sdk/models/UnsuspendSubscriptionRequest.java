package com.epicpay.epic_gateway_sdk.models;

/**
 * UnsuspendSubscriptionRequest
 */
public class UnsuspendSubscriptionRequest {

    public transient String subscriptionId;
    public UnsuspendSubscriptionRequest subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }
}