package com.epicpay.epic_gateway_sdk;
/* data structures for the Epic Gateway SDK */

public class Models {
    public class Address {} // In the current C# SDK HomeAddress and BillingAddress have the same attributes.
    public class Phone {}
    public class CreditCard {}
    public class BankAccount {}
    public class Token {}
    public class CardToken extends Token {} // todo: read inheritance of source
    public class Wallet {}
}