package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * GetTransactionRequest
 */
public class GetTransactionRequest {

    @SerializedName("id_source")
    public String idSource;
    public transient String id;


    public GetTransactionRequest idSource(String idSource) {
        this.idSource = idSource;
        return this;
    }

    public GetTransactionRequest id(String id) {
        this.id = id;
        return this;
    }
    
}