package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * EditWalletRequest
 */
public class EditWalletRequest {
    // Required
    public transient String walletId;
    @SerializedName("account_holder_name")
    public String accountHolderName;
    // Conditional
    @SerializedName("exp_year")
    public String expYear;
    @SerializedName("exp_month")
    public String expMonth;
    // Optional
    @SerializedName("billing_address")
    public Address billingAddress;

    public EditWalletRequest accountHolderName(String cardHolderName) {
        this.accountHolderName = cardHolderName;
        return this;
    }

    public EditWalletRequest billingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    public EditWalletRequest expMonth(String expMonth) {
        this.expMonth = expMonth;
        return this;
    }

    public EditWalletRequest expYear(String expYear) {
        this.expYear = expYear;
        return this;
    }
    public EditWalletRequest walletId(String walletId) {
        this.walletId = walletId;
        return this;
    }

}