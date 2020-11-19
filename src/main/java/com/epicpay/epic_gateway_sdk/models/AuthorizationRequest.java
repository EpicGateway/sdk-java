package com.epicpay.epic_gateway_sdk.models;

import com.google.gson.annotations.SerializedName;

/**
 * AuthorizationRequest
 */
public class AuthorizationRequest {

    @SerializedName("transaction_type")
    public final String transactionType = "authorize";

    @SerializedName("method")
    public String method;

    @SerializedName("amount")
    public int amount = 0;

    @SerializedName("secondary_amount")
    public int secondaryAmount = 0;

    @SerializedName("currency")
    public String currency = "usd";

    @SerializedName("client_transaction_id")
    public String clientTransactionId = null;

    @SerializedName("client_order_id")
    public String clientOrderId = null;

    @SerializedName("client_customer_id")
    public String clientCustomerId = null;

    @SerializedName("credit_card")
    public CreditCard creditCard;

    @SerializedName("bank_account")
    public BankAccount bankAccount;

    @SerializedName("token")
    public CardToken token;

    @SerializedName("wallet")
    public Wallet wallet;

    @SerializedName("billing_address")
    public Address billingAddress;

    @SerializedName("sec_code")
    public String secCode;

    @SerializedName("entry_description")
    public String entryDescription;

    public AuthorizationRequest entryDescription(String entryDescription) {
        this.entryDescription = entryDescription;
        return this;
    }

    public AuthorizationRequest secCode(String secCode) {
        this.secCode = secCode;
        return this;
    }

    public AuthorizationRequest method(String method) {
        this.method = method;
        return this;
    }

    public AuthorizationRequest amount(int amount) {
        this.amount = amount;
        return this;
    }

    public AuthorizationRequest secondaryAmount(int secondaryAmount) {
        this.secondaryAmount = secondaryAmount;
        return this;
    }

    public AuthorizationRequest currency(String currency) {
        this.currency = currency;
        return this;
    }

    public AuthorizationRequest clientTransactionId(String clientTransactionID) {
        this.clientTransactionId = clientTransactionID;
        return this;
    }

    public AuthorizationRequest clientOrderId(String ClientOrderID) {
        this.clientOrderId = ClientOrderID;
        return this;
    }

    public AuthorizationRequest clientCustomerId(String ClientCustomerID) {
        this.clientCustomerId = ClientCustomerID;
        return this;
    }

    public AuthorizationRequest creditCard(CreditCard CreditCard) {
        this.creditCard = CreditCard;
        return this;
    }

    public AuthorizationRequest bankAccount(BankAccount BankAccount) {
        this.bankAccount = BankAccount;
        return this;
    }

    public AuthorizationRequest token(CardToken Token) {
        this.token = Token;
        return this;
    }

    public AuthorizationRequest wallet(Wallet Wallet) {
        this.wallet = Wallet;
        return this;
    }

    public AuthorizationRequest billingAddress(Address BillingAddress) {
        this.billingAddress = BillingAddress;
        return this;
    }

}