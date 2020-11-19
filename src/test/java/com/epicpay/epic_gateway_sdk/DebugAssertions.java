package com.epicpay.epic_gateway_sdk;

import static org.junit.Assert.assertTrue;

import com.epicpay.epic_gateway_sdk.models.*;

/**
 * DebugAssertions
 */
public class DebugAssertions {

    public static void assertReceived(Status resp) {
        assertTrue(resp.ReasonText, resp.ResponseCode.equalsIgnoreCase("Received"));
    }
    public static void assertApproved(Status resp) {
        assertTrue(resp.ReasonText, resp.ResponseCode.equalsIgnoreCase("Approved"));
    }
    public static void assertDeclined(Status resp) {
        assertTrue(resp.ReasonText, resp.ResponseCode.equalsIgnoreCase("Declined"));        
    }
    public static void assertError(Status resp) {
        assertTrue(resp.ReasonText, resp.ResponseCode.equalsIgnoreCase("Error"));
    }
}