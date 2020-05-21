package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * EditSubscriptionRequest
 */
public class EditSubscriptionRequest {
    // Required
    public transient String subscriptionId;

    @SerializedName("method")
    public String method;
    @SerializedName("amount")
    public int amount;
    @SerializedName("alert_after")
    public boolean alert;
    @SerializedName("secondary_amount")
    public int secondaryAmount;
    @SerializedName("customer_address")
    public Address customerAddress;
    @SerializedName("billing_address")
    public Address billingAddress;
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
    @SerializedName("last_secondary_amount")
    public int lastSecondaryAmount;
    @SerializedName("client_order_id")
    public String clientOrderID;
    @SerializedName("client_customer_id")
    public String clientCustomerID;
    @SerializedName("alert_days_before")
    public int alertDaysBefore;
    @SerializedName("sec_code")
    public String secCode;
    @SerializedName("entry_description")
    public String entryDescription;
    @SerializedName("credit_card")
    public CreditCard creditCard;
    @SerializedName("bank_account")
    public BankAccount bankAccount;
    @SerializedName("token")
    public CardToken token;
    @SerializedName("wallet")
    public Wallet wallet;

    public EditSubscriptionRequest subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

    public EditSubscriptionRequest method(String method) {
        this.method = method;
        return this;
    }

    public EditSubscriptionRequest amount(int amount) {
        this.amount = amount;
        return this;
    }

    public EditSubscriptionRequest secondaryAmount(int secondaryAmount) {
        this.secondaryAmount = secondaryAmount;
        return this;
    }

    public EditSubscriptionRequest customerAddress(Address customerAddress) {
        this.customerAddress = customerAddress;
        return this;
    }

    public EditSubscriptionRequest billingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
        return this;
    }

    public EditSubscriptionRequest currency(String currency) {
        this.currency = currency;
        return this;
    }

    public EditSubscriptionRequest nextPaymentDate(String nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
        return this;
    }

    public EditSubscriptionRequest frequency(String frequency) {
        this.frequency = frequency;
        return this;
    }

    public EditSubscriptionRequest period(int period) {
        this.period = period;
        return this;
    }

    public EditSubscriptionRequest firstPaymentDayOfMonth(int firstPaymentDayOfMonth) {
        this.firstPaymentDayOfMonth = firstPaymentDayOfMonth;
        return this;
    }

    public EditSubscriptionRequest secondPaymentDayOfMonth(int secondPaymentDayOfMonth) {
        this.secondPaymentDayOfMonth = secondPaymentDayOfMonth;
        return this;
    }

    public EditSubscriptionRequest totalPayments(int totalPayments) {
        this.totalPayments = totalPayments;
        return this;
    }

    public EditSubscriptionRequest lastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
        return this;
    }

    public EditSubscriptionRequest lastAmount(int lastAmount) {
        this.lastAmount = lastAmount;
        return this;
    }

    public EditSubscriptionRequest lastSecondaryAmount(int lastSecondaryAmount) {
        this.lastSecondaryAmount = lastSecondaryAmount;
        return this;
    }

    public EditSubscriptionRequest clientOrderID(String clientOrderID) {
        this.clientOrderID = clientOrderID;
        return this;
    }

    public EditSubscriptionRequest clientCustomerID(String clientCustomerID) {
        this.clientCustomerID = clientCustomerID;
        return this;
    }

    public EditSubscriptionRequest alert(boolean alert) {
        this.alert = alert;
        return this;
    }

    public EditSubscriptionRequest alertDaysBefore(int alertDaysBefore) {
        this.alertDaysBefore = alertDaysBefore;
        return this;
    }

    public EditSubscriptionRequest secCode(String entryClass) {
        this.secCode = entryClass;
        return this;
    }

    public EditSubscriptionRequest entryDescription(String entryDescription) {
        this.entryDescription = entryDescription;
        return this;
    }

    public EditSubscriptionRequest creditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
        return this;
    }

    public EditSubscriptionRequest bankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public EditSubscriptionRequest token(CardToken token) {
        this.token = token;
        return this;
    }

    public EditSubscriptionRequest wallet(Wallet wallet) {
        this.wallet = wallet;
        return this;
    }

}