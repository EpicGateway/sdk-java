package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

public class Status {
    @SerializedName("response_code")
    public String ResponseCode;
    @SerializedName("reason_code")
    public String ReasonCode;
    @SerializedName("reason_text")
    public String ReasonText;
}