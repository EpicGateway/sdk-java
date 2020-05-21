package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Token
 */
public class Token {
    @SerializedName("token_type")
    public String tokenType;

    @SerializedName("token_number")
    public String tokenNumber;

    @SerializedName("account_holder_name")
    public String accountHolderName;

    public Token tokenType(String TokenType) {
        this.tokenType = TokenType;
        return this;
    }

    public Token tokenNumber(String TokenNumber) {
        this.tokenNumber = TokenNumber;
        return this;
    }

    public Token accountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        return this;
    }

}