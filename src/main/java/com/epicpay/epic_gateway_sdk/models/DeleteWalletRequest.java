package com.epicpay.epic_gateway_sdk.models;

/**
 * DeleteWalletRequest
 */
public class DeleteWalletRequest {
    public transient String walletId;
    public DeleteWalletRequest walletId(String walletId) {
        this.walletId = walletId;
        return this;
    }
}