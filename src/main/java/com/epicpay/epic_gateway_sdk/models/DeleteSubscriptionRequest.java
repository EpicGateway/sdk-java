package com.epicpay.epic_gateway_sdk.models;

/**
 * DeleteSubscriptionRequest
 */
public class DeleteSubscriptionRequest {

    public transient String subscriptionId;
    
    public DeleteSubscriptionRequest subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }
}