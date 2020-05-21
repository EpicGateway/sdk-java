package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * AddSubscriptionRequest
 */
public class AddSubscriptionRequest {
    // Required
    @SerializedName("amount")
    public int amount;
    @SerializedName("currency")
    public String currency;
    @SerializedName("method")
    public String method;
    @SerializedName("next_payment_date")
    public String nextPaymentDate;
    @SerializedName("frequency")
    public String frequency;
    // Conditional
    @SerializedName("period")
    public int period;
    @SerializedName("date_of_month_1")
    public int firstPaymentDayOfMonth;
    @SerializedName("date_of_month_2")
    public int secondPaymentDayOfMonth;
    @SerializedName("credit_card")
    public CreditCard creditCard;
    @SerializedName("bank_account")
    public BankAccount bankAccount;
    @SerializedName("token")
    public CardToken token;
    @SerializedName("wallet")
    public Wallet wallet;
    @SerializedName("customer_address")
    public Address customerAddress;
    @SerializedName("sec_code")
    public String secCode;
    // Optional
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
    @SerializedName("alert_days_before")
    public int alertDaysBefore;
    @SerializedName("entry_description")
    public String entryDescription;
    @SerializedName("billing_address")
    public Address billingAddress;

    public AddSubscriptionRequest billingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    public AddSubscriptionRequest customerAddress(Address customerAddress) {
        this.customerAddress = customerAddress;
        return this;
    }

    public AddSubscriptionRequest method(String method) {
        this.method = method;
        return this;
    }

    public AddSubscriptionRequest amount(int amount) {
        this.amount = amount;
        return this;
    }

    public AddSubscriptionRequest currency(String currency) {
        this.currency = currency;
        return this;
    }

    public AddSubscriptionRequest nextPaymentDate(String nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
        return this;
    }

    public AddSubscriptionRequest frequency(String frequency) {
        this.frequency = frequency;
        return this;
    }

    public AddSubscriptionRequest period(int period) {
        this.period = period;
        return this;
    }

    public AddSubscriptionRequest firstPaymentDayOfMonth(int firstPaymentDayOfMonth) {
        this.firstPaymentDayOfMonth = firstPaymentDayOfMonth;
        return this;
    }

    public AddSubscriptionRequest secondPaymentDayOfMonth(int secondPaymentDayOfMonth) {
        this.secondPaymentDayOfMonth = secondPaymentDayOfMonth;
        return this;
    }

    public AddSubscriptionRequest totalPayments(int totalPayments) {
        this.totalPayments = totalPayments;
        return this;
    }

    public AddSubscriptionRequest lastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
        return this;
    }

    public AddSubscriptionRequest lastAmount(int lastAmount) {
        this.lastAmount = lastAmount;
        return this;
    }

    public AddSubscriptionRequest clientOrderID(String clientOrderID) {
        this.clientOrderID = clientOrderID;
        return this;
    }

    public AddSubscriptionRequest clientCustomerID(String clientCustomerID) {
        this.clientCustomerID = clientCustomerID;
        return this;
    }

    public AddSubscriptionRequest alert(boolean alert) {
        this.alert = alert;
        return this;
    }

    public AddSubscriptionRequest alertDaysBefore(int alertDaysBefore) {
        this.alertDaysBefore = alertDaysBefore;
        return this;
    }

    public AddSubscriptionRequest secCode(String entryClass) {
        this.secCode = entryClass;
        return this;
    }

    public AddSubscriptionRequest entryDescription(String entryDescription) {
        this.entryDescription = entryDescription;
        return this;
    }

    public AddSubscriptionRequest creditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
        return this;
    }

    public AddSubscriptionRequest bankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public AddSubscriptionRequest token(CardToken token) {
        this.token = token;
        return this;
    }

    public AddSubscriptionRequest wallet(Wallet wallet) {
        this.wallet = wallet;
        return this;
    }
}