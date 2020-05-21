package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * CreditCard
 */
public class CreditCard {
    // Keyed card info: mutually exlusive with swiped card info
    @SerializedName("card_number")
    public String cardNumber;
    @SerializedName("card_holder_name")
    public String cardHolderName;
    @SerializedName("exp_month")
    public String expMonth;
    @SerializedName("exp_year")
    public String expYear;
    @SerializedName("cvv")
    public String cvv;

    // Swiped card info: mutually exclusive with Keyed card info
    @SerializedName("card_track")
    public String cardTrack;

    // Generally only in returned result data
    @SerializedName("card_type")
    public String cardType;
    
 

    public CreditCard cardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
        return this;
    }

    public CreditCard cardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
        return this;
    }

    public CreditCard expMonth(String expMonth) {
        this.expMonth = expMonth;
        return this;
    }

    public CreditCard expYear(String expYear) {
        this.expYear = expYear;
        return this;
    }

    public CreditCard cardTrack(String cardTrack) {
        this.cardTrack = cardTrack;
        return this;
    }

    public CreditCard cvv(String cvv) {
        this.cvv = cvv;
        return this;
    }
/*
    public CreditCard cardType(String cardType) {
        this.cardType = cardType;
        return this;
    }    */
}