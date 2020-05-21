package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * Subscription
 */
public class Subscription {

    @SerializedName("subscription_id")
    public String subscriptionId;
    @SerializedName("customer_id")
    public String customerId;
    @SerializedName("wallet_id")
    public String walletId;
    @SerializedName("amount")
    public int amount;
    @SerializedName("currency")
    public String currency;
    @SerializedName("next_payment_date")
    public String nextPaymentDate;
    @SerializedName("frequency")
    public String frequency;
    @SerializedName("period")
    public int period;
    @SerializedName("date_of_month_1")
    public int firstPaymentDayOfMonth;
    @SerializedName("date_of_month_2")
    public int secondPaymentDayOfMonth;
    @SerializedName("total_payments")
    public int totalPayments;
    @SerializedName("last_payment_date")
    public String lastPaymentDate;
    @SerializedName("last_amount")
    public int lastAmount;
    @SerializedName("client_order_id")
    public String clientOrderID;
    @SerializedName("client_customer_id")
    public String clientCustomerID;
    @SerializedName("alert_after")
    public boolean alert;
    @SerializedName("sec_code")
    public String entryClass;
    @SerializedName("entry_description")
    public String entryDescription;

    public Subscription subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public Subscription customerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public Subscription walletId(String walletId) {
        this.walletId = walletId;
        return this;
    }

    public Subscription amount(int amount) {
        this.amount = amount;
        return this;
    }

    public Subscription currency(String currency) {
        this.currency = currency;
        return this;
    }

    public Subscription nextPaymentDate(String nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
        return this;
    }

    public Subscription frequency(String frequency) {
        this.frequency = frequency;
        return this;
    }

    public Subscription period(int period) {
        this.period = period;
        return this;
    }

    public Subscription firstPaymentDayOfMonth(int firstPaymentDayOfMonth) {
        this.firstPaymentDayOfMonth = firstPaymentDayOfMonth;
        return this;
    }

    public Subscription secondPaymentDayOfMonth(int secondPaymentDayOfMonth) {
        this.secondPaymentDayOfMonth = secondPaymentDayOfMonth;
        return this;
    }

    public Subscription totalPayments(int totalPayments) {
        this.totalPayments = totalPayments;
        return this;
    }

    public Subscription lastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
        return this;
    }

    public Subscription lastAmount(int lastAmount) {
        this.lastAmount = lastAmount;
        return this;
    }

    public Subscription clientOrderID(String clientOrderID) {
        this.clientOrderID = clientOrderID;
        return this;
    }

    public Subscription clientCustomerID(String clientCustomerID) {
        this.clientCustomerID = clientCustomerID;
        return this;
    }

    public Subscription alert(boolean alert) {
        this.alert = alert;
        return this;
    }

    public Subscription entryClass(String entryClass) {
        this.entryClass = entryClass;
        return this;
    }

    public Subscription entryDescription(String entryDescription) {
        this.entryDescription = entryDescription;
        return this;
    }

}