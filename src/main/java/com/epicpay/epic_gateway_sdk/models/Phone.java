package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Phone
 */
public class Phone {

    @SerializedName("number")
    public String number;
    @SerializedName("type")
    public String type;

    public Phone number(String number) {
        this.number = number;
        return this;
    }

    public Phone type(String type) {
        this.type = type;
        return this;
    }
}