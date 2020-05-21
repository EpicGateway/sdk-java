package com.epicpay.epic_gateway_sdk.models;

/**
 * SuspendSubscriptionRequest
 */
public class SuspendSubscriptionRequest {

    public transient String subscriptionId;
    public SuspendSubscriptionRequest subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }
}