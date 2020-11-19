// public methods for the Epic Gateway SDK
package com.epicpay.epic_gateway_sdk;

import java.util.Hashtable;
import java.util.concurrent.CompletableFuture;

import com.epicpay.epic_gateway_sdk.models.*;
import com.epicpay.epic_gateway_sdk.HttpClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EpicGateway {

    private Hashtable<String, String> config;
    private Gson gson;
    private HttpClient http;

    private static Hashtable<String, String> makeConfig(String apikeyid, String apikeypasswd, String apiurl) {
        Hashtable<String, String> config = new Hashtable<String, String>();
        config.put("apikeyid", apikeyid);
        config.put("apikeypasswd", apikeypasswd);
        config.put("apiurl", apiurl);
        return config;
    }
    public EpicGateway(String apikeyid, String apikeypasswd, String apiurl) {
        this(makeConfig(apikeyid, apikeypasswd, apiurl));
    }

    public EpicGateway(Hashtable<String, String> config) {
        // FIXME: pretty printing for debugging, remove for prod
        gson = new GsonBuilder().setPrettyPrinting().create(); 
        this.config = config;
        this.http = new HttpClient(config.get("apikeyid"), config.get("apikeypasswd"), config.get("apiurl"));
    }
    //#region Public API Methods
    /** 
     * Mark a sale for resolution as soon as possible. See
     * https://developer.epicpay.com/Docs/PaymentAPI#Api_Transaction_SaleAuth
     * for information on which parameters are required.
    */
    public CompletableFuture<PaymentResponse> sale(SaleRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<PaymentResponse> resp = 
            http.postAsyncHttpBasicAuth("authorize", jsonRequest).thenApply(
                (strResp) -> {return gson.fromJson(strResp, PaymentResponse.class);}
            );
        return resp;
    }
    /** 
     * Authorize a transaction for later capture. See
     * https://developer.epicpay.com/Docs/PaymentAPI#Api_Transaction_SaleAuth
     * for information on which parameters are required.
    */
    public CompletableFuture<PaymentResponse> authorize(AuthorizationRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<PaymentResponse> resp = 
            http.postAsyncHttpBasicAuth("authorize", jsonRequest).thenApply(
                (strResp) -> {return gson.fromJson(strResp, PaymentResponse.class);}
            );
        return resp;
    }
    /**
     * Capture an authorized transaction by ID. See
     * https://developer.epicpay.com/Docs/PaymentAPI#Api_Transaction_VCR
     * for information on which parameters are required.
     */
    public CompletableFuture<PaymentResponse> capture(CaptureRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<PaymentResponse> resp = 
            http.postAsyncHttpBasicAuth("authorize/" + req.transactionId, jsonRequest).thenApply(
                (strResp) -> {return gson.fromJson(strResp, PaymentResponse.class);}
            );
        return resp;
    }
    /**
     * Make a Refund (aka Credit) by transaction ID. See
     * https://developer.epicpay.com/Docs/PaymentAPI#Api_Transaction_VCR
     * for information on which parameters are required.
     */
    public CompletableFuture<PaymentResponse> refund(RefundRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<PaymentResponse> resp = 
            http.postAsyncHttpBasicAuth("authorize/" + req.transactionId, jsonRequest).thenApply(
                (strResp) -> {return gson.fromJson(strResp, PaymentResponse.class);}
            );
        return resp;
    }
    /**
     * Void an authorized transaction by ID. See
     * https://developer.epicpay.com/Docs/PaymentAPI#Api_Transaction_VCR
     * for information on which parameters are required.
     */
    public CompletableFuture<PaymentResponse> voidTransaction(VoidRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<PaymentResponse> resp = 
            http.postAsyncHttpBasicAuth("authorize/" + req.transactionId, jsonRequest).thenApply(
                (strResp) -> {return gson.fromJson(strResp, PaymentResponse.class);}
            );
        return resp;
    }
    /**
     * Register a multi-use token for later use in payments. See
     * https://developer.epicpay.com/Docs/PaymentAPI#Api_Tokenization_MultiUse
     * for information on which parameters are required.
     */
    public CompletableFuture<MultiUseTokenResponse> registerToken(MultiUseTokenRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<MultiUseTokenResponse> resp = 
            http.postAsyncHttpBasicAuth("registertoken", jsonRequest).thenApply(
                (strResp) -> {return gson.fromJson(strResp, MultiUseTokenResponse.class);}
            );
        return resp;
    }
    /**
     * Register a Javascript Web Token to send to clients for a one-time token.
     * https://developer.epicpay.com/Docs/PaymentAPI#Api_Tokenization_OneTime
     * for information on which parameters are required.
     */
    public CompletableFuture<JwtTokenResponse> requestJwt(JwtTokenRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<JwtTokenResponse> resp = 
            http.postAsyncHttpBasicAuth("requestjwt", jsonRequest).thenApply(
                (strResp) -> {return gson.fromJson(strResp, JwtTokenResponse.class);}
            );
        return resp;
    }
    /**
     * Add an item to a wallet, or create a wallet and customer if no IDs provided.
     * https://developer.epicpay.com/Docs/PaymentAPI#Api_Wallet_Add
     * for information on which parameters are required.
     */
    public CompletableFuture<WalletResponse> addWalletItem(AddWalletRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<WalletResponse> resp = 
            http.postAsyncHttpBasicAuth("addwalletitem", jsonRequest).thenApply(
                (strResp) -> {return gson.fromJson(strResp, WalletResponse.class);}
            );
        return resp;
    }
    /**
     * Edit an existing item in a wallet. All parameters must be included,
     * even if only some are being changed. See
     * https://developer.epicpay.com/Docs/PaymentAPI#Api_Wallet_Add
     * for information on what can and cannot be changed with this method.
     */
    public CompletableFuture<WalletResponse> editWalletItem(EditWalletRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<WalletResponse> resp = 
            http.postAsyncHttpBasicAuth("editwalletitem/" + req.walletId, jsonRequest).thenApply(
                (strResp) -> {return gson.fromJson(strResp, WalletResponse.class);}
            );
        return resp;
    }
    /**
     * Remove an item from a wallet. See 
     * https://developer.epicpay.com/Docs/PaymentAPI#Api_Wallet_Delete
     * for more detailed information.
     */
    public CompletableFuture<WalletResponse> deleteWalletItem(DeleteWalletRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<WalletResponse> resp = 
            http.postAsyncHttpBasicAuth("deletewalletitem/" + req.walletId, jsonRequest).thenApply(
                (strResp) -> {return gson.fromJson(strResp, WalletResponse.class);}
            );
        return resp;
    }
    /**
     * Edit data regarding a specific customer. All parameters must be included, even
     * if only some are being changed. See 
     * https://developer.epicpay.com/Docs/PaymentAPI#Api_Customer_Edit
     * for more detailed information.
     */
    public CompletableFuture<CustomerResponse> editCustomer(EditCustomerRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<CustomerResponse> resp = 
            http.postAsyncHttpBasicAuth("editcustomer/" + req.customerId, jsonRequest).thenApply(
                (strResp) -> {return gson.fromJson(strResp, CustomerResponse.class);}
            );
        return resp;
    }
    /**
     * Get the Payment object for a transaction that has already happened.
     * You can search for transaction by either client transaction ID or by 
     * the EpicPay-assigned transaction ID. See
     * https://developer.epicpay.com/Docs/PaymentAPI#Api_GetTransaction
     * for usage instructions.
     */
    public CompletableFuture<PaymentResponse> getTransaction(GetTransactionRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<PaymentResponse> resp = 
            http.postAsyncHttpBasicAuth("gettransaction/" + req.id, jsonRequest).thenApply(
                (strResp) -> {return gson.fromJson(strResp, PaymentResponse.class);}
            );
        return resp;
    }
    /**
     * Create a new subscription and attach a payment method. See
     * https://developer.epicpay.com/Docs/PaymentAPI#Api_Subscription_Add
     * for a complete list of parameters.
     */
    public CompletableFuture<SubscriptionResponse> addSubscription(AddSubscriptionRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<SubscriptionResponse> resp = 
            http.postAsyncHttpBasicAuth("addsubscription", jsonRequest).thenApply(
                (strResp) -> {return gson.fromJson(strResp, SubscriptionResponse.class);}
            );
        return resp;
    }
    /**
     * Edit any parameters of a subscription, including the payment method.
     * All parameters must be included, even if only some have changed. See
     * https://developer.epicpay.com/Docs/PaymentAPI#Api_Subscription_Edit
     * for a complete list of parameters.
     */
    public CompletableFuture<SubscriptionResponse> editSubscription(EditSubscriptionRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<SubscriptionResponse> resp = 
            http.postAsyncHttpBasicAuth("editsubscription/" + req.subscriptionId, jsonRequest).thenApply(
                (strResp) -> {return gson.fromJson(strResp, SubscriptionResponse.class);}
            );
        return resp;
    }
    /**
     * 
     */
    public CompletableFuture<SubscriptionResponse> deleteSubscription(DeleteSubscriptionRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<SubscriptionResponse> resp = 
            http.postAsyncHttpBasicAuth("deletesubscription/" + req.subscriptionId, jsonRequest).thenApply(
                (strResp) -> {return gson.fromJson(strResp, SubscriptionResponse.class);}
            );
        return resp;
    }
    public CompletableFuture<SuspendSubscriptionResponse> suspendSubscription(SuspendSubscriptionRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<SuspendSubscriptionResponse> resp = 
            http.postAsyncHttpBasicAuth("subscription/suspend/" + req.subscriptionId, jsonRequest).thenApply(
                (strResp) -> {return gson.fromJson(strResp, SuspendSubscriptionResponse.class);}
            );
        return resp;
    }
    public CompletableFuture<SuspendSubscriptionResponse> unsuspendSubscription(UnsuspendSubscriptionRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<SuspendSubscriptionResponse> resp = 
            http.postAsyncHttpBasicAuth("subscription/unsuspend/" + req.subscriptionId, jsonRequest).thenApply(
                (strResp) -> {return gson.fromJson(strResp, SuspendSubscriptionResponse.class);}
            );
        return resp;
    }
    //#endregion
    // This method is useful for unit testing, but should not be accessible to the user
    // as this API endpoint should only be called from clientside code.
    protected CompletableFuture<TokenResponse> registerOneTimeToken(String jwt, OneTimeTokenRequest req) {
        String jsonRequest = gson.toJson(req);
        CompletableFuture<TokenResponse> resp = 
            http.postAsyncHttpBearerAuth("registeronetimetoken", jsonRequest, jwt).thenApply(
                (strResp) -> {return gson.fromJson(strResp, TokenResponse.class);}
            );
        return resp;
    }
}
