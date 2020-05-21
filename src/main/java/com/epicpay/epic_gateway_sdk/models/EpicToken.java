package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

public class EpicToken {
    @SerializedName("token_type")
    public String tokenType;
    @SerializedName("token_data")
    public String tokenData;
    @SerializedName("account_holder_name")
    public String accountHolderName;
    @SerializedName("exp_month")
    public String expMonth;
    @SerializedName("exp_year")
    public String expYear;
    @SerializedName("cvv")
    public String cvv;

    public EpicToken tokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public EpicToken tokenData(String tokenData) {
        this.tokenData = tokenData;
        return this;
    }

    public EpicToken accountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        return this;
    }

    public EpicToken expMonth(String expMonth) {
        this.expMonth = expMonth;
        return this;
    }

    public EpicToken expYear(String expYear) {
        this.expYear = expYear;
        return this;
    }

    public EpicToken cvv(String cvv) {
        this.cvv = cvv;
        return this;
    }

}
