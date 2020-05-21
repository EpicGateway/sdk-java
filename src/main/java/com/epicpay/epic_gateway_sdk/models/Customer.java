package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Customer
 */
public class Customer {
    @SerializedName("customer_id")
    public String customerId;
    @SerializedName("client_transaction_id")
    public String clientTransactionId;
    @SerializedName("client_customer_id")
    public String clientCustomerId;
    @SerializedName("customer_address")
    public Address customerAddress;

    public Customer customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public Customer clientTransactionId(String clientTransactionId) {
        this.clientTransactionId = clientTransactionId;
        return this;
    }

    public Customer clientCustomerId(String clientCustomerId) {
        this.clientCustomerId = clientCustomerId;
        return this;
    }

    public Customer customerAddress(Address customerAddress) {
        this.customerAddress = customerAddress;
        return this;
    }

}