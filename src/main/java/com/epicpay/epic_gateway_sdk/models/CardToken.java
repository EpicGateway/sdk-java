package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * CardToken
 */
public class CardToken extends Token {
    // Optional: require for credit card token
    @SerializedName("exp_month")
    public String expMonth;

    // Optional: require for credit card token
    @SerializedName("exp_year")
    public String expYear;

    @SerializedName("cvv")
    public String cvv;

    public CardToken expMonth(String ExpMonth) {
        this.expMonth = ExpMonth;
        return this;
    }

    public CardToken expYear(String ExpYear) {
        this.expYear = ExpYear;
        return this;
    }

    public CardToken cvv(String CVV) {
        this.cvv = CVV;
        return this;
    }

    // COPIED FROM PARENT
    public CardToken tokenType(String TokenType) {
        this.tokenType = TokenType;
        return this;
    }

    public CardToken tokenNumber(String TokenNumber) {
        this.tokenNumber = TokenNumber;
        return this;
    }

    public CardToken accountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        return this;
    }

}