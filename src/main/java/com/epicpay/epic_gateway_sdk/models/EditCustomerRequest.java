package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * EditCustomerRequest
 */
public class EditCustomerRequest {
    public transient String customerId;
    @SerializedName("customer_address")
    public Address customerAddress;
    @SerializedName("client_customer_id")
    public String clientCustomerId;

    public EditCustomerRequest customerAddress(Address customerAddress) {
        this.customerAddress = customerAddress;
        return this;
    }

    public EditCustomerRequest clientCustomerId(String clientCustomerId) {
        this.clientCustomerId = clientCustomerId;
        return this;
    }
    public EditCustomerRequest customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }


}